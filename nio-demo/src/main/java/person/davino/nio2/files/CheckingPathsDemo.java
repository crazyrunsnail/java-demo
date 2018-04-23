package person.davino.nio2.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CheckingPathsDemo {

    public static void main(String[] args) {
        Path path = Paths.get("hello.txt");

        // true
        System.out.println("File is exists:" + Files.exists(path, LinkOption.NOFOLLOW_LINKS));

        // false, 与!exists() 不同的是 notExists是atomic的
        System.out.println("File is notExists:" + Files.notExists(path, LinkOption.NOFOLLOW_LINKS));

        // false
        System.out.println("File is Diderctory:" + Files.isDirectory(path,LinkOption.NOFOLLOW_LINKS));

        // false
        System.out.println("File is executable:" + Files.isExecutable(path));

        // false
        try {
            System.out.println("File is hidden:" + Files.isHidden(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // true
        Path path1 = Paths.get(".", "hello.txt");

        try {
            System.out.println("Files is same？ " + Files.isSameFile(path, path1));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
