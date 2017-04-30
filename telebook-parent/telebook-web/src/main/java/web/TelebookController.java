package web;

import dto.DataTransfer;
import entity.Accounts;
import entity.Contacts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.TelebookService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by huangfugui on 2016/10/22.
 * MVC中着重写M与V，带有@Controller的类其实是一个javabean，是在Model中的controller。
 */
@Controller
@RequestMapping(value = "/telebook")
public class TelebookController {

    @Autowired
    private TelebookService telebookService;

    //主页
    @RequestMapping(value = "/index",method= RequestMethod.GET)
    private String index(Model model,HttpServletRequest request){
        HttpSession session = request.getSession();
        Accounts accounts = (Accounts) session.getAttribute("account");
        model.addAttribute("account",accounts);

        List<Contacts> list = null;
        if(accounts!=null){
            list = telebookService.showAllContacts(accounts.getAccountId());
        }
        model.addAttribute("contacts",list);
        return "index";
    }

    //注册
    @RequestMapping(value = "/register",
                    produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    private DataTransfer<Integer> register(Model model, HttpServletRequest request, HttpServletResponse response){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        int result = telebookService.register(username,password);
        if(result==-1){
            //账号重复
            return new DataTransfer<Integer>(-1,false);
        }
        else if(result==0){
            //注册失败
            return new DataTransfer<Integer>(0,false);
        }
        else{
            //注册成功
            return new DataTransfer<Integer>(1,true);
        }
    }

    //登录
    @RequestMapping(value = "/login",
                    produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    private DataTransfer login(Model model,HttpServletRequest request,HttpServletResponse response){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        int result = telebookService.login(username,password);
        if(result>0){//登录成功
            //保存session
            Accounts accounts = new Accounts(result,username);
            HttpSession session = request.getSession();
            session.setAttribute("account",accounts);

            //返回当前用户联系人列表
            List<Contacts> list = telebookService.showAllContacts(result);
            return new DataTransfer<List<Contacts>>(list,true);
        }else{//result==-1
            //登录失败
            return new DataTransfer(false);
        }
    }

    //删除联系人
    //org.springframework.beans.factory.BeanCreationException: Error creating bean with name
    //'org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping#0':
    //Invocation of init method failed; nested exception is org.springframework.http.InvalidMediaTypeException:
    //Invalid mime type "{application/json;charset=UTF-8}": Invalid token character '{' in token "{application"
    @RequestMapping(value = "/deleteContact",
                    produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    private DataTransfer deleteContact(Model model,HttpServletRequest request){
        int contactId = Integer.valueOf(request.getParameter("contactId"));
        boolean result = telebookService.dropContact(contactId);
        if(result==true){
            return new DataTransfer(true);//删除联系人成功
        }
        else{
            return new DataTransfer(false);//删除联系人失败
        }
    }

    //update.jsp映射，可能是添加/修改联系人
    @RequestMapping(value = "/{contactId}/update")
    private String updatePage(Model model, HttpServletRequest request, @PathVariable("contactId") int contactId){

        HttpSession session = request.getSession();
        Accounts accounts = (Accounts) session.getAttribute("account");
        if(accounts==null){
            return "redirect:../index";//还没有登录就重定向至首页
        }
        else{
            model.addAttribute("account",accounts);
            if(contactId!=-1){//是修改操作
                Contacts contacts = telebookService.showContactById(contactId);
                model.addAttribute("contact",contacts);
            }
            return "update";
        }
    }

    /**
     * 头像更换
     * @param request
     * @return
     */
    @RequestMapping(value = "/headImgChange",
                    produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    private DataTransfer headImgChange(HttpServletRequest request){
        String result = telebookService.headImgChange(request);
        if(result==null){
            return new DataTransfer(false);//上传失败
        }
        else{//上传成功，返回图片名
            return new DataTransfer<String>(result,true);
        }
    }

    /**
     * 插入联系人
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/insertContact",
                    produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    private DataTransfer insertContact(Model model,HttpServletRequest request){

        String headImgPath = request.getParameter("headImgPath");
        String contactName = request.getParameter("contactName");
        String contactPhone = request.getParameter("contactPhone");
        String contactAddress = request.getParameter("contactAddress");

        HttpSession session = request.getSession();
        Accounts accounts = (Accounts) session.getAttribute("account");

        boolean result = telebookService.addContact(accounts.getAccountId(),
                                                    headImgPath,contactName,contactPhone,contactAddress);
        if(result==true){//添加联系人成功
            return new DataTransfer(true);
        }
        else{//添加联系人失败
            return new DataTransfer(false);
        }
    }

    /**
     * 更新联系人
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "{contactId}/updateContact",
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    private DataTransfer updateContact(Model model,
                                       HttpServletRequest request,
                                       @PathVariable("contactId") int contactId){

        String headImgPath = request.getParameter("headImgPath");
        String contactName = request.getParameter("contactName");
        String contactPhone = request.getParameter("contactPhone");
        String contactAddress = request.getParameter("contactAddress");

        boolean result = telebookService.modifyContact(contactId,
                headImgPath,contactName,contactPhone,contactAddress);
        if(result==true){//修改联系人成功
            return new DataTransfer(true);
        }
        else{//修改联系人失败
            return new DataTransfer(false);
        }
    }
}
