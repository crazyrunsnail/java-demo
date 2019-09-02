package personal.davino.hibernate.test;

import org.hibernate.query.Query;
import org.junit.Test;
import person.davino.hibernate.demo.entity.UserEntity;

public class HqlTests extends BaseHibernateTest{

    @Test
    public void test1() {
        Query from_userEntity = getSession().createQuery("from UserEntity");
        from_userEntity.getResultList().forEach(System.out::println);

    }

    @Test
    public void test2() {
        Query query = getSession().createQuery("select user.name, user.age from UserEntity user");
        query.list().forEach(item -> {
            Object[] result = (Object[])item;
            System.out.println(result[0] + "," + result[1]);

        });
    }

    @Test
    public void test3() {
        Query query = getSession().createQuery("select new UserEntity(user.name, user.age) from UserEntity user");
        query.list().forEach(item -> {
            UserEntity userEntity = (UserEntity)item;
            System.out.println(((UserEntity) item).getName());
        });

    }

    // 设置参数
    @Test
    public void query1() {
        Query query = getSession().createQuery("from UserEntity where name = :name");
        query.setParameter("name", "a");
        query.list().forEach(System.out::println);
    }


}
