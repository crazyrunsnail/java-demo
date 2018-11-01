package person.davino.hibernate.demo.bean;

import java.io.Serializable;

public class Contact implements Serializable {
    private static final long serialVersionUID = 3801971955111785971L;
    private String address;
    private String zipcode;
    private String tel;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
