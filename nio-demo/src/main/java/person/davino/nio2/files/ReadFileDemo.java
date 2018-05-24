package person.davino.nio2.files;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.List;

public class ReadFileDemo {

    private final static Path path = Paths.get("hello.txt");

    public static void main(String[] args) {
        byte[] bytes = new byte[1024];
        try {
            bytes = Files.readAllBytes(Paths.get("hello.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (byte b: bytes) {
            System.out.println(b & 255);
            System.out.println(b);
        }

        // 对文本文件有效, 否则 MalformedInputException
        try {
            List<String> lines = Files.readAllLines(Paths.get("hello.txt"));
            lines.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try{
            List<String> lines = Files.readAllLines(Paths.get("hello.txt"), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedReader reader = Files.newBufferedReader(Paths.get("hello.txt"));
            String line = new String();
            while ((line = reader.readLine())!= null) {
                System.out.println(line);
            }
        } catch (IOException e) {

        }

        try {
            // OpenOption默认是READ
            InputStream stream = Files.newInputStream(path, StandardOpenOption.READ);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
