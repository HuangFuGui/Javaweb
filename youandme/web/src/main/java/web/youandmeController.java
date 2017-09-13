package web;

import complexService.download.downloadService;
import complexService.plupload.pluploadService;
import dto.youandmeResult;
import entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import service.youandmeService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/youandme")
public class youandmeController {

    private final String salt = "asd$%^$156120#BbK0-%^%*&!&*fef~{}@##VJ*{))&@@@@#";

    //自动装载service写好的接口（已实现对象，存在SpringIOC容器中）
    @Autowired
    private youandmeService youandmeService;

    @Autowired
    private pluploadService pluploadService;

    @Autowired
    private downloadService downloadService;

    /**
     * 登录页面，包含注册
     * @param model
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String loginPage(Model model){
        return "login";
    }

    @RequestMapping(value = "/plupload",method = RequestMethod.GET)
    public String plupload(Model model){
        return "plupload";
    }

    /**Plupload文件上传处理方法*/
    @RequestMapping(value="/pluploadUpload")
    public void upload(Plupload plupload,HttpServletRequest request,HttpServletResponse response) {

        String FileDir = "pluploadDir";//文件保存的文件夹
        plupload.setRequest(request);//手动传入Plupload对象HttpServletRequest属性

        int userId = ((User)request.getSession().getAttribute("user")).getUserId();

        //文件存储绝对路径,会是一个文件夹，项目相应Servlet容器下的"pluploadDir"文件夹，还会以用户唯一id作划分
        File dir = new File(request.getSession().getServletContext().getRealPath("/")+"/user_space/"+userId+"/"+FileDir);
        if(!dir.exists()){
            dir.mkdirs();//可创建多级目录，而mkdir()只能创建一级目录
        }
        //开始上传文件
        pluploadService.upload(plupload, dir);
    }


