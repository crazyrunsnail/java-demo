package person.davino.nio2.files;

import java.io.IOException;
import java.nio.file.*;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class MovingFileDemo {


    public static final Path src = Paths.get("x", "y1", "hello_dump.txt");

    public static final Path dst = Paths.get("x", "y1", "hello_dump_moving.txt");

    public static void main(String[] args) {
        // 共用copyOption
        try {
            Files.move(src, dst, REPLACE_EXISTING);
        }catch (FileAlreadyExistsException ex) {
            System.err.printf("%s: files already exists", dst);
        }catch (DirectoryNotEmptyException dnee) {
            System.err.printf("%s not empty", dst);
        }catch (IOException ioe) {
            System.err.printf("io exception %s%n", dst);
        }
    }

}
