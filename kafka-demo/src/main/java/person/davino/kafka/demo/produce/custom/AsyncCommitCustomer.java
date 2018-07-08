package person.davino.kafka.demo.produce.custom;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import person.davino.kafka.demo.produce.factory.DefaultCustomerPropertiesFactory;

import java.util.Collections;
import java.util.Map;
import java.util.Properties;

public class AsyncCommitCustomer {

    private static Logger logger = LoggerFactory.getLogger(AsyncCommitCustomer.class);

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
                kafkaConsumer.commitAsync(new OffsetCommitCallback() {
                    @Override
                    public void onComplete(Map<TopicPartition, OffsetAndMetadata> map, Exception e) {
                        if (e !=null) {
                            logger.error("Commit failed for offsets {}", map, e);
                        }
                        logger.debug("Receive commit callback success! offsets {}", map);
                    }
                });
            }
        }

    }
}
