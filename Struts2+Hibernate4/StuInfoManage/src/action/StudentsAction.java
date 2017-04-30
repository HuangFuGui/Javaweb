package action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import entity.Students;
import service.StudentsDAO;
import serviceImpl.StudentsDAO_Impl;

/*
 * 学生Action类
 * */
public class StudentsAction extends SuperAction {

	private static final long serialVersionUID = 1L;
	
	//查询所有学生的动作
	public String query(){
		
		 //action中调用已经写好的接口来实现！
		StudentsDAO sdao = new StudentsDAO_Impl();
		List <Students> list = sdao.queryAllStudents();   /*list有可能是空，在接口实现类已考虑此情况*/
		//放进session中
		if(list!=null&&list.size()>0){
			
			/*继承了SuperAction，里面有Http会话对象*/
			session.setAttribute("students_list", list);
		}
		return  "query_success";
	}
	
	//删除指定学生的动作
	public String delete(){
		
		StudentsDAO sdao = new StudentsDAO_Impl();
		String sid = request.getParameter("sid");    //获得传过来的参数
		sdao.deleteStudents(sid);   //action中调用已经写好的接口来实现！
		
		return "delete_success";
	}
	
	
	//添加学生动作
	public String add() throws ParseException{
		
		/*新建一个学生对象*/
		Students s = new Students();
		
		/*为学生对象s设置相应信息*/
		s.setSname(request.getParameter("sname"));
		s.setGender(request.getParameter("gender"));
		s.setAddress(request.getParameter("address"));
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String birthdayString = request.getParameter("birthday");
		Date s_birthday = sdf.parse(birthdayString);    //这里没必要新建一个对象！可类比String，String没有新建对象。

		s.setBirthday(s_birthday);
		
		/*在controller里面调用已经写好的接口实现添加学生*/
		StudentsDAO sdao = new StudentsDAO_Impl();
		sdao.addStudents(s);
		
		return "add_success";
		
	}
	
	//修改学生资料，将学生原来信息展示在页面上
	public String modify(){
		
		String sid = request.getParameter("sid");
		StudentsDAO sdao = new StudentsDAO_Impl();
		Students s = sdao.queryStudentBysid(sid);
		
		//保存在会话中
		session.setAttribute("modify_students", s);
		
		return "modify_success";
	}
	
	//保存修改后的学生动作
	public String  save() throws ParseException{
		Students s = new Students();
		s.setSid(request.getParameter("sid"));
		s.setSname(request.getParameter("sname"));
		s.setGender(request.getParameter("gender"));
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String s_birthday = request.getParameter("birthday");
		Date birthday = sdf.parse(s_birthday);
		s.setBirthday(birthday);
		
		s.setAddress(request.getParameter("address"));
		
		/*调用已经写好的接口*/
		StudentsDAO sdao = new StudentsDAO_Impl();
		sdao.updateStudents(s);
		
		return "save_success";
	}

}
