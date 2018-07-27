package person.davino.mybatis.demo.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedJdbcTypes(JdbcType.VARCHAR)
public class ExampleTypeHandler extends BaseTypeHandler<String> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, String s, JdbcType jdbcType) throws SQLException {
        ps.setString(i, s);
    }

    @Override
    public String getNullableResult(ResultSet resultSet, String column) throws SQLException {
       return resultSet.getString(column).toUpperCase();
    }

    @Override
    public String getNullableResult(ResultSet resultSet, int i) throws SQLException {
        if (resultSet.getString(i) == null ||
                resultSet.getString(i).trim().equals("")) {
            return "NULL";
        }
        return resultSet.getString(i);
    }

    @Override
    public String getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return callableStatement.getString(i);
    }
}
