package person.davino.kafka.demo.produce.factory;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;

import java.util.Properties;

public class ProducerFactory implements KafkaProducerFactory {

    Producer producer;

    @Override
    public Producer createProducer() {
        if (producer == null) {
            synchronized (this) {
                if (producer == null)
                    producer = new KafkaProducer(getProducerProperties());
            }
        }
        return producer;
    }

    @Override
    public Properties getProducerProperties() {
        Properties kafkaProperties = new Properties();
        kafkaProperties.put("bootstrap.servers", "localhost:32769");
        kafkaProperties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProperties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        return kafkaProperties;
    }
}
