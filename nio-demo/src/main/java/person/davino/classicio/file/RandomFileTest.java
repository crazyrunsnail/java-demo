package person.davino.classicio.file;

import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Description
 * <p>
 * Writed by davino
 * Created on 05/03/2018
 */
public class RandomFileTest {

    public static void main(String[] args) throws IOException {

        /** mode: r rw rws rwd
         * rws, rwd 会自动创建文件, 同时每一次写入会同步到存储, 应用于对存储比较高的情况
         * r: FileNotFoundException, 文件不存在
         * rw: FieeNotFoundException, 文件不存在或者是Read-only
         */
        RandomAccessFile randomAccessFile = new RandomAccessFile("hello.txt", "rw");
        System.out.println(randomAccessFile.length());

        // 移动到文件最后, randomAccessFile的特征
        randomAccessFile.seek(randomAccessFile.length());

        String content = "123123123";
        randomAccessFile.write(content.getBytes());

        // write之后, 马上同步到store devices.
        FileDescriptor fd = randomAccessFile.getFD();
        fd.sync();

        randomAccessFile.close();

    }
}
