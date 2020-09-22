package person.davino.mybatis.demo;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.SqlSessionManager;
import person.davino.mybatis.demo.mapper.UserMapper;
import person.davino.mybatis.demo.model.User;

import java.io.IOException;
import java.io.InputStream;

public class QuickStarter {
    public static void main(String[] args) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();
        try {
            User user1 = (User)session.selectOne("person.davino.mybatis.demo.mapper.UserMapper.select", 1);
            System.out.println(user1);
            UserMapper userMapper = session.getMapper(UserMapper.class);
            User dto = new User();
            dto.setId(1L);
            User user = userMapper.select(dto);
            System.out.println(user);
        } finally {
            session.close();
        }
    }
}
