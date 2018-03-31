package person.davino.classicio.stream.pipe;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * pipedStream一般用于线程的通信中
 * 使用single thread非常危险，经常会造成deadlock.
 * <p>
 * Writed by davino
 * Created on 07/01/2018
 */
public class PipeStreamDemo {

    public static int LIMIT = 10;

    public static void main(String[] args) throws IOException {
        final PipedOutputStream pos = new PipedOutputStream();
        final PipedInputStream pis = new PipedInputStream(pos);
        Runnable senderTask = () -> {
            try {
                for (int i = 0; i < LIMIT; i++) {
                    pos.write((byte) (Math.random() * 256));
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            } finally {
                try {
                    pos.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        };

        Runnable receiverTask = () -> {
            try {
                for (int i = 0; i < LIMIT; i++) {
                    int b;
                    while ((b = pis.read()) != -1)
                        System.out.println("pis output:" + b);

                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            } finally {
                try {
                    pis.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        };

        Thread sender = new Thread(senderTask);
        Thread receiver = new Thread(receiverTask);
        sender.start();
        receiver.start();
    }
}
