package person.davino.nio2.path;

import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AbsoluteReletivePathDemo {

    public static void main(String[] args) {
        FileSystem defaultFs = FileSystems.getDefault();
        Path path = defaultFs.getPath("a", "b", "c");
        System.out.println(path);
        System.out.printf("Absolute: %b%n", path.isAbsolute());
        System.out.printf("Root %s%n", path.getRoot()); // 返回null

        // getRootDirectories 返回Root列表
        defaultFs.getRootDirectories().forEach((Path root) -> {
            Path path1 = Paths.get(root.toString(), "a", "b", "c");
            System.out.println(path1);
            System.out.printf("Absolute: %b%n", path1.isAbsolute()); // true
            System.out.printf("Root: %s%n", path1.getRoot()); // Root: /
        });
    }
}
