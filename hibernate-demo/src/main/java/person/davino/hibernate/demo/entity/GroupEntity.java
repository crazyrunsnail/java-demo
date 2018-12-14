package person.davino.hibernate.demo.entity;

import java.io.Serializable;

public class GroupEntity implements Serializable {
    private static final long serialVersionUID = -428514581779673741L;

    private Long id;

    private String name;

    private UserEntity user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
