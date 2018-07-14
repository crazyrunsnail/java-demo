package person.davino.dp.decorator.iodecorator;

import java.io.*;

public class Test {
    public static void main(String[] args) throws IOException {
        LowerCaseInputStream lowerCaseInputStream =
                new LowerCaseInputStream(new BufferedInputStream(new FileInputStream(new File("file_write_demo.txt"))));
        int c;
        while ((c = lowerCaseInputStream.read()) > -1) {
            System.out.print((char)c);
        }
        lowerCaseInputStream.close();
    }
}
