package personal.davino.vertxdemo.entity;


import java.time.LocalDateTime;

public class Order {
  private Long id;
  private LocalDateTime createTeim;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public LocalDateTime getCreateTeim() {
    return createTeim;
  }

  public void setCreateTeim(LocalDateTime createTeim) {
    this.createTeim = createTeim;
  }
}
