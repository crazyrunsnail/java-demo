package person.davino.scalable.reactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.*;
import java.nio.channels.spi.SelectorProvider;
import java.util.Iterator;
import java.util.Set;

/**
 * Description
 * <p>
 * Writed by davino
 * Created on 06/03/2018
 */
public class Reactor implements Runnable{

    final Selector selector;

    final ServerSocketChannel serverSocketChannel;

    Reactor(int port) throws IOException {
        selector = Selector.open();

        serverSocketChannel = ServerSocketChannel.open();

        serverSocketChannel.bind(new InetSocketAddress(port));

        serverSocketChannel.configureBlocking(false);

        SelectionKey sk = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        sk.attach(new Acceptor());

        // 下面的方式也是可以, 而且更加简洁

        /*SelectorProvider p = SelectorProvider.provider();

        selector = p.openSelector();

        serverSocketChannel = p.openServerSocketChannel();*/

    }


    @Override
    public void run() { // 一般来讲是新的进程
        try {
            while (!Thread.interrupted()) {

                selector.select(); // 阻塞到有数据来

                Set selected = selector.selectedKeys();
                Iterator iterator = selected.iterator();

                while (iterator.hasNext()) {
                    SelectionKey selectionKey = (SelectionKey)iterator.next();
                    dispath(selectionKey);
                    selected.clear();   //事实上这里clear是因为只有一个SelectKey
                }
            }
        }catch (IOException ioe) {

        }
    }

    // 分发器
    public void dispath(SelectionKey k) {
        Runnable r = (Runnable)k.attachment(); // attchment 为 accepter
        if (r!=null)
            r.run();
    }

    class Acceptor implements Runnable{

        @Override
        public void run() {
            SocketChannel c = null;
            try {
                c = serverSocketChannel.accept();
                if (c!=null) {
                    new Handler(selector, c);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }
}
