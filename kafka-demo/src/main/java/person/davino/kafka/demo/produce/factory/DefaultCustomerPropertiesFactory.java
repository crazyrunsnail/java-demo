package person.davino.kafka.demo.produce.factory;

import java.util.Properties;

public class DefaultCustomerPropertiesFactory {
    private static Properties properties;

    private DefaultCustomerPropertiesFactory() {}

    public static Properties getProperties() {
        if (properties == null) {
            properties = new Properties();
            properties.put("bootstrap.servers", "localhost:9092");
            properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
            properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
            properties.put("group.id", "G1");
        }
        return properties;
    }

    public static String getDefaultBootstrapServers() {
        return "localhost:32769";
    }

    public static String getDefaultKeySerializer() {
        return "org.apache.kafka.common.serialization.StringSerializer";
    }

    public static String getDefaultValueSerializer() {
        return "org.apache.kafka.common.serialization.StringSerializer";
    }
}
