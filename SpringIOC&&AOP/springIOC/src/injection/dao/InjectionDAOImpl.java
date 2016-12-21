package injection.dao;

public class InjectionDAOImpl implements InjectionDAO {

	@Override
	//模拟数据库保存操作
	public void save(String args) {
		
		System.out.println("保存数据："+args);
	}

}
