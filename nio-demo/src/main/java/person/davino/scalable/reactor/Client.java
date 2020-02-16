package person.davino.scalable.reactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Client {

    public static void main(String[] args) throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.wrap("Hello Reactor".getBytes());
        ByteBuffer readbuffer = ByteBuffer.allocate(1024);

        Selector selector = Selector.open();

        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);

        socketChannel.connect(new InetSocketAddress(9098));

        socketChannel.register(selector, SelectionKey.OP_CONNECT);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (socketChannel.isOpen()) {
                try {
                    System.out.println("close socket....");
                    socketChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }));

        while (!Thread.currentThread().isInterrupted()) {
            int i = selector.select();
            if (i == 0) continue;

            System.out.println("some selection key...");
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                System.out.println("select key:" + key.interestOps());

                if (key.isConnectable()) {
                    System.out.println("isConnectable!");
                    SocketChannel sc = (SocketChannel) key.channel();
                    while (!sc.finishConnect()) {
                        continue;
                    }
                    System.out.println("connect finish!");
                    System.out.println("write and connect..");
                    sc.write(byteBuffer);
                    key.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
                }


                if (key.isReadable()) {
                    SocketChannel sc = (SocketChannel) key.channel();
                    sc.read(readbuffer);
                    readbuffer.flip();
                    String s = new String(readbuffer.array());
                    System.out.println("read from sever:" + s.trim());
                }
            }

            selectionKeys.clear();
        }



    }
}
