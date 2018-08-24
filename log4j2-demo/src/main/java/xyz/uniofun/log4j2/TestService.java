package xyz.uniofun.log4j2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.Random;

public class TestService {

    private Logger logger = LogManager.getLogger();

    private String[] messages = new String[] {
            "Hello, world!",
            "Goodbye Cruel World",
            "Three hello world!"
    };

    private Random random = new Random(1);

    public String[] getMessages() {
        logger.traceEntry();
        return logger.traceExit(messages);

    }

    public void setMessages(String[] messages) {
        logger.traceEntry();
        this.messages = messages;
        logger.traceExit("message: {}", Arrays.toString(messages));
    }

    public String retrieveMessage() {
        logger.traceEntry();

        String message = getMessage(getKey());

        return logger.traceExit("message: {}", message);

    }

    public String getMessage(int key) {
        logger.traceEntry();
        String value = messages[key];
        return logger.traceExit(value);
    }


    public int getKey() {
        logger.traceEntry();
        int key = random.nextInt(messages.length);
        return logger.traceExit(key);
    }

    public void exampleException() {
        logger.traceEntry();
        try {
            String msg = messages[messages.length];
            logger.error("An exception should have been thrown");
        } catch (Exception ex) {
            logger.catching(ex);
        }
        logger.traceExit();
    }

    public static void main(String[] args) {
        TestService service = new TestService();
        service.retrieveMessage();
        service.retrieveMessage();
        service.exampleException();
    }
}
