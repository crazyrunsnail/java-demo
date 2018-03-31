package person.davino.nio.channel.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Description
 * <p>
 * Writed by davino
 * Created on 28/02/2018
 */
public class ChannelClientDemo {

    public static void main(String[] args)  {

        try {

            SocketChannel sc = SocketChannel.open();

            sc.configureBlocking(false);

            InetSocketAddress address = new InetSocketAddress("localhost", 9999);

            sc.connect(address);

            while (!sc.finishConnect()) {
                System.out.println("waiting to finish connection");
            }

            ByteBuffer buffer = ByteBuffer.allocateDirect(200);

            while (sc.read(buffer) >= 0) {
                buffer.flip();

                while (buffer.hasRemaining())
                    System.out.print((char) buffer.get());

                buffer.clear();
            }

            sc.close();
        }catch (IOException ioe) {
            System.err.println("I/O error: " + ioe.getMessage());
        }

    }

}
