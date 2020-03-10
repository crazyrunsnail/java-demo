package personal.davino.disruptor.demo;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.dsl.Disruptor;
import personal.davino.disruptor.demo.event.Trade;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MutilConsumes {


    public static void main(String[] args) throws InterruptedException {
        // Executor that will be used to construct new threads for consumers
        ExecutorService executor = Executors.newCachedThreadPool();

        // Specify the size of the ring buffer, must be power of 2.
        int bufferSize = 1024;
        // Construct the Disruptor
        Disruptor<Trade> disruptor = new Disruptor<Trade>(Trade::new, bufferSize, executor);
        // Connect the handler
        disruptor.handleEventsWith(new EventHandler<Trade>() {
            @Override
            public void onEvent(Trade trade, long l, boolean b) throws Exception {
                System.out.println("handle first: " + trade.getPrice());
            }
        }, new EventHandler<Trade>() {
            @Override
            public void onEvent(Trade trade, long l, boolean b) throws Exception {
                System.out.println("handle two: " + trade.getPrice());
            }
        }).then(new EventHandler<Trade>() {
            @Override
            public void onEvent(Trade trade, long l, boolean b) throws Exception {
                System.out.println("after one and two.:" + trade.getPrice());
            }
        });


        // Start the Disruptor, starts all threads running
        disruptor.start();

        CountDownLatch latch = new CountDownLatch(1);

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                disruptor.getRingBuffer().publishEvent(new EventTranslator(), new Double(i));
            }
            latch.countDown();
        }).start();

        latch.await();

        disruptor.shutdown();
        executor.shutdown();


    }

    private static class EventTranslator implements EventTranslatorOneArg<Trade, Double> {


        @Override
        public void translateTo(Trade trade, long l, Double aDouble) {
            trade.setId(UUID.randomUUID().toString());
            trade.setPrice(aDouble);
        }
    }

}
