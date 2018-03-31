package person.davino.nio.channel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.GatheringByteChannel;
import java.nio.channels.ScatteringByteChannel;

/**
 * Description
 * <p>
 * Writed by davino
 * Created on 28/02/2018
 */
public class ScatterGatherDemo {

    public static void main(String[] args) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("hello.txt");
        } catch (FileNotFoundException e) {
            System.out.println("File not found....");
            System.exit(1);
        }

        ScatteringByteChannel src = (ScatteringByteChannel) Channels.newChannel(fis);

        ByteBuffer byteBuffer1 = ByteBuffer.allocateDirect(1);
        ByteBuffer byteBuffer2 = ByteBuffer.allocateDirect(2);
        ByteBuffer[] byteBuffers = {byteBuffer1, byteBuffer2};

        try {
            src.read(byteBuffers);
        } catch (IOException e) {
            System.out.println("IOException");
        }

        byteBuffer1.flip();
        while (byteBuffer1.hasRemaining())
            System.out.println((char) byteBuffer1.get());

        byteBuffer2.flip();
        while (byteBuffer2.hasRemaining())
            System.out.println((char)byteBuffer2.get());

        byteBuffer1.rewind();
        byteBuffer2.rewind();

        GatheringByteChannel dest = null;
        try {
            dest = (GatheringByteChannel)Channels.newChannel(new FileOutputStream("y.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        byteBuffers[0] = byteBuffer1;
        byteBuffers[1] = byteBuffer2;

        try {
            dest.write(byteBuffers);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
