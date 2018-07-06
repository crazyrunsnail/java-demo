package eventbus;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import javax.swing.event.ChangeEvent;

public class EventBusDemo {

    public static void main(String[] args) {
        EventBusDemo eventBusDemo = new EventBusDemo();
        EventBus eventBus = new EventBus();
        eventBus.register(eventBusDemo.eventBusChangeRecorder());
        ChangeEvent changeEvent = new ChangeEvent("source");
        eventBus.post(changeEvent);
    }

    public void changeCustomer() {

    }

    public EventBusChangeRecorder eventBusChangeRecorder() {
        return new EventBusChangeRecorder();
    }

    public  class EventBusChangeRecorder {

        public @Subscribe void recordCustomerChange(ChangeEvent event) {
            System.out.println("recordCustomerChange....");
            System.out.println((String)event.getSource());
        }
    }
}
