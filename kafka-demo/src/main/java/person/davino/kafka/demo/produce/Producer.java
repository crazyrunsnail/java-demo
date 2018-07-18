package person.davino.kafka.demo.produce;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import person.davino.kafka.demo.produce.factory.DefaultProducerFactory;
import person.davino.kafka.demo.produce.factory.KafkaProducerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Producer {

    private static Logger logger = LoggerFactory.getLogger(Producer.class);
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Map<String, String> config = new HashMap<>();
//         非常重要的参数, 决定了kafka的时延
        config.put("batch.size","90");
        config.put("linger.ms", "1000");
        config.put("max.request.size", "150"); // 应该大于batch.size
        config.put("max.in.flight.requests.per.connection", "1"); // 经测试, 为单次发送时的

        org.apache.kafka.clients.producer.Producer producer = DefaultProducerFactory.getProducer(config);

        ProducerRecord<String, String> producerRecord =
                new ProducerRecord<>("test", "Message From Java Client and with callback3");
        for (int i = 0; i < 10; i++) {
            producerRecord =
                    new ProducerRecord<>("test", "Message From Java Client and with callback" + "[" + i + "]");
            Future<RecordMetadata> send = producer.send(producerRecord, new ProducerCallback());
        }
//        Future<RecordMetadata> send = producer.send(producerRecord, new ProducerCallback());

        TimeUnit.SECONDS.sleep(13);
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
