package person.davino.nio2.files;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class CopyFileDemo {


    private final static Path path = Paths.get("hello.txt");

    // 复制的文件名为y
//    private final static Path dst = Paths.get("x", "y");

    // 由于没有x/y目录, 所以会报FileSystemException: x/y/hello_dump.txt: Not a directory
    // 因此需要先使用 createDirectoies
    private final static Path dst = Paths.get("x", "y1", "hello_dump.txt");

    public static void main(String[] args) throws IOException {

//        Path copy = copy(path, dst);
//
//        System.out.printf("Copy file %s sucessfully! Finally Path: %s\n", path.getFileName(), copy.getFileName());

        copyUrl("https://www.baidu.com", Paths.get("x", "y1", "baidu.html"));

    }

    private static Path copy(Path src, Path dst) throws IOException {
        // 这里没有y目录, 所以需要 parent == null
        Path parent = dst.getParent();

        if (parent == null) {
            parent = dst.resolve("..");
        }
        if (Files.notExists(parent)) {
            Files.createDirectory(parent);
        }

        Path copy = Files.copy(path, dst, REPLACE_EXISTING);

        return copy;
    }

    private static void copyUrl(String url, Path dst) throws IOException {
        URL url1 = new URL(url);
        Files.copy(url1.openStream(), dst);
    }


}
