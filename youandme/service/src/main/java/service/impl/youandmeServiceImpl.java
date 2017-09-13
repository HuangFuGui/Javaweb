package service.impl;

import dao.*;
import entity.*;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import service.youandmeService;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.nio.file.FileSystems;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


@Service
public class youandmeServiceImpl implements youandmeService {

    @Autowired
    private LoginDao loginDao;

    @Autowired
    private DynamicsDao dynamicsDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private MessageDao messageDao;

    @Autowired
    private PluploadDao pluploadDao;

    public int register(String username, String password,String email) {

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = simpleDateFormat.format(date);

        int result = loginDao.insertUser(username,password,email,dateString);
        return result;
    }

    public User login(String stringToLogin, String password) {

        User user = loginDao.selectUserFromAllUser(stringToLogin, password);
        return user;
    }

    public void postDynamics(HttpServletRequest request, int userId) {

        String dynamicsText = "";
        String dynamicsFile = "";
        try{

            if(!ServletFileUpload.isMultipartContent(request)){
                //如果上传的数据不是表单原始的数据（经过编码），则直接返回。因为只有表单原始数据才会被接收
                //http://stackoverflow.com/questions/4526273/what-does-enctype-multipart-form-data-mean
                return;
            }

            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            dynamicsText = multipartRequest.getParameter("dynamicsText");//获取表单文本部分
            MultipartFile multipartFile = multipartRequest.getFile("dynamicsFile");//获取表单

            String savePath = request.getSession().getServletContext().getRealPath("/") + "user_space/"+userId;
            File file = new File(savePath);
            if (!file.exists() && !file.isDirectory()) {
                file.mkdirs();//新建文件夹（多重）
            }
            dynamicsFile = multipartFile.getOriginalFilename();
            multipartFile.transferTo(new File(savePath+"/"+dynamicsFile));

            Timestamp now = new Timestamp(System.currentTimeMillis());
            dynamicsDao.insertDynamics(userId, dynamicsText, userId + "/" + dynamicsFile, now);
            userDao.updateDynamicsNum(userId);//更新发表动态数量

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<SocialDynamics> showDynamics() {
        List<SocialDynamics> list = dynamicsDao.selectAllDynamics();
        return list;
    }

    public int curMaxDynamicsId() {
        String curMaxDynamicsIdString = dynamicsDao.selectMaxDynamicsId();
        if(curMaxDynamicsIdString==null){
            return 0;
        }
        else{
            return Integer.valueOf(curMaxDynamicsIdString);
        }
    }

    public List<SocialDynamics> showNewDynamics(int pos) {
        List<SocialDynamics> list = dynamicsDao.selectDynamicsFromPos(pos);
        return list;
    }

    public boolean changeHeadImg(HttpServletRequest request, int userId) {
        String headimgName = "";
        try{

            if(!ServletFileUpload.isMultipartContent(request)){
                return false;
            }

            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            MultipartFile multipartFile = multipartRequest.getFile("headImg");//获取表单
            headimgName = multipartFile.getOriginalFilename();

            String savePath = request.getSession().getServletContext().getRealPath("/") + "user_space/"+userId;
            File file = new File(savePath);
            if(!file.exists()&&!file.isDirectory()){
                file.mkdirs();
            }
            multipartFile.transferTo(new File(savePath+"/"+headimgName));

        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
        userDao.updateUserHeadImg(userId,userId+"/"+headimgName);
        return true;
    }

    public boolean changePersonalInfo(int userId, String username, String email, String address, String description) {
        int updateResult = userDao.updateUser(userId, username, email, address, description);
        if(updateResult==1){
            return true;
        }
        else {
            return false;
        }
    }

    public User queryUserById(int userId) {
        User user = userDao.selectUserById(userId);
        return user;
    }

    public String clickLikeDynamics(int dynamicsId, int userId) {
        int flag = 0;
        int selectLikeResult = dynamicsDao.selectLike(dynamicsId, userId);
        if(selectLikeResult == 0){
            flag = 1;
            dynamicsDao.updateLikeNum(dynamicsId);
            dynamicsDao.insertLike(dynamicsId, userId);

        }else if(selectLikeResult==1) {
            dynamicsDao.updateLikeNumSub(dynamicsId);
            dynamicsDao.deleteLike(dynamicsId, userId);
        }
        int newLikeNum = dynamicsDao.selectLikeNum(dynamicsId);
        if(flag ==1){
            return newLikeNum+".like";
        }else{
            return newLikeNum+".unlike";
        }
    }

    public List<Integer> showWhichLike(int userId) {
        List<Integer> list = dynamicsDao.selectWhichLike(userId);
        return list;
    }

    public SocialDynamics showDetailDynamicsById(int dynamicsId) {
        SocialDynamics socialDynamics = dynamicsDao.selectDetailDynamicsById(dynamicsId);
        return socialDynamics;
    }

    public List<User> showLikeUserOfDynamics(int dynamicsId) {
        List<User> list = dynamicsDao.selectLikeUserOfDynamics(dynamicsId);
        return list;
    }

    public CommentInfo sendComment(int dynamicsId, int sendId,String commentText) {

        Timestamp now = new Timestamp(System.currentTimeMillis());
        SocialDynamics socialDynamics = dynamicsDao.selectDetailDynamicsById(dynamicsId);
        int insertCommentResult = commentDao.insertComment(dynamicsId, sendId, socialDynamics.getUser().getUsername(), commentText, now);
        if(insertCommentResult == 1){
            CommentInfo commentInfo = commentDao.selectNewestCommentOfUser(sendId);
            return commentInfo;
        }
        return null;
    }

    public List<CommentInfo> showCommentById(int dynamicsId) {
        List<CommentInfo> list = commentDao.selectCommentByDynamicsId(dynamicsId);
        return list;
    }

    public CommentInfo showComment(int commentId) {
        CommentInfo commentInfo = commentDao.selectCommentById(commentId);
        return commentInfo;
    }

    public ReplyInfo sendReply(int commentId, int sendId, String replyText) {

        Timestamp now = new Timestamp(System.currentTimeMillis());
        String receiveUsername = commentDao.selectCommentById(commentId).getSendUser().getUsername();
        commentDao.insertReply(commentId, sendId, receiveUsername, replyText, now);

        ReplyInfo replyInfo = commentDao.selectReplyInfoBysendId(sendId);
        return replyInfo;
    }

    public List<ReplyInfo> showAllReplyByCommentId(int commentId) {
        List<ReplyInfo> list = commentDao.selectReplyInfoByCommentId(commentId);
        return list;
    }

    public ReplyInfo sendReplyOfReply(int replyId, int sendId, String replyText) {

        Timestamp now = new Timestamp(System.currentTimeMillis());

        ReplyInfo replyInfo = commentDao.selectReplyInfoById(replyId);//�û�Ҫ���лظ��Ļظ���
        String receiveUsername = replyInfo.getSendUser().getUsername();
        int commentId = replyInfo.getCommentId();

        commentDao.insertReply(commentId,sendId,receiveUsername,replyText,now);

        ReplyInfo replyInfo2 = commentDao.selectReplyInfoBysendId(sendId);//�û������»ظ�
        return replyInfo2;
    }

    public ReplyInfo showReplyInfo(int replyId) {
        ReplyInfo replyInfo = commentDao.selectReplyInfoById(replyId);
        return replyInfo;
    }

    public List<User> luceneSearchUser(String inputString) {

        IndexWriter indexWriter = null;
        try{
            //创建存放Lucene索引文件的Directory
            Directory directory = FSDirectory.open(
                    FileSystems.getDefault().getPath("C:\\wamp\\www\\LuceneIndex"));
            //创建IndexWriter
            Analyzer analyzer = new StandardAnalyzer();
            IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
            indexWriter = new IndexWriter(directory, indexWriterConfig);
            indexWriter.deleteAll();//删除之前的全部索引文件

            List<User> userList = userDao.selectAllUserForLucene();
            for(User user:userList){
                //内存中创建document对象
                Document document = new Document();
                document.add(new Field("userId",String.valueOf(user.getUserId()), TextField.TYPE_STORED));
                document.add(new Field("username",user.getUsername(), TextField.TYPE_STORED));
                document.add(new Field("headImg",user.getHeadImg(),TextField.TYPE_STORED));
                /**
                 * 1. 会对指定的每个域进行分词做倒排的词典，每个域词典的词指向相应索引中文件，可以根据不用的域进行检索
                 * 2. 规定TYPE_NOT_STORED的域不会将内容存在索引中文件（检索索引文件的时候会返回document对象）
                 * 但是还会将此域进行分词做倒排的词典，指向特定的（索引中）文件，检索的时候可以根据该域进行检索
                 */
                if(user.getAddress()==null){
                    document.add(new Field("address","",TextField.TYPE_STORED));
                }else{
                    document.add(new Field("address",user.getAddress(),TextField.TYPE_STORED));
                }
                indexWriter.addDocument(document);//创建索引文件（将这个内存中的document写到磁盘中）
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally {

            try{
                indexWriter.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        DirectoryReader directoryReader = null;
        List<User> resultUserList = new ArrayList<User>();
        try {
            //创建Directory对象,路径即为索引文件的路径
            Directory directory = FSDirectory.open(
                    FileSystems.getDefault().getPath("C:\\wamp\\www\\LuceneIndex"));
            //根据Directory创建IndexReader
            directoryReader = DirectoryReader.open(directory);
            //根据IndexReader创建IndexSearch
            IndexSearcher indexSearcher = new IndexSearcher(directoryReader);

            //创建Query对象,指定要搜索的域和内容
            Analyzer analyzer = new StandardAnalyzer();
            QueryParser queryParser = new QueryParser("username", analyzer);
            Query query = queryParser.parse(inputString);

            //开始检索,查询返回前1000个TopDocs
            TopDocs topDocs = indexSearcher.search(query, 1000);
            System.out.println("the number of query hits:" +topDocs.totalHits);

            //根据TopDocs获取ScoreDoc对象
            ScoreDoc[] scoreDocs = topDocs.scoreDocs;
            for (ScoreDoc scoreDoc : scoreDocs) {
                //获得相应的document对象
                Document document = indexSearcher.doc(scoreDoc.doc);
                System.out.println("userId:"+document.get("userId")+" username:"+document.get("username")+" headImg:"+document.get("headImg"));

                String value = toHighlighter(query,document,"username",analyzer);
                System.out.println("highlight result:"+value);

                User user = new User(Integer.valueOf(document.get("userId")),value,document.get("headImg"),document.get("address"));
                resultUserList.add(user);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (directoryReader != null) {
                    directoryReader.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return resultUserList;
    }

    //Lucene高亮
    public String toHighlighter(Query query,Document doc,String field,Analyzer analyzer){
        try {
            SimpleHTMLFormatter simpleHtmlFormatter = new SimpleHTMLFormatter("<font>", "</font>");
            Highlighter highlighter = new Highlighter(simpleHtmlFormatter,new QueryScorer(query));
            TokenStream tokenStream1 = analyzer.tokenStream("text",new StringReader(doc.get(field)));
            String highlighterStr = highlighter.getBestFragment(tokenStream1, doc.get(field));
            return highlighterStr == null ? doc.get(field):highlighterStr;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (InvalidTokenOffsetsException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void addMessage(int fromId, String fromName, int toId, String messageText, Timestamp messageDate) {
        messageDao.insertMessage(fromId,fromName,toId,messageText,messageDate);
    }

    public List<Message> showMessage(int fromId, int toId) {
        List list = messageDao.selectMessageOfTwo(fromId,toId);
        return list;
    }

    public void uploadInfo(String fileName, String uploadUsername, Timestamp uploadTime) {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        pluploadDao.insertFileInfo(fileName,uploadUsername,now);
    }

    public List<PluploadFile> showUploadOfUser(String uploadUsername) {
        List<PluploadFile> list = pluploadDao.selectFileByUsername(uploadUsername);
        return list;
    }

    public void deletePluploadFile(HttpServletRequest request,int userId,int id) {

        PluploadFile pluploadFile = pluploadDao.selectFileById(id);
        File file = new File(request.getSession().getServletContext().getRealPath("/")+"user_space/"+userId+"/pluploadDir/"+pluploadFile.getFileName());
        if(file.exists()){
            file.delete();//删除磁盘文件
            pluploadDao.deleteInfoOfFile(id);//数据库中删除文件相关信息
        }
    }

    public PluploadFile showFileOfId(int id) {
        PluploadFile pluploadFile = pluploadDao.selectFileById(id);
        return pluploadFile;
    }

}
