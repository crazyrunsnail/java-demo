package person.davino.nio2.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TempFileDemo {

    public static void main(String[] args) {
        try {
            // 生成一个video开头的文件
            // 程序结束后并不会主动删除它
            Path path = Files.createTempFile(Paths.get("."), "video", null);

            Thread.currentThread().sleep(3000);
            // 删除
            path.toFile().deleteOnExit();
        } catch (IOException e) {
            System.err.printf("Create a temp file error!\t: %s", e.getMessage());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
