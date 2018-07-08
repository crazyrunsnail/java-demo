package person.davino.kafka.demo.produce;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import person.davino.kafka.demo.produce.factory.DefaultProducerFactory;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Producer {

    private static Logger logger = LoggerFactory.getLogger(Producer.class);
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        org.apache.kafka.clients.producer.Producer producer = DefaultProducerFactory.getProducer();

        ProducerRecord<String, String> producerRecord = new ProducerRecord<>("topic", "Message From Java Client and with callback3");
        Future<RecordMetadata> send = producer.send(producerRecord, new ProducerCallback());

        TimeUnit.SECONDS.sleep(1);
    }

    private static class ProducerCallback implements Callback {

        @Override
        public void onCompletion(RecordMetadata recordMetadata, Exception e) {
            if (e != null) {
                System.out.println(e);
            }

            logger.debug("Send success! topic = {}, partition = {}, offset = {}, timestamp ={}", recordMetadata.topic(),
                    recordMetadata.partition(),
                    recordMetadata.offset(),
                    recordMetadata.timestamp());
        }
    }
}
