package person.davion.rocketmq.order;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Producer {
    private final static Random random = new Random(123);

    public static void main(String[] args) throws MQClientException, RemotingException,
            InterruptedException, MQBrokerException {
        DefaultMQProducer producer = new DefaultMQProducer("producer_group");
        producer.setNamesrvAddr("localhost:9876");

        producer.start();

        for (int i = 1; i <= 5; i++) {
            String msg = "msg" + i;
            Message message = new Message("test", "test", msg.getBytes());
            System.out.println("Ready to send message! : " + msg);
            SendResult sendResult = producer.send(message, new MessageQueueSelector() {
                @Override
                public MessageQueue select(List<MessageQueue> queues, Message message, Object o) {
                    Integer index = (Integer) o;
                    return queues.get(index);
                }
            }, 0);
            System.out.println(sendResult);

        }

        for (int i = 1; i <= 5; i++) {
            String msg = "msg" + i;
            Message message = new Message("test", "test2", msg.getBytes());
            System.out.println("Ready to send message! : " + msg);
            SendResult sendResult = producer.send(message, new MessageQueueSelector() {
                @Override
                public MessageQueue select(List<MessageQueue> queues, Message message, Object o) {
                    Integer index = (Integer) o;
                    return queues.get(index);
                }
            }, 1);
            System.out.println(sendResult);
        }

    }
}
