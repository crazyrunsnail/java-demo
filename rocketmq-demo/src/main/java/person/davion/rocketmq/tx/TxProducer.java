package person.davion.rocketmq.tx;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.concurrent.TimeUnit;

public class TxProducer {

    public static void main(String[] args) {
        TransactionMQProducer producer = new TransactionMQProducer("tx_pgroup");
        producer.setNamesrvAddr("localhost:9876");

        producer.setTransactionListener(new TransactionListener() {
            @Override
            public LocalTransactionState executeLocalTransaction(Message message, Object o) {
                System.out.println("Rocket transaction callback");
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Send Commit Message");
                return LocalTransactionState.COMMIT_MESSAGE;
            }

            @Override
            public LocalTransactionState checkLocalTransaction(MessageExt messageExt) {
                System.out.println("Rocket check!");
                return LocalTransactionState.COMMIT_MESSAGE;
            }
        });

        Message message = new Message("txTopic", "tx", "tx".getBytes());

        try {
            producer.start();
            producer.sendMessageInTransaction(message, "tx");
        } catch (MQClientException e) {
            e.printStackTrace();
        }

        System.out.println("Send tx message success!");

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Shutdown producer");
            producer.shutdown();
        }));




    }
}
