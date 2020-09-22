package person.davino.mybatis.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.type.Alias;
import person.davino.mybatis.demo.model.User;

@Mapper
public interface UserMapper {

    User select(@Param("dto") User user);

}
