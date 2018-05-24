package person.davino.nio2.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WritingFileDemo {

    private final static Path path = Paths.get("file_write_demo.txt");

    public static void main(String[] args) {
        List<String> line = Stream.of("This is a new line!", "It's good1").collect(Collectors.toList());

        List<String> line2 = Stream.of("This is line written second time").collect(Collectors.toList());
        try {
            Files.write(path, line, StandardOpenOption.CREATE);
//            Files.write(path, line, StandardOpenOption.CREATE_NEW); //FileAlreadyExistsException
            System.out.println("Create a file " + path.getFileName() + " successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try{
            // 之后前需要用RandomAccessFile.seek(file.size())
            Files.write(path, line2, StandardOpenOption.APPEND);
            System.out.println("Append file " + path.getFileName() + " successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 使用 BufferedWriter
        try {
            Files.newBufferedWriter(path, StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Files.newOutputStream(path, StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
