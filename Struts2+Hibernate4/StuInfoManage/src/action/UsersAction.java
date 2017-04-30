package action;

import org.apache.struts2.interceptor.validation.SkipValidation;

import service.UsersDAO_Me;
import serviceImpl.UsersDAO_Me_Impl;

import com.opensymphony.xwork2.ModelDriven;

import entity.Users;

public class UsersAction extends SuperAction implements ModelDriven<Users> {

	private static final long serialVersionUID = 1L;
	private Users user = new Users();
	
	//用户登录
	public String login(){
		UsersDAO_Me udao = new UsersDAO_Me_Impl();
		if(udao.usersLogin(user)){
			
			/*
			 * 在session中保存登陆成功的用户名
			 * UsersAction继承了SuperAction，在SuperAction中有web内置对象可以直接使用
			 * */
			session.setAttribute("loginUserName", user.getUsername());
			return "login_success";
		}
		else{
			return "login_failure";
		}
	}
	
	@SkipValidation             /*因为validate()默认对UsersAction中所有action都验证，所以在这注释注销action不进行验证*/
	//用户注销
	public String logout(){
		
		if(session.getAttribute("loginUserName")!=null){
			
			session.removeAttribute("loginUserName");
			session.invalidate();
			/*sessionId并没有清除？*/
		}
		return "logout_success";
	}
	
	@Override
	public Users getModel() {
		// TODO Auto-generated method stub
		return this.user;
	}

	@Override
	public void validate() {
		// TODO Auto-generated method stub
		
		/*使用默认的表单验证功能要在struts.xml中进行配置！
		 * <result name="input">/users/Users_login.jsp</result>
		 * */
		if("".equals(user.getUsername().trim())){
			/*trim()去前后空格*/
			this.addFieldError("usernameError", "用户名不能为空！");
		}
		if(user.getPassword().length()<6){
			this.addFieldError("passwordError", "密码长度不能少于6位！");
		}
		
	}
	
	

}
