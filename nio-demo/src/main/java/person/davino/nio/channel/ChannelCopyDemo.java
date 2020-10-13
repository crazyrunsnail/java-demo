package person.davino.nio.channel;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

/**
 *
 */
public class ChannelCopyDemo {

    public static void main(String[] args) {
        try (ReadableByteChannel readableByteChannel = Channels.newChannel(System.in); WritableByteChannel writableByteChannel = Channels.newChannel(System.out)) {
            copyChannel2(readableByteChannel, writableByteChannel);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private static void copyChannel1(ReadableByteChannel src, WritableByteChannel dest) throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(16 * 1024);
        while (src.read(byteBuffer) != -1) {
            byteBuffer.flip();
            dest.write(byteBuffer);
            // 有可能只写了部分， 这里使用compact，如果全部已经写了相当于clear();
            byteBuffer.compact();
        }

        // 确保文件被完整写
        byteBuffer.flip();
        while (byteBuffer.hasRemaining()) {
            dest.write(byteBuffer);
        }
    }

    private static void copyChannel2(ReadableByteChannel src, WritableByteChannel dest) throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(16 * 1024);
        while (src.read(byteBuffer) != -1) {
            byteBuffer.flip();
            dest.write(byteBuffer);

            while (byteBuffer.hasRemaining()) {
                dest.write(byteBuffer);
            }
            // 清空
            byteBuffer.clear();
        }
    }
}
