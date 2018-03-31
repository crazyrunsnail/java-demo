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

    Handler(Selector sel, SocketChannel c) throws ClosedChannelException {
        socket = c;
        sk = socket.register(sel, SelectionKey.OP_READ);
        sk.attach(this);
        sk.interestOps(SelectionKey.OP_ACCEPT);
        sel.wakeup();
    }

    boolean inputIsComplete() {
        return true;
    }

    boolean outputIsComplete() {
        return false;
    }

    void process() {

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
        socket.read(input);
        if (inputIsComplete()) {
            process();
            state = SENDING;
            sk.interestOps(SelectionKey.OP_WRITE);
        }
    }

    void read() throws IOException {
        socket.write(output);
        if (outputIsComplete()) sk.cancel();
    }
}
