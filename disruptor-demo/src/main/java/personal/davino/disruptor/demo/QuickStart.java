package personal.davino.disruptor.demo;

import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

import java.nio.ByteBuffer;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class QuickStart {

    private static class LongEvent {
        private long value;

        public long getValue() {
            return value;
        }

        public void setValue(long value) {
            this.value = value;
        }
    }


    private static class LongEventFactory implements EventFactory {

        @Override
        public Object newInstance() {
            return new LongEvent();
        }
    }

    private static class LongEventHandler implements EventHandler<LongEvent> {

        @Override
        public void onEvent(LongEvent longEvent, long l, boolean b) throws Exception {
            System.out.println("get value" + longEvent.getValue() + ", long is " + l);
        }
    }

    private static class LongEventProducer {
        private final RingBuffer<LongEvent> ringBuffer;
        public LongEventProducer(RingBuffer<LongEvent> ringBuffer) {
            this.ringBuffer = ringBuffer;
        }

        /**
         * onData用来发布事件，每调用一次就发布一次事件事件
         * 它的参数会通过事件传递给消费者
         *
         * @param bb
         */public void onData(ByteBuffer bb) {
            //可以把ringBuffer看做一个事件队列，那么next就是得到下面一个事件槽
            long sequence = ringBuffer.next();try {
                //用上面的索引取出一个空的事件用于填充
                LongEvent event = ringBuffer.get(sequence);// for the sequence
                event.setValue(bb.getLong(0));
            } finally {
                //发布事件
                ringBuffer.publish(sequence);
            }
        }
    }
    public static  class LongEventProducerWithTranslator {
        //一个translator可以看做一个事件初始化器，publicEvent方法会调用它
        //填充Event
        private static final EventTranslatorOneArg<LongEvent, ByteBuffer> TRANSLATOR =
                new EventTranslatorOneArg<LongEvent, ByteBuffer>() {
                    public void translateTo(LongEvent event, long sequence, ByteBuffer bb) {
                        event.setValue(bb.getLong(0));
                    }
                };
        private final RingBuffer<LongEvent> ringBuffer;

        public LongEventProducerWithTranslator(RingBuffer<LongEvent> ringBuffer) {
            this.ringBuffer = ringBuffer;
        }

        public void onData(ByteBuffer bb) {
            ringBuffer.publishEvent(TRANSLATOR, bb);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // Executor that will be used to construct new threads for consumers
        Executor executor = Executors.newCachedThreadPool();
        // The factory for the event
        LongEventFactory factory = new LongEventFactory();
        // Specify the size of the ring buffer, must be power of 2.
        int bufferSize = 1024;
        // Construct the Disruptor
        Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(factory, bufferSize, executor);
        // Connect the handler
        disruptor.handleEventsWith(new LongEventHandler());
        // Start the Disruptor, starts all threads running
        disruptor.start();
        // Get the ring buffer from the Disruptor to be used for publishing.
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        LongEventProducer producer = new LongEventProducer(ringBuffer);

        ByteBuffer bb = ByteBuffer.allocate(8);
        for (long l = 0; true; l++) {
            bb.putLong(0, l);
            producer.onData(bb);
            Thread.sleep(1000);
        }
    }
}