    /**
     * 主页
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String indexPage(HttpServletRequest request,Model model){

        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        if(user==null){
            //return "redirect:/youandme/login";//客户端请求重定向，URL地址栏改变，有些浏览器会有sessionId在地址栏重写//TODO
            return "forward:/youandme/login";//服务器内部请求转发，URL地址栏不会改变
        }
        else {
            model.addAttribute("userModel",user);

            //每次刷新主页都显示全部动态信息
            List<SocialDynamics> list = youandmeService.showDynamics();
            model.addAttribute("dynamicsModel",list);

            //用session记录当前最大的动态id
            int pos = youandmeService.curMaxDynamicsId();
            session.setAttribute("pos",pos);
            session.setMaxInactiveInterval(86400);//session生命周期为一天

            return "index";
        }
    }


    /**
     * 用户详细页面
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/{userId}/userDetail" ,method = RequestMethod.GET)
    public String userDetailPage(HttpServletRequest request,
                                 Model model,
                                 @PathVariable("userId") int userId){

        User user = youandmeService.queryUserById(userId);
        if(user!=null){
            model.addAttribute("userModel",user);
            return "userDetail";
        }else{
            return "forward:/youandme/index";
        }
    }


    /**
     * 注册操作
     * @param request
     * @return
     */
    @RequestMapping(value = "/registerUser",
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public youandmeResult<User> registerResponse(HttpServletRequest request){
        //获取键值对参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        String base = password+"/"+salt;
        String passwordMD5 = DigestUtils.md5DigestAsHex(base.getBytes());

        //注册service
        youandmeService.register(username,passwordMD5,email);

        User user = new User(username);
        return new youandmeResult<User>(user,true,"register success!");//注册成功只返回用户名
    }



    /**
     * 登录操作
     * @param request
     * @return
     */
    @RequestMapping(value = "/userLogin",
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public youandmeResult loginResponse(HttpServletRequest request){

        HttpSession session = request.getSession();
        if((User)session.getAttribute("user")!=null){
            //同一个浏览器在注销账号前不能再登录
            return new youandmeResult(false,"you are already logged in an account in the same browser!");
        }

        String stringToLogin = request.getParameter("stringToLogin");

        String password = request.getParameter("password");
        String base = password+"/"+salt;
        String passwordMD5 = DigestUtils.md5DigestAsHex(base.getBytes());
        System.out.println(passwordMD5);

        User user = youandmeService.login(stringToLogin, passwordMD5);
        if(user == null){//不能登录
            return new youandmeResult(false,"fail to login!Please check your Information!");
        }
        else{//能登录，服务端保存用户信息至session
            session.setAttribute("user",user);
            return new youandmeResult(true,"login success");
        }
    }


    /**
     * 用户更改头像操作
     * @param request
     * @return
     */
    @RequestMapping(value = "/changeHeadImg",
                    produces ={"application/JSON;charset=UTF-8"})
    @ResponseBody
    public youandmeResult changeHeadImg(HttpServletRequest request){

        HttpSession session = request.getSession();
        int userId = ((User)session.getAttribute("user")).getUserId();

        boolean changeResult = youandmeService.changeHeadImg(request, userId);
        if(changeResult==true){
            //涉及到更新资料要及时更新session
            User newUser = youandmeService.queryUserById(userId);
            session.setAttribute("user",newUser);//更新session
            return new youandmeResult(newUser.getHeadImg(),true);
        }
        else{
            return new youandmeResult(false,"头像上传失败");
        }
    }

    /**
     * 用户更新用户名，邮箱等文本资料
     * @param request
     * @return
     */
    @RequestMapping(value = "changeInfo",
                    produces = {"application/JSON;charset=UTF-8"})
    @ResponseBody
    public youandmeResult changeInfo(HttpServletRequest request){

        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        int userId = user.getUserId();

        String newUsername = request.getParameter("newUsername");
        String newEmail = request.getParameter("newEmail");
        String newAddress = request.getParameter("newAddress");
        String newDescription = request.getParameter("newDescription");
        boolean updateResult = youandmeService.changePersonalInfo(userId, newUsername, newEmail, newAddress, newDescription);

        //涉及到更新资料需要及时更新session
        user = youandmeService.queryUserById(userId);
        session.setAttribute("user",user);//更新user session

        if(updateResult==true){
            return new youandmeResult(true,"更改资料成功");
        }
        else{
            return new youandmeResult(false,"更改资料失败");
        }
    }

    /**
     * 用户上传动态操作
     * @param request
     * @return
     */
    @RequestMapping(value = "/postDynamics",
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public youandmeResult postDynamics(HttpServletRequest request){

        HttpSession session = request.getSession();
        int userId = ((User) session.getAttribute("user")).getUserId();

        //得到还没发表动态前表中最大的动态主键值，强制转换后自动拆箱
        int pos = (Integer)session.getAttribute("pos");

        //执行发表动态，即插入数据库与上传动态文件
        youandmeService.postDynamics(request, userId);
        User user = youandmeService.queryUserById(userId);//涉及到更新用户动态信息，要更新user session
        session.setAttribute("user",user);

        //返回新的动态用于局部刷新
        List<SocialDynamics> list = youandmeService.showNewDynamics(pos);
        session.setAttribute("pos",youandmeService.curMaxDynamicsId());

        return new youandmeResult<List<SocialDynamics>>(list,true);
    }

    /**
     * 用户点赞操作
     * @param request
     * @return
     */
    @RequestMapping(value ="/clickLike",
                    produces = {"application/JSON;charset=UTF-8"})
    @ResponseBody
    public youandmeResult clickLike(HttpServletRequest request){

        HttpSession session = request.getSession();
        int userId = ((User)session.getAttribute("user")).getUserId();
        int dynamicsId = Integer.valueOf(request.getParameter("dynamicsId"));

        String clickLikeDynamicsResult = youandmeService.clickLikeDynamics(dynamicsId, userId);
        int newLikeNum = Integer.valueOf(clickLikeDynamicsResult.substring(0, clickLikeDynamicsResult.lastIndexOf(".")));

        if(clickLikeDynamicsResult.substring(clickLikeDynamicsResult.lastIndexOf(".")+1).equals("like")){
            return new youandmeResult(newLikeNum,true);//点赞
        }
        else {
            return new youandmeResult(newLikeNum,false);//取消点赞
        }
    }

    /**
     * 进入主页时对已经点赞的动态进行 “点赞” 显示
     * @param request
     * @return
     */
    @RequestMapping(value = "/showLikeHelp",
                    produces = {"application/JSON;charset=UTF-8"})
    @ResponseBody
    public youandmeResult showLikeHelp(HttpServletRequest request){

        HttpSession session = request.getSession();
        int userId = ((User)session.getAttribute("user")).getUserId();
        List<Integer> list = youandmeService.showWhichLike(userId);
        return new youandmeResult<List<Integer>>(list,true);
    }

    /**
     * 用户点击评论图标后模态框出现，返回动态信息
     * @param request
     * @return
     */
    @RequestMapping(value = "/detailDynamicsById",
                    produces = {"application/JSON;charset=UTF-8"})
    @ResponseBody
    public youandmeResult showDetailDynamicsById(HttpServletRequest request){
        int dynamicsId = Integer.valueOf(request.getParameter("dynamicsId"));
        SocialDynamics socialDynamics = youandmeService.showDetailDynamicsById(dynamicsId);
        return new youandmeResult<SocialDynamics>(socialDynamics,true);
    }

    /**
     * 用户点击评论图标后模态框出现，返回点赞用户id与头像
     * @param request
     * @return
     */
    @RequestMapping(value = "/dynamicsOfLikeUser",
                    produces = {"application/JSON;charset=UTF-8"})
    @ResponseBody
    public youandmeResult<List<User>> showLikeUserOfDynamics(HttpServletRequest request){

        int dynamicsId = Integer.valueOf(request.getParameter("dynamicsId"));
        List<User> userList = youandmeService.showLikeUserOfDynamics(dynamicsId);

        HttpSession session = request.getSession();
        User user =(User)session.getAttribute("user");
        userList.add(user);

        return new youandmeResult<List<User>>(userList,true);
    }

    /**
     * 将动态的评论数据返回至前端
     * @param request
     * @return
     */
    @RequestMapping(value = "/showCommentOfDynamics",
                    produces = {"application/JSON;charset=UTF-8"})
    @ResponseBody
    public youandmeResult showCommentOfDynamics(HttpServletRequest request){

        int dynamicsId = Integer.valueOf(request.getParameter("dynamicsId"));
        List<CommentInfo> commentList = youandmeService.showCommentById(dynamicsId);
        return new youandmeResult<List<CommentInfo>>(commentList,true);
    }

    /**
     * 用户对动态发表评论
     * @param request
     * @return
     */
    @RequestMapping(value = "/sendComment",
                    produces = {"application/JSON;charset=UTF-8"})
    @ResponseBody
    public youandmeResult sendComment(HttpServletRequest request){

        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        int dynamicsId = Integer.valueOf(request.getParameter("dynamicsId"));
        String commentContent  = request.getParameter("commentContent");

        CommentInfo commentInfo = youandmeService.sendComment(dynamicsId, user.getUserId(), commentContent);
        return  new youandmeResult<CommentInfo>(commentInfo,true);
    }

    /**
     * 回复评论时显示当前用户（头像）以及被回复用户名
     * @param request
     * @return
     */
    @RequestMapping(value = "/replyCommentHelp",
                    produces = {"application/JSON;charset=UTF-8"})
    @ResponseBody
    public youandmeResult replyCommentHelp(HttpServletRequest request){

        int commentId = Integer.valueOf(request.getParameter("commentId"));
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");//发表回复的用户信息
        User user2 = youandmeService.showComment(commentId).getSendUser();//被回复用户的信息
        List<User> list = new ArrayList<User>();
        list.add(user);
        list.add(user2);

        return new youandmeResult<List<User>>(list,true);
    }

    /**
     * 用户对动态的评论进行回复
     * @param request
     * @return
     */
    @RequestMapping(value = "/sendReply",
                    produces = {"application/JSON;charset=UTF-8"})
    @ResponseBody
    public youandmeResult sendReply(HttpServletRequest request){

        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");

        int sendId = user.getUserId();
        int commentId = Integer.valueOf(request.getParameter("commentId"));
        String replyCommentContent = request.getParameter("replyCommentContent");

        ReplyInfo replyInfo = youandmeService.sendReply(commentId, sendId, replyCommentContent);
        return new youandmeResult<ReplyInfo>(replyInfo,true);
    }

    /**
     * 根据动态的评论id返回该评论的所有回复
     * @param request
     * @return
     */
    @RequestMapping(value = "/showReplyOfComment",
            produces = {"application/JSON;charset=UTF-8"})
    @ResponseBody
    public youandmeResult showReplyOfComment(HttpServletRequest request){

        int commentId = Integer.valueOf(request.getParameter("commentId"));
        List<ReplyInfo> list = youandmeService.showAllReplyByCommentId(commentId);
        return new youandmeResult<List<ReplyInfo>>(list,true);
    }

    /**
     * 当用户要回复评论中的回复时，显示当前用户头像，要回复的用户名
     * @param request
     * @return
     */
    @RequestMapping(value = "/replyReplyHelp",
            produces = {"application/JSON;charset=UTF-8"})
    @ResponseBody
    public youandmeResult replyReplyHelp(HttpServletRequest request){

        int replyId = Integer.valueOf(request.getParameter("replyId"));

        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");//发表回复评论的回复的用户信息
        User user2 = youandmeService.showReplyInfo(replyId).getSendUser();//被回复用户的信息
        List<User> list = new ArrayList<User>();
        list.add(user);
        list.add(user2);

        return new youandmeResult<List<User>>(list,true);
    }

    /**
     * 用户对动态评论的回复进行回复
     * @param request
     * @return
     */
    @RequestMapping(value = "/sendReplyOfReply",
            produces = {"application/JSON;charset=UTF-8"})
    @ResponseBody
    public youandmeResult sendReplyOfReply(HttpServletRequest request){

        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");

        int sendId = user.getUserId();
        int replyId = Integer.valueOf(request.getParameter("replyId"));
        String replyText = request.getParameter("replyText");

        ReplyInfo replyInfo = youandmeService.sendReplyOfReply(replyId, sendId, replyText);

        return new youandmeResult<ReplyInfo>(replyInfo,true);
    }

    /**
     * 用户在input.searchUserToContact_search中输入用户名，Lucene查找并高亮
     * @param request
     * @return
     */
    @RequestMapping(value = "/luceneSearchUser",
                    produces = {"application/JSON;charset=UTF-8"})
    @ResponseBody
    public youandmeResult luceneSearchUser(HttpServletRequest request){

        String inputString = request.getParameter("inputString");
        List<User> userList = youandmeService.luceneSearchUser(inputString);
        return new youandmeResult<List<User>>(userList,true);
    }

    /**
     * 用户查找想要聊天的朋友，点击div.searchUserToContact_body中的div.userToContact后触发的查找相应用户详细信息
     * 加入到div.contactDivTrue_left中
     * @param request
     * @return
     */
    @RequestMapping(value = "/prepareUserToContact",
            produces = {"application/JSON;charset=UTF-8"})
    @ResponseBody
    public youandmeResult prepareUserToContact(HttpServletRequest request){

        int userId = Integer.valueOf(request.getParameter("userId"));
        User user = youandmeService.queryUserById(userId);
        return new youandmeResult<User>(user,true);
    }

    /**
     * 显示两个用户过去的聊天记录
     * @param request
     * @return
     */
    @RequestMapping(value = "/showContentInThePast",
            produces = {"application/JSON;charset=UTF-8"})
    @ResponseBody
    public youandmeResult showContentInThePast(HttpServletRequest request){

        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        int toId = Integer.valueOf(request.getParameter("toId"));

        List<Message> list = youandmeService.showMessage(user.getUserId(), toId);
        return new youandmeResult<List<Message>>(list,true);
    }

    @RequestMapping(value = "/showUploadFileOfUser",
                    produces = {"application/JSON;charset=UTF-8"})
    @ResponseBody
    public youandmeResult showUploadFileOfUser(HttpServletRequest request) {

        User user = (User)request.getSession().getAttribute("user");
        String username = user.getUsername();
        List<PluploadFile> list = youandmeService.showUploadOfUser(username);
        return new youandmeResult<List<PluploadFile>>(list,true);
    }


    @RequestMapping(value = "/deleteFile")
    public void deleteFile(HttpServletRequest request){

        int id = Integer.valueOf(request.getParameter("id"));
        int userId = ((User)request.getSession().getAttribute("user")).getUserId();
        youandmeService.deletePluploadFile(request,userId,id);
    }

    @RequestMapping(value = "/downloadFile/{id}")
    public void downloadFile(@PathVariable("id") int id,HttpServletRequest request,HttpServletResponse response){
        try {
            int userId = ((User)(request.getSession().getAttribute("user"))).getUserId();
            downloadService.downloadSolve(id,request,response,userId);
        }catch (ServletException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
