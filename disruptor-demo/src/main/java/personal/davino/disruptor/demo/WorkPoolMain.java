package personal.davino.disruptor.demo;

import com.lmax.disruptor.*;
import personal.davino.disruptor.demo.event.Trade;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class WorkPoolMain {

    public static void main(String[] args) {
        // ring buffer
        RingBuffer<Trade> ringBuffer = RingBuffer.createSingleProducer(new EventFactory<Trade>() {
            @Override
            public Trade newInstance() {
                return new Trade();
            }
        }, 1024, new YieldingWaitStrategy());

        // event processor
        // 最重要设置barrierSequence
        WorkerPool<Trade> workerPool = new WorkerPool<>(ringBuffer, ringBuffer.newBarrier(), new IgnoreExceptionHandler(), new WorkHandler<Trade>() {
            @Override
            public void onEvent(Trade trade) throws Exception {
                System.out.println("work pool:" + trade.getId());
            }
        });


        ExecutorService executors = Executors.newFixedThreadPool(4);

        workerPool.start(executors);


        Future<?> future = executors.submit(() -> {
            for (int i = 0; i < 10; i++) {
                long next = ringBuffer.next();
                ringBuffer.get(next).setId(UUID.randomUUID().toString());
                ringBuffer.publish(next);
            }
        });
    }
}
