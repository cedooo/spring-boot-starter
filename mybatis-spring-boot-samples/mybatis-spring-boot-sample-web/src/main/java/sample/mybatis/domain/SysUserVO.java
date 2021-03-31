package sample.mybatis.domain;

import sample.mybatis.helper.encrypt.EncryptDecryptClass;
import sample.mybatis.helper.encrypt.EncryptDecryptField;
import sample.mybatis.helper.sensitive.Sensitive;
import sample.mybatis.helper.sensitive.SensitiveStrategy;

@EncryptDecryptClass
public class SysUserVO {

    private String id;

    //@Sensitive(strategy = SensitiveStrategy.PHONE)
    @EncryptDecryptField
    private String phone;

    private String name;

    //@Sensitive(strategy = SensitiveStrategy.ID_CARD)
    @EncryptDecryptField
    private String cardId;

    @Sensitive(strategy = SensitiveStrategy.ADDRESS_CN)
    private String address;

    private Integer sex;

    @EncryptDecryptField
    private String passwd;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
}