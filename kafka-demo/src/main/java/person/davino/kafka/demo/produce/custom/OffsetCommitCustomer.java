package person.davino.kafka.demo.produce.custom;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import person.davino.kafka.demo.produce.factory.DefaultCustomerPropertiesFactory;

import java.util.*;

/**
 * 消息3条提交.
 */
public class OffsetCommitCustomer {

    private static Logger logger = LoggerFactory.getLogger(OffsetCommitCustomer.class);

    private static Map<TopicPartition, OffsetAndMetadata> currentOffsets = new HashMap<>();

    private class HandleRebalance implements ConsumerRebalanceListener {

        // 消费者停止读取时间之后
        // 再均衡之前
        @Override
        public void onPartitionsRevoked(Collection<TopicPartition> collection) {

        }

        // 分配之后
        @Override
        public void onPartitionsAssigned(Collection<TopicPartition> collection) {

        }
    }

    public static void main(String[] args) {
        Properties defaultProperties = DefaultCustomerPropertiesFactory.getProperties();
        defaultProperties.put("enable.auto.commit", "false"); // 设置成不自动提交
        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<String, String>(defaultProperties);
        kafkaConsumer.subscribe(Collections.singleton("topic"));

        int count = 0;
        while (true) {
            ConsumerRecords<String, String> records = kafkaConsumer.poll(100);
            for (ConsumerRecord<String, String> record: records) {
                logger.debug("topic = {}, partition = {}, offset = {}, key = {} , value ={}",
                        record.topic(), record.partition(), record.offset(), record.key(), record.value());
                currentOffsets.put(new TopicPartition(record.topic(), record.partition()),
                        new OffsetAndMetadata(record.offset() + 1, "no metadata"));
                if (count % 3 == 0)
                    kafkaConsumer.commitAsync(currentOffsets, null);
                count ++;
            }
        }

    }
}
