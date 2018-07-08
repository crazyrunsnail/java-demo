package person.davino.kafka.demo.produce.factory;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;

import java.util.Properties;

public class DefaultProducerFactory  {

    private static Producer producer;

    private DefaultProducerFactory() {

    }

    public static Producer getProducer() {
        if (producer == null) {
            producer = new KafkaProducer(getDefaultProperties());
        }
        return producer;
    }


    private static Properties getDefaultProperties() {
        return DefaultProducerPropertiesFactory.getProperties();
    }


}
