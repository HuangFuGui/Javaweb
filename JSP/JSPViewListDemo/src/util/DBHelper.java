package util;
/*
 * 连接数据库
 * */
import java.sql.Connection;
import java.sql.DriverManager;
public class DBHelper {
	
	/*在此之前要导入mysql connector jar包*/
	
	private static final String driver="com.mysql.jdbc.Driver";	
	/*3306是mysql数据库的端口，8001是Apache的端口（解析PHP）*/
	private static final String url="jdbc:mysql://localhost:3306/mystream?useUnicode=true&characterEncoding=UTF-8";	
	private static final String username="root";
	private static final String password="";
   private static Connection conn = null;;
	
	/*静态代码块负责加载驱动*/
	static
	{
		try
		{
			Class.forName(driver).newInstance();
			System.out.print("成功加载驱动");
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	/*单例模式返回数据库连接对象*/
	public static Connection getConnection() throws Exception
	{
		if(conn == null)
		{
			conn = DriverManager.getConnection(url, username, password);
			return conn;
		}
		return conn;
		
	}
	
	/*在此检查是否能成功连接数据库*/
	public static void main(String[] args) {
		try
		{
			Connection conn = DBHelper.getConnection();
			if(conn!=null)
			{
				System.out.print("数据库连接正常");
			}
			else
			{
				System.out.print("数据库连接错误");
			}
		}
		catch(Exception ex)
		{
		    ex.printStackTrace();
		}
	}
	
}