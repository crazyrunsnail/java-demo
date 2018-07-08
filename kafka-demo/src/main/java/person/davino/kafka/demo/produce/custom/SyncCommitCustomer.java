package person.davino.kafka.demo.produce.custom;

import org.apache.kafka.clients.consumer.CommitFailedException;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import person.davino.kafka.demo.produce.factory.DefaultCustomerPropertiesFactory;

import java.util.Collections;
import java.util.Properties;

public class SyncCommitCustomer {

    private static Logger logger = LoggerFactory.getLogger(SyncCommitCustomer.class);

    public static void main(String[] args) {
        Properties defaultProperties = DefaultCustomerPropertiesFactory.getProperties();
        defaultProperties.put("enable.auto.commit", "false"); // 设置成不自动提交
        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<String, String>(defaultProperties);
        kafkaConsumer.subscribe(Collections.singleton("topic"));
        while (true) {
            ConsumerRecords<String, String> records = kafkaConsumer.poll(100);
            for (ConsumerRecord<String, String> record: records) {
                logger.debug("topic = {}, partition = {}, offset = {}, key = {} , value ={}",
                        record.topic(), record.partition(), record.offset(), record.key(), record.value());
                try {
                    kafkaConsumer.commitSync();
                    logger.debug("commit success");
                } catch (CommitFailedException ex) {
                    logger.error("commit failed, {}", ex);
                }
            }

        }
    }
}
