package cn.muke.spring.demo1;
/*
 * 转账DAO层接口
 * */
public interface AccountDAO {

	/**
	 * 
	 * @param out   :转出帐号
	 * @param money :转出金额
	 */
	public void outMoney(String out,Double money);
	
	/**
	 * 
	 * @param in    :转入帐号
	 * @param money :转入金额
	 */
	public void inMoney(String in,Double money);
}
