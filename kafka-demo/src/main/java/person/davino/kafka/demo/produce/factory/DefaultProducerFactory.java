package person.davino.kafka.demo.produce.factory;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;

import java.util.Map;
import java.util.Properties;

public class DefaultProducerFactory  {

    private static volatile Producer producer;

    private static Properties properties;

    private DefaultProducerFactory() {

    }

    public static Producer getProducer() {
        if (producer == null) {
            synchronized (Producer.class) {
                producer = new KafkaProducer(getDefaultProperties());
            }
        }
        return producer;
    }

    public static Producer getProducer(Map<String, String> other) {
        Producer producer;
        Properties defaultProperties = getDefaultProperties();
        Properties properties = new Properties();
        defaultProperties.forEach((k, v)-> {
            properties.put(k, v);
        });
        other.forEach((k, v) -> {
            properties.put(k, v);
        });
        return new KafkaProducer(properties);
    }


    private static Properties getDefaultProperties() {
        return DefaultProducerPropertiesFactory.getProperties();
    }


}
