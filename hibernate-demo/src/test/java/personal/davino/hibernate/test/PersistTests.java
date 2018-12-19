package personal.davino.hibernate.test;

import org.hibernate.Transaction;
import org.junit.Test;
import person.davino.hibernate.demo.entity.UserEntity;

public class PersistTests extends BaseHibernateTest {
    @Test
    public void update() {
        Transaction transaction = getSession().beginTransaction();
        UserEntity userEntity = getSession().get(UserEntity.class, 2L);
        if (userEntity != null) {
            System.out.println(userEntity.getFullName());
            userEntity.setName("update2");
            userEntity.setAge(109);
        }
        transaction.commit();
    }
}
