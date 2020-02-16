package person.davino.scalable.reactor;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * Description
 * <p>
 * Writed by davino
 * Created on 06/03/2018
 */
final class Handler implements Runnable{

    final SocketChannel socket;
    final SelectionKey sk;
    ByteBuffer input = ByteBuffer.allocate(2048);
    ByteBuffer output = ByteBuffer.allocate(4096);
    static final int READING = 0, SENDING = 1;
    int state = READING;

    Handler(Selector sel, SocketChannel c) throws IOException {
        socket = c;
        c.configureBlocking(false);
        sk = socket.register(sel, 0);
        sk.attach(this);
        sk.interestOps(SelectionKey.OP_READ);
        sel.wakeup();
    }

    boolean inputIsComplete() {
        return true;
    }

    boolean outputIsComplete() {
        return true;
    }

    void process() {
        System.out.println("In process..");
        this.output.clear();
        this.input.rewind();
        this.output.put(this.input);
        this.output.flip();
        System.out.println("Process finished!!!");
    }

    @Override
    public void run() {
        try {
            if (state == READING) {
                read();
            }else if (state == SENDING)
                send();
        }catch (IOException ioe) {

        }
    }

    void send() throws IOException {
        System.out.println("Ready to send data....");
        socket.write(output);
//        if (outputIsComplete())
//            sk.cancel();

        System.out.println("Send data finished!");
        System.out.println("interest read..");
        state = READING;
    }

    void read() throws IOException {
        System.out.println("Ready to read data...");
        socket.read(input);
        if (inputIsComplete()) {
            process();
            state = SENDING;
            sk.interestOps(SelectionKey.OP_WRITE);
        }
        System.out.println("Read data finished!");
    }
}
