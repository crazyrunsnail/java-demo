package person.davino.classicio.file;

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
        RandomAccessFile randomAccessFile = new RandomAccessFile("hello.txt", "rw");
        System.out.println(randomAccessFile.length());

        // 移动到文件最后, randomAccessFile的特征
        randomAccessFile.seek(randomAccessFile.length());

        String content = "123123123";
        randomAccessFile.write(content.getBytes());
        randomAccessFile.close();

    }
}
