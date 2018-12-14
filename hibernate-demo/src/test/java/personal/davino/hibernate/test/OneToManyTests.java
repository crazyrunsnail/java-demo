package personal.davino.hibernate.test;

import org.hibernate.Transaction;
import org.junit.Test;
import person.davino.hibernate.demo.entity.GroupEntity;
import person.davino.hibernate.demo.entity.UserEntity;

import java.util.HashSet;
import java.util.Set;

public class OneToManyTests extends BaseHibernateTest {
    @Test
    public void save() {
        UserEntity userEntity = new UserEntity();
        userEntity.setName("123");
        GroupEntity groupEntity = new GroupEntity();
        groupEntity.setName("group1");
        Set<GroupEntity> groupEntitySet = new HashSet<>();
        groupEntitySet.add(groupEntity);
        userEntity.setGroups(groupEntitySet);
        Transaction transaction = getSession().beginTransaction();
        getSession().save(userEntity);
        transaction.commit();
    }
}
