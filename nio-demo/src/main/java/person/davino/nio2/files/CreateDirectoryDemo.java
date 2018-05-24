package person.davino.nio2.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CreateDirectoryDemo {
    private final static Path path = Paths.get("x", "y");

    public static void main(String[] args) throws IOException {
        System.out.println("Create path " + path.toFile().getPath());
        Files.createDirectories(path);
        // 当Path是联级目录时, 使用createDirectory会抛出NoSuchFileException
        // 当目录存在时会抛出FileAlreadyExistsException!
        // createDirectories 则不会
//        Files.createDirectory(path);
        System.out.println("Created!");
    }
}
