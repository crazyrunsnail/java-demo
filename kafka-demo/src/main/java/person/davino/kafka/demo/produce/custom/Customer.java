package person.davino.kafka.demo.produce.custom;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import person.davino.kafka.demo.produce.factory.DefaultCustomerPropertiesFactory;
import person.davino.kafka.demo.produce.factory.DefaultProducerPropertiesFactory;

import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Customer {

    private static Logger logger = LoggerFactory.getLogger(Customer.class);

    public static void main(String[] args) {
        Properties defaultProperties = DefaultCustomerPropertiesFactory.getProperties();
//        defaultProperties.put("fetch.max.wait.ms", "5000"); // 设置最大等等时间为5s
        defaultProperties.put("fetch.min.bytes", "1000"); // 设置最小的大小为1KB
        defaultProperties.put("max.partition.fetch.bytes", "1000"); // 针对单个partition
        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<String, String>(defaultProperties);
        kafkaConsumer.subscribe(Collections.singleton("test"));
        while (true) {
            ConsumerRecords<String, String> records = kafkaConsumer.poll(100);
            for (ConsumerRecord<String, String> record: records) {
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    System.out.println("Interrupted!");
                }
                logger.debug("topic = {}, partition = {}, offset = {}, key = {} , value ={}",
                        record.topic(), record.partition(), record.offset(), record.key(), record.value());
            }
        }
    }
}
