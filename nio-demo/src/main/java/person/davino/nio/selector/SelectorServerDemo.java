package person.davino.nio.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * <i>selector</i>会维护三个Set
 * 1. key set
 *  当 channel.resgit(selector, int)会加入key set
 * 2. selected-key set
 *  当 selection时，key会被加入到selecte-key当中
 * 3. cancelled-key set
 *  调用 SelectionKey.cancel()会将key加入到cancelled-key中, selected-key需要手动remove()
 *
 * <i>SelectionKey</i>
 *  <ul>
 *      <li>interest set</li>
 *      <li>ready set</li>
 *  </ul>
 *
 * <b>selection</b>操作:
 * 1. 将cancelled-key Set中的key, 从其他Set remove掉, 并将cancelled-key set clear()
 *
 *
 *
 *
 * Writed by davino
 * Created on 28/02/2018
 */
public class SelectorServerDemo {


    private static ByteBuffer bb = ByteBuffer.allocateDirect(8);

    public static void main(String[] args) throws IOException {

        Selector selector = Selector.open();

        ServerSocketChannel ssc = ServerSocketChannel.open();

        ssc.configureBlocking(false);

        ssc.bind(new InetSocketAddress(9999));

        // 查看源码可以看到, 是将SelectionKey的interestOps设置为OP_ACCEPT
        ssc.register(selector, SelectionKey.OP_ACCEPT);

        for(;;) {
            // 此步将会阻塞....
            int n = selector.select();
            if (n == 0 ) {
                System.out.println("select return 0...");
                continue;
            }

            Iterator<SelectionKey> it = selector.selectedKeys().iterator();

            while (it.hasNext()) {
                SelectionKey key = it.next();
                if (key.isAcceptable()) {
                    SocketChannel sc = ((ServerSocketChannel)key.channel()).accept();
                    if (sc != null) {
                        bb.clear();
                        bb.putLong(System.currentTimeMillis());
                        bb.flip();

                        System.out.println("Writing current time...");
                        while (bb.hasRemaining())
                            sc.write(bb);

                        sc.close();
                    }
                }

                // 需要手动remove
                it.remove();
            }

        }

    }
}
