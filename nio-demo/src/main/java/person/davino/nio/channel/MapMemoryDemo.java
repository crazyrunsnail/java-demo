package person.davino.nio.channel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;

/**
 * Description
 * <p>
 * Writed by davino
 * Created on 28/02/2018
 */
public class MapMemoryDemo {

    public static void main(String[] args) {
        try (RandomAccessFile fis = new RandomAccessFile("hello.txt", "rw")) {
            MappedByteBuffer mappedByteBuffer =
                    fis.getChannel().map(FileChannel.MapMode.READ_WRITE, 0, fis.getChannel().size());
            while (mappedByteBuffer.hasRemaining())
                System.out.println(mappedByteBuffer.get());
            System.out.println("buffer position:" + mappedByteBuffer.position());

            mappedByteBuffer.put(1, (byte) 52);
            mappedByteBuffer.force();
//            String msg = "Hello times.";
//            mappedByteBuffer.asCharBuffer().append(msg);
//            mappedByteBuffer.flip();
//            while (mappedByteBuffer.hasRemaining())
//                System.out.println(mappedByteBuffer.get());

            // 如果不rewind.
            // hasRemaining功能将返回false.
            mappedByteBuffer.rewind();
            while (mappedByteBuffer.hasRemaining())
                System.out.println(mappedByteBuffer.get());
            System.out.println("buffer position:" + mappedByteBuffer.position());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
