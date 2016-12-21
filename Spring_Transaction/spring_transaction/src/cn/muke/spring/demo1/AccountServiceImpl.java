package cn.muke.spring.demo1;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

/*
 * 转账业务逻辑实现类
 * */
public class AccountServiceImpl implements AccountService {

	//注入转账DAO实现类的接口
	private AccountDAO accountDAO;
	
    public void setAccountDAO(AccountDAO accountDAO) {
		this.accountDAO = accountDAO;
	}
    
    //注入事务管理的模版
    private TransactionTemplate transactionTemplate;

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}


	/**
     * @param out     :转出帐号
     * @param in      :转入帐号
     * @param money   :转账金额
     * */
	@Override
	public void transfer(final String out, final String in, final Double money) {
		
		/*accountDAO.outMoney(out, money);
		int i = 1/0;  //这样会执行错误，使得aaa的钱转出了，但是bbb的钱没有增加！
		accountDAO.inMoney(in, money);
		*/
		
		
		
		/*
		 * 编程式的事务管理
		 * 现在：转出金额跟转入金额要么一起成功，要么一起失败。
		 * */
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
				accountDAO.outMoney(out, money);
				int i = 1/0;  
				accountDAO.inMoney(in, money);
				
			}
		});
	}

}
