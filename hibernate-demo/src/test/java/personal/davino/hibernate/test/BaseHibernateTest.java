package personal.davino.hibernate.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

public class BaseHibernateTest {

    private static SessionFactory  sessionFactory;

    private Session session;

    @BeforeClass
    public static void setup() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    @AfterClass
    public static void teardown() {
        if (sessionFactory.isOpen()){
            sessionFactory.close();
        }
    }

    @Before
    public void before() {
        session = sessionFactory.openSession();
    }

    @After
    public void after() {
        if (session.isOpen())
            session.close();
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
