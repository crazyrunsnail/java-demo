package person.davino.basic.concurrency.delay;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayedTask implements Delayed, Runnable {
    private static int counter = 0;
    private final int id = counter++;
    private final int delta;
    private final long trigger;
    protected static List<DelayedTask> sequence = new ArrayList<DelayedTask>();

    public DelayedTask(int delayInMilliseconds) {
        delta = delayInMilliseconds;
        trigger = System.nanoTime() + TimeUnit.NANOSECONDS.convert(delta, TimeUnit.MILLISECONDS);
        sequence.add(this);
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(trigger - System.nanoTime(), TimeUnit.NANOSECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        if (o == null)
            return -1;
        DelayedTask other = (DelayedTask)o;
        if (other.getTrigger() > this.getTrigger()) {
            return -1;
        } else if (other.getTrigger() < this.getTrigger()) {
           return 1;
        }
        return 0;
    }

    @Override
    public void run() {

    }

    public long getTrigger() {
        return trigger;
    }
}
