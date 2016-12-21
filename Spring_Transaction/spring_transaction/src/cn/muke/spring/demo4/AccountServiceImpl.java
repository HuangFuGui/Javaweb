package cn.muke.spring.demo4;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/*
 * 转账业务逻辑实现类
 * */
@Transactional(propagation=Propagation.REQUIRED)
public class AccountServiceImpl implements AccountService {

	//注入转账DAO实现类的接口
	private AccountDAO accountDAO;
	
    public void setAccountDAO(AccountDAO accountDAO) {
		this.accountDAO = accountDAO;
	}
    

	/**
     * @param out     :转出帐号
     * @param in      :转入帐号
     * @param money   :转账金额
     * */
	@Override
	public void transfer( String out, String in, Double money) {
		
		accountDAO.outMoney(out, money);
		int i = 1/0;
		accountDAO.inMoney(in, money);
		
		
	}

}
