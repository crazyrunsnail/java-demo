package person.davino.nio.channel;

import person.davino.nio.buffer.BufferDemo;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

/**
 * Description
 * <p>
 * Writed by davino
 * Created on 18/02/2018
 */
public class ChannelDemo {
    public static void main(String[] args) {

        //其中一种创建chaanel的方式
        ReadableByteChannel src = Channels.newChannel(System.in);
        WritableByteChannel dest = Channels.newChannel(System.out);

        try {
            copy(src, dest);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                src.close();
                dest.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static void copy(ReadableByteChannel src, WritableByteChannel dest) throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(2048);
        while ((src.read(byteBuffer)) != -1) {
            System.out.println("read....");
            BufferDemo.printBuffer(byteBuffer);
            byteBuffer.flip();  //flip的作用是将limit设置为position, position设置为0
            System.out.println("after flip....");
            BufferDemo.printBuffer(byteBuffer);
            dest.write(byteBuffer);
            System.out.println("after writer....");
            BufferDemo.printBuffer(byteBuffer);
            byteBuffer.compact();
            System.out.println("after compact....");
            BufferDemo.printBuffer(byteBuffer);
        }
        byteBuffer.flip();
        while (byteBuffer.hasRemaining())
            dest.write(byteBuffer);
    }
}
