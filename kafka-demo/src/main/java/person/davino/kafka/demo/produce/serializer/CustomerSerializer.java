package person.davino.kafka.demo.produce.serializer;

import org.apache.kafka.common.serialization.Serializer;
import person.davino.kafka.demo.produce.bean.Customer;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class CustomerSerializer implements Serializer<Customer>{

    @Override
    public void configure(Map<String, ?> map, boolean b) {

    }

    @Override
    public byte[] serialize(String topic, Customer data) {

        byte[] serializedName;
        int stringsize;

        if (data == null) return  null;

        if (data.getCustomerName() != null) {
            serializedName = data.getCustomerName().getBytes(StandardCharsets.UTF_8);
        } else  {
            serializedName = new byte[0];
        }
        stringsize = serializedName.length;
        ByteBuffer byteBuffer = ByteBuffer.allocate(4 + 4 + stringsize);
        byteBuffer.putInt(data.getCustomerID());
        byteBuffer.putInt(stringsize);
        byteBuffer.put(serializedName);

        return byteBuffer.array();
    }

    @Override
    public void close() {

    }
}
