package person.davino.nio2.files;

import java.io.IOException;
import java.nio.file.*;

public class DeleteFileDemo {

    public static void main(String[] args) throws IOException {
        //NoSuchFileException
//        Path delete = Paths.get("x", "y1","x.txt");

        //DirectoryNotEmptyException
//        Path delete = Paths.get("x", "y1");
        Path delete = Paths.get("x", "y1", "hello_dump_moving.txt");
        try {
            Files.delete(delete);
        } catch (NoSuchFileException nsfe){
            System.err.printf("%s not exists", delete);
        } catch (DirectoryNotEmptyException dnee) {
            System.err.printf("%s is not empty, cannot delete!", delete);

        } catch (IOException e) {
            e.printStackTrace();
        }

        Files.deleteIfExists(delete);
    }

}
