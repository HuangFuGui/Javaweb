package cn.muke.spring.demo2;
/*
 * 转账业务逻辑接口
 * */
public interface AccountService {

    /**
     * @param out     :转出帐号
     * @param in      :转入帐号
     * @param money   :转账金额
     * */
	public void transfer(String out,String in,Double money);
}
