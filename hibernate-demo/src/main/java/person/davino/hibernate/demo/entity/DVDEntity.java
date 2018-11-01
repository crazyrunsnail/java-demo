package person.davino.hibernate.demo.entity;

public class DVDEntity extends ItemEntity {
    private static final long serialVersionUID = 3015790070936923359L;
    private String regioncode;

    public String getRegioncode() {
        return regioncode;
    }

    public void setRegioncode(String regioncode) {
        this.regioncode = regioncode;
    }
}
