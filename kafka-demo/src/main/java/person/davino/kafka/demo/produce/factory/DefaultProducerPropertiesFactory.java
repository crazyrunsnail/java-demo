package person.davino.kafka.demo.produce.factory;

import java.util.Properties;

public class DefaultProducerPropertiesFactory {
    private static Properties properties;

    private DefaultProducerPropertiesFactory() {}

    public static Properties getProperties() {
        if (properties == null) {
            properties = new Properties();
            properties.put("bootstrap.servers", "localhost:32769");
            properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
            properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

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
