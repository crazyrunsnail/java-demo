package person.davino.nio.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Date;

/**
 * Description
 * <p>
 * Writed by davino
 * Created on 28/02/2018
 */
public class SelectorClientDemo {

    public static void main(String[] args) throws IOException {

        ByteBuffer bb = ByteBuffer.allocateDirect(8);

        SocketChannel sc = SocketChannel.open();

        sc.connect(new InetSocketAddress(9999));

        long time = 0;

        while (sc.read(bb) != -1) {
            bb.flip();

            while (bb.hasRemaining()) {
                time <<= 8;
                time |= bb.get() & 0xFF;
            }

            bb.clear();
        }
        System.out.println(new Date(time));

        sc.close();

    }
}
