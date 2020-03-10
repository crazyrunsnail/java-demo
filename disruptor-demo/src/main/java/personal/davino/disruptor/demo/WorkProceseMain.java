package personal.davino.disruptor.demo;

import com.lmax.disruptor.*;
import personal.davino.disruptor.demo.event.Trade;

import java.util.UUID;
import java.util.concurrent.*;

public class WorkProceseMain {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // ring buffer
        RingBuffer<Trade> ringBuffer = RingBuffer.createSingleProducer(new EventFactory<Trade>() {
            @Override
            public Trade newInstance() {
                return new Trade();
            }
        }, 1024, new YieldingWaitStrategy());

        // event processor
        // 最重要设置barrierSequence
        BatchEventProcessor<Trade> eventProcessor =
                new BatchEventProcessor<>(ringBuffer, ringBuffer.newBarrier(), new EventHandler<Trade>() {
            @Override
            public void onEvent(Trade trade, long l, boolean b) throws Exception {
                System.out.println("trade: " + trade.getId());
            }
        });

        // ringbuffer中消息者和生产的通讯
        ringBuffer.addGatingSequences(eventProcessor.getSequence());

        ExecutorService executors = Executors.newFixedThreadPool(4);

        executors.submit(eventProcessor);

        Future<?> future = executors.submit(() -> {
            for (int i = 0; i < 10; i++) {
                long next = ringBuffer.next();
                ringBuffer.get(next).setId(UUID.randomUUID().toString());
                ringBuffer.publish(next);
            }
        });

        future.get();
        TimeUnit.SECONDS.sleep(2);
        eventProcessor.halt();
        executors.shutdown();

    }


}
