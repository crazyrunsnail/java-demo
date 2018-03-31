package person.davino.nio.channel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

/**
 * Description
 * <p>
 * Writed by davino
 * Created on 28/02/2018
 */
public class TransferToDemo {

    public static void main(String[] args) {
        try(FileInputStream fis = new FileInputStream("hello.txt")) {
            FileChannel fc = fis.getChannel();
            WritableByteChannel dest = Channels.newChannel(System.out);
            fc.transferTo(0, fc.size(), dest);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
