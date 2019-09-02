package person.davino.hibernate.demo.entity;

import java.io.Serializable;

public class ItemEntity implements Serializable {
    private static final long serialVersionUID = 2601332040356587994L;
    private long id;
    private String name;
    private String catagory;
    private String manufacturer;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCatagory() {
        return catagory;
    }

    public void setCatagory(String catagory) {
        this.catagory = catagory;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
}
