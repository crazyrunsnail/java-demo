package personal.davino.hibernate.test;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.query.criteria.internal.CriteriaQueryImpl;
import org.junit.Assert;
import org.junit.Test;
import person.davino.hibernate.demo.entity.UserEntity;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import java.util.List;

public class QuaikTest extends BaseHibernateTest{



    @Test
    public void select() {
        Session session = getSession();
        UserEntity userEntity = session.get(UserEntity.class, 1L);
        Assert.assertNotNull(userEntity);
    }

    @Test
    public void delete() {
        Session session = getSession();
        session.beginTransaction();
        UserEntity userEntity = session.get(UserEntity.class, 1L);
        session.delete(userEntity);
        session.getTransaction().commit();
    }

    @Test
    public void save() {
        UserEntity userEntity = new UserEntity();
        userEntity.setName("Insert By Hibernate");
        userEntity.setAge(10);
        getSession().beginTransaction();
        getSession().save(userEntity);
        getSession().getTransaction().commit();
    }

    @Test
    public void createQuery() {
        Query<UserEntity> query = getSession().createQuery("from UserEntity WHERE name = ?", UserEntity.class);
//        Query<UserEntity> query = getSession().createQuery("from UserEntity WHERE name = ‘hello’", UserEntity.class);
        query.setParameter(0, "hello");
        List<UserEntity> list = query.list(); // 从0开始
        list.forEach(System.out::println);
    }

    @Test
    public void newCriteria() {
        // hibernate5 为了兼容JPA可谓用心良苦
        Session session = getSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<UserEntity> query = criteriaBuilder.createQuery(UserEntity.class);
        Root<UserEntity> root = query.from(UserEntity.class);
        query.select(root);
        query.where(criteriaBuilder.equal(root.get("name"), "hello"));
        session.createQuery(query).getResultList().forEach(System.out::println);
    }

    @Test
    public void  componentTest() throws JsonProcessingException {
        UserEntity userEntity = getSession().get(UserEntity.class, 2L);
        System.out.println(JSON.toJSONString(userEntity));
    }


}
