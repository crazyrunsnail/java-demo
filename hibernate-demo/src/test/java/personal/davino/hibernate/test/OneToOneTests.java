package personal.davino.hibernate.test;

import org.hibernate.Session;
import org.junit.Test;
import person.davino.hibernate.demo.entity.PassportEntity;
import person.davino.hibernate.demo.entity.UserEntity;

public class OneToOneTests  extends BaseHibernateTest{
    @Test
    public void test() {
        Session session = getSession();

        UserEntity userEntity = new UserEntity();
        userEntity.setName("Carin");
        userEntity.setAge(20);

        PassportEntity passportEntity = new PassportEntity();
        passportEntity.setSerial("PCN20181109");
        passportEntity.setExpiry(20190801L);
        passportEntity.setUser(userEntity);
        userEntity.setPassport(passportEntity);
        session.getTransaction().begin();
        session.save(userEntity);
        session.getTransaction().commit();

    }

    @Test
    public void t() {
        Session session = getSession();

        UserEntity userEntity = new UserEntity();
        userEntity.setName("Carind");
        userEntity.setAge(20);

        PassportEntity passportEntity = new PassportEntity();
        passportEntity.setSerial("PCN2018110901");
        passportEntity.setExpiry(2019080102L);
        passportEntity.setUser(userEntity);
        userEntity.setPassport(passportEntity);
        session.getTransaction().begin();
        session.save(passportEntity);
        session.getTransaction().commit();

    }
}
