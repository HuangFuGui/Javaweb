package service.impl;

import dao.AccountsDao;
import dao.ContactsDao;
import entity.Accounts;
import entity.Contacts;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.TelebookService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

/**
 * Created by huangfugui on 2016/10/20.
 */
@Component
public class TelebookServiceImpl implements TelebookService {

    @Autowired
    private ContactsDao contactsDao;

    @Autowired
    private AccountsDao accountsDao;

    public List<Contacts> showAllContacts(int accountId) {
        List<Contacts> contactsList = contactsDao.selectAllContacts(accountId);
        if(!contactsList.isEmpty()){
            return contactsList;
        }
        else{
            return null;
        }
    }

    public Contacts showContactById(int contactId) {
        Contacts contacts = contactsDao.selectContactById(contactId);
        if(contacts!=null){
            return contacts;
        }
        return null;
    }

    public int login(String account, String password) {
        String result = accountsDao.selectAccount(account,password);
        if(result!=null){
            return Integer.valueOf(result);//登录成功
        }
        return -1;//登录失败
    }

    public boolean logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Accounts accounts = (Accounts) session.getAttribute("account");
        if(accounts!=null){
            session.setMaxInactiveInterval(0);
            return true;//注销成功
        }
        return false;//注销失败
    }

    public int register(String account, String password) {
        int result_fade = accountsDao.selectAccountBeforeInsert(account);
        if(result_fade==1){
            return -1;//账号重复
        }
        else{
            int result = accountsDao.insertAccount(account,password);
            if(result==1){
                return 1;//注册成功
            }
            else{
                return 0;//注册失败
            }
        }
    }

    public boolean addContact(int accountId,String headImg, String contactName, String contactPhone, String contactAddress) {
        int result = contactsDao.insertContact(accountId,headImg, contactName, contactPhone, contactAddress);
        if(result==1){
            return true;//新增联系人成功
        }
        else{
            return false;//新增联系人失败
        }
    }

    public boolean dropContact(int contactId) {
        int result = contactsDao.deleteContactById(contactId);
        if(result==1){
            return true;//删除联系人成功
        }
        else{
            return false;//删除联系人失败
        }
    }

    public boolean modifyContact(int contactId, String headImg, String contactName, String contactPhone, String contactAddress) {
        int result = contactsDao.updateContact(contactId, headImg, contactName, contactPhone, contactAddress);
        if(result==1){
            return true;//修改联系人成功
        }
        else{
            return false;//修改联系人失败
        }
    }

    public String headImgChange(HttpServletRequest request) {

        int flag=0;
        String filename=null;
        try{
            //创建一个DiskFileItemFactory工厂
            DiskFileItemFactory factory = new DiskFileItemFactory();
            //创建一个文件上传解析器
            ServletFileUpload upload = new ServletFileUpload(factory);
            //解决上传文件名的中文乱码
            upload.setHeaderEncoding("UTF-8");
            //判断提交上来的数据是否是上传表单的数据
            if(!ServletFileUpload.isMultipartContent(request)){
                return null;
            }
            //使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
            List<FileItem> list = upload.parseRequest(request);
            for(FileItem item : list){
                //如果fileitem中封装的是普通输入项的数据
                if(item.isFormField()){
                    String name = item.getFieldName();
                    //解决普通输入项的数据的中文乱码问题
                    String value = item.getString("UTF-8");//TODO
                    System.out.println(name + "=" + value);
                }
                else{
                    //如果fileitem中封装的是上传文件
                    //得到上传的文件名称
                    filename = item.getName();//TODO
                    System.out.println(filename);
                    if(filename==null || filename.trim().equals("")){
                        continue;
                    }
                    //注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如： c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
                    //处理获取到的上传文件的文件名的路径部分，只保留文件名部分
                    filename = filename.substring(filename.lastIndexOf("/")+1);
                    //获取item中的上传文件的输入流
                    InputStream in = item.getInputStream();

                    //There is one context per "web application" per Java Virtual Machine.
                    //The Servlet container is the deploy target:target/telebook-web/,which corresponding to webapp under source code.
                    //inside there is a SpringMVC Servlet:DispatcherServlet
                    String savePath = request.getSession().getServletContext().getRealPath("\\")+"\\resources\\img";
                    System.out.println(savePath);
                    File file = new File(savePath);
                    if (!file.exists() && !file.isDirectory()) {
                        file.mkdirs();
                    }
                    FileOutputStream out = new FileOutputStream(savePath + "\\" + filename);
                    byte buffer[] = new byte[1024];
                    int len = 0;
                    while((len=in.read(buffer))>0){
                        out.write(buffer, 0, len);
                    }
                    //关闭输入流
                    in.close();
                    //关闭输出流
                    out.close();
                    //删除处理文件上传时生成的临时文件
                    item.delete();
                    if(flag==0){
                        flag=1;
                    }
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        if(flag==1){
            return filename;
        }
        return null;
    }
}
