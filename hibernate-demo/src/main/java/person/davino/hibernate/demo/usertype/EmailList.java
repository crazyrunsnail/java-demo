package person.davino.hibernate.demo.usertype;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class EmailList implements UserType, Serializable{

    private List<String> list;

    private static final long serialVersionUID = -1555962918840411479L;

    @Override
    public int[] sqlTypes() {
        return new int[] { Types.VARCHAR };
    }

    @Override
    public Class returnedClass() {
        return List.class;
    }

    @Override
    public boolean equals(Object x, Object y) throws HibernateException {
        if (x == y) return true;
        if (x != null && y != null) {
            List xList = (List) x;
            List yList = (List) y;
            if (xList.size() != yList.size()) return false;
            for (int i = 0; i < xList.size(); i++){
                String st1 = (String)xList.get(i);
                String st2 = (String)yList.get(i);
                if (!st1.equals(st2)) return false;
            }
            return true;
        }
        return false;
    }

    @Override
    public int hashCode(Object x) throws HibernateException {
        return Objects.hashCode(x);
    }

    @Override
    public Object nullSafeGet(ResultSet rs, String[] names, SharedSessionContractImplementor session, Object owner) throws HibernateException, SQLException {
        String value = rs.getString(names[0]);
        if (value != null && value.length() > 0) {
            return Arrays.asList(value.split(";"));
        }
        return new ArrayList<String>();
    }

    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index, SharedSessionContractImplementor session) throws HibernateException, SQLException {

    }

    @SuppressWarnings("unchecked")
    @Override
    public Object deepCopy(Object value) throws HibernateException {
        List<String> value1 = (List<String>) value;
        return new ArrayList<>(value1);
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public Serializable disassemble(Object value) throws HibernateException {
        return null;
    }

    @Override
    public Object assemble(Serializable cached, Object owner) throws HibernateException {
        return null;
    }

    @Override
    public Object replace(Object original, Object target, Object owner) throws HibernateException {
        return null;
    }
}
