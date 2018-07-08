package person.davino.kafka.demo.produce.partitioner;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.record.InvalidRecordException;
import org.apache.kafka.common.utils.Utils;

import java.util.List;
import java.util.Map;

public class BananaPartitioner implements Partitioner{
    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        List<PartitionInfo> partitionInfos = cluster.partitionsForTopic(topic);
        int numPartitions = partitionInfos.size();
        if (keyBytes == null || (!(key instanceof String))) {
            throw new InvalidRecordException("We except all messages to have customer name as key.");
        }

        // 总是最后一个分区.
        if (((String)key).equals("Banana")) {
            return numPartitions;
        }

        return (Math.abs(Utils.murmur2(keyBytes) % (numPartitions -1)));
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}
