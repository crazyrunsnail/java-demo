package personal.davino.hibernate.test;

import org.hibernate.Session;
import org.hibernate.Transaction;
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
//        session.save(passportEntity);
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

    @Test
    public void  biDirectOne2One() {
        PassportEntity load = getSession().load(PassportEntity.class, 26L);
        System.out.println(load.getUser());
    }

    @Test
    public void biDirectOne2One2() {
        UserEntity load = getSession().load(UserEntity.class, 29L);
        System.out.println(load.getPassport());
    }

    @Test
    public void biDirectSave() {
        PassportEntity passportEntity = new PassportEntity();
        passportEntity.setSerial("Seraial");
        passportEntity.setExpiry(123124L);
        Transaction transaction = getSession().getTransaction();
        transaction.begin();
        UserEntity userEntity = getSession().get(UserEntity.class, 9L);
        passportEntity.setUser(userEntity);
        userEntity.setPassport(passportEntity);
        getSession().save(passportEntity);
        transaction.commit();
    }
}
