package person.davino.nio2.path;

import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class PathDemo {

    public static void main(String[] args) {
        FileSystem defaultFs = FileSystems.getDefault();
        Path path = defaultFs.getPath("x", "y", "dir");
//        Path path1 = defaultFs.getPath("x/y"); // 同上
        System.out.println(path);
        System.out.printf("File name: %s%n", path.getFileName());
        for (int i = 0; i < path.getNameCount(); i++) {
            System.out.println(path.getName(i));
        }
        System.out.printf("Parent %s%n", path.getParent());
        System.out.printf("Root %s%n", path.getRoot());
        System.out.printf("SubPath [0, 2): %s%n", path.subpath(0, 2));
    }
}
