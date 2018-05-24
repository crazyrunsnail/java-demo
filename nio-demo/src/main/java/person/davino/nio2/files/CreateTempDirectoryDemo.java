package person.davino.nio2.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CreateTempDirectoryDemo {
    private final static Path path = Paths.get("x");

    public static void main(String[] args) throws IOException, InterruptedException {
        // java tmpdir 目录
        // /var/folders/8x/1w1k9t0x1pg5rd8d23bk6q_h0000gn/T/
        System.out.println(System.getProperty("java.io.tmpdir"));
        Path tmpPath = Files.createTempDirectory(path, "images");

        Thread.sleep(10000);

        // 一种很优雅的方式来删除临时文件
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                System.out.println("....");
                Files.delete(tmpPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
    }

}
