package person.davino.netty.demo.echo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.TimeUnit;

/**
 * Description
 * <p>
 * Writed by davino
 * Created on 22/03/2018
 */
public class EchoClient implements Runnable {

    private final SocketChannel sc;

    public EchoClient(SocketChannel sc) {
        this.sc = sc;
    }


    public static void main(String[] args) throws IOException {

        ByteBuffer byteBuffer = ByteBuffer.allocate(100);

        SocketChannel sc = SocketChannel.open();

        sc.configureBlocking(false);

        sc.connect(new InetSocketAddress(8989));

        if (!sc.finishConnect())
            System.out.println("waiting connect....");

        new Thread(new EchoClient(sc)).start();


        int i = 0;

        for (; ; ) {
            if (i >= 10) break;
            while (sc.read(byteBuffer) != -1) {

                byteBuffer.flip();

                while (byteBuffer.hasRemaining()) {
                    System.out.println((char) byteBuffer.get());
                }

                byteBuffer.clear();

                i++;
            }

        }

        sc.close();


    }

    @Override
    public void run() {

        while (!Thread.interrupted()) {
            try {
                if (sc.isOpen()) {
                    sc.write(ByteBuffer.wrap("hello".getBytes()));
                    TimeUnit.SECONDS.sleep(3);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
