package entity;

/**
 * Created by huangfugui on 2016/10/20.
 */
public class Accounts {

    private int accountId;
    private String account;
    private String password;

    public Accounts(int accountId, String account) {
        this.accountId = accountId;
        this.account = account;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Accounts{" +
                "accountId=" + accountId +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
