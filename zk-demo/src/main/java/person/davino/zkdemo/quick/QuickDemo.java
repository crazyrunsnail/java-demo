package person.davino.zkdemo.quick;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * 快速Demo
 *
 * Server地址由 <em>,</em> 分开
 *
 */
public class QuickDemo {

    private final static Logger logger = LoggerFactory.getLogger(QuickDemo.class);

    static String connectString = "127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183";
    static ZooKeeper zkClient;
    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        zkClient = new ZooKeeper(connectString, 2000, null);
//        createNode(zkClient);
        createChildNode(zkClient);
    }

    /**
     * 创建一个path, 以 <em>/</em> 开头
     * 创建后再创建会报NodeExistsException, 可以使用此原理来做排它锁
     * @param zkClient
     * @throws KeeperException
     * @throws InterruptedException
     */
    public static void createNode(ZooKeeper zkClient) throws KeeperException, InterruptedException {
        String answer = zkClient.create("/davino_test", "firstpath".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.PERSISTENT);
        logger.info("创建成功, 返回结果: {}", answer);
    }

    /**
     * 创建一个child path, 父path一定要先存在
     * @param zkClient
     * @throws KeeperException
     * @throws InterruptedException
     */
    public static void createChildNode(ZooKeeper zkClient) throws KeeperException, InterruptedException {
        String answer = zkClient.create("/davino_test/first_node", "firstpath".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.PERSISTENT);
        logger.info("创建成功, 返回结果: {}", answer);
    }
}
