package person.davino.hibernate.demo.entity;

import java.io.Serializable;

public class PassportEntity implements Serializable {
    private static final long serialVersionUID = -4392832533614623925L;
    private Long id;
    private String serial;
    private Long expiry;
    private UserEntity user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public Long getExpiry() {
        return expiry;
    }

    public void setExpiry(Long expiry) {
        this.expiry = expiry;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
