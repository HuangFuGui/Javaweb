package entity;

/**
 * Created by huangfugui on 2016/10/20.
 */
public class Contacts {

    private int contactId;
    private String headImg;
    private String contactName;
    private String contactPhone;
    private String contactAddress;

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactAddress() {
        return contactAddress;
    }

    public void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress;
    }

    @Override
    public String toString() {
        return "Contacts{" +
                "contactId=" + contactId +
                ", headImg='" + headImg + '\'' +
                ", contactName='" + contactName + '\'' +
                ", contactPhone='" + contactPhone + '\'' +
                ", contactAddress='" + contactAddress + '\'' +
                '}';
    }
}
