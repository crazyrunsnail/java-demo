package person.davino.kafka.demo.produce.custom;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.internals.Topic;
import person.davino.kafka.demo.produce.factory.DefaultCustomerPropertiesFactory;

import java.util.*;

public class SeekOffsetConsumer {

    private static Map<TopicPartition, OffsetAndMetadata> currentOffset = new HashMap<>();

    private static class SaveOffsetsOnReblance implements ConsumerRebalanceListener{

        private Consumer cunsumer;

        public SaveOffsetsOnReblance(Consumer cunsumer) {
            this.cunsumer = cunsumer;
        }

        @Override
        public void onPartitionsRevoked(Collection<TopicPartition> collection) {
            commitDBTrancation();
        }

        @Override
        public void onPartitionsAssigned(Collection<TopicPartition> collection) {
            for (TopicPartition partition: collection) {
                cunsumer.seek(partition, getOffsetFromDB(partition)); // 移动
            }
        }
    }

    public static void main(String[] args) {
        Properties defaultProperties = DefaultCustomerPropertiesFactory.getProperties();
        defaultProperties.put("enable.auto.commit", "false"); // 设置成不自动提交
        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<String, String>(defaultProperties);
        kafkaConsumer.subscribe(Collections.singleton("topic"), new SaveOffsetsOnReblance(kafkaConsumer));

        kafkaConsumer.poll(0);

        for (TopicPartition partition: kafkaConsumer.assignment()) {
            kafkaConsumer.seek(partition, getOffsetFromDB(partition)); // 移动
        }

        while (true) {
            ConsumerRecords<String, String> records = kafkaConsumer.poll(100);
            for (ConsumerRecord record: records) {
                storeOffsetInDB(record.topic(), record.partition(), record.offset());
                currentOffset.put(new TopicPartition(record.topic(), record.partition()),
                        new OffsetAndMetadata(record.offset(), "nommetadata"));

            }

        }



    }

    private static void commitDBTrancation() {

    }

    private static int getOffsetFromDB(TopicPartition partition) {
        return 1;
    }

    private static void storeOffsetInDB(String topic, int partition, long offset) {

    }
}
