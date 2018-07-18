package person.davino.kafka.demo.produce.factory;

import java.io.IOException;
import java.util.Properties;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class DefaultProducerPropertiesFactory{
    private static Properties properties;

    private static ResourceBundle bundle;

    static {
        try {
            bundle = new PropertyResourceBundle(DefaultProducerPropertiesFactory.class.getClassLoader().getResourceAsStream("config/kafka-producer.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private DefaultProducerPropertiesFactory() {

    }

    public static Properties getProperties() {
        if (properties == null) {
            properties = new Properties();
            properties.put("bootstrap.servers", bundle.getString("bootstrap.servers"));
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
