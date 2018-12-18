package personal.davino.hibernate.test;

import org.hibernate.Transaction;
import org.junit.Test;
import person.davino.hibernate.demo.entity.GroupEntity;
import person.davino.hibernate.demo.entity.UserEntity;

import java.util.HashSet;
import java.util.Set;

public class OneToManyTests extends BaseHibernateTest {
    /**
     * 单向时插入三条SQL 最后一条SQL会 update t_group
     * 双向时, 并且group为主动方, 只插入两条SQL
     */
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

    @Test
    public void  query() {
        GroupEntity groupEntity = getSession().find(GroupEntity.class, 1L);
        System.out.println(groupEntity.getUser());
    }
}
