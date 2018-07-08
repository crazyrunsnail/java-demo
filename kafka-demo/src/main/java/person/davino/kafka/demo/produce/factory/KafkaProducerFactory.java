package person.davino.kafka.demo.produce.factory;

import org.apache.kafka.clients.producer.Producer;

import java.util.Properties;

public interface KafkaProducerFactory {
    Producer createProducer();
    Properties getProducerProperties();
}
