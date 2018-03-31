package person.davino.nio.channel.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.TimeUnit;

/**
 * Description
 * <p>
 * Writed by davino
 * Created on 28/02/2018
 */
public class ChannelServerDemo {

    public static void main(String[] args) {
        System.out.println("Start server....");
        ServerSocketChannel ssc = null;
        try {
            ssc = ServerSocketChannel.open();

            // 使用bind来监听端口
            ssc.socket().bind(new InetSocketAddress(9999));

            // 设置成为不阻塞的
            ssc.configureBlocking(false);

            String msg = "Local address:" + ssc.socket().getLocalSocketAddress();

            ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());

            int i = 1;

            for(;;){
                if ( i > 3 )
                    i = 0 ;

                for (int j = 0 ; j < i ; j++)
                    System.out.print(".");
                System.out.println();

                // 如果ssc nonblocking 即立即返回
                SocketChannel sc = ssc.accept();

                if (sc != null) {
                    System.out.println();
                    System.out.println("Received connection from " + sc.socket().getRemoteSocketAddress());
                    buffer.rewind();
                    sc.write(buffer);
                    sc.close();

                }else {
                    TimeUnit.SECONDS.sleep(1);
                }

                i++;
            }

        } catch (IOException e) {
            System.out.println("socket open exception!!!" + e.getMessage());
        } catch (InterruptedException ie) {
            System.out.println("InterruptedException....");
            assert false;
        }


    }
}
