package person.davino.hibernate.demo.entity;

public class BookEntity extends ItemEntity {
    private static final long serialVersionUID = 2882668926377504349L;
    private Integer pagecount;

    public Integer getPagecount() {
        return pagecount;
    }

    public void setPagecount(Integer pagecount) {
        this.pagecount = pagecount;
    }
}
