package person.davino.nio2.files;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardOpenOption.*;

public class RandomAccessDemo {
    private final static Path path = Paths.get("emp");

    private final static int RECLEN = 50;

    public static void main(String[] args) throws IOException {

        // FileChannel 实现了 GatheringByteChannel ScatteringByteChannel SeekableByteChannel
        // 使用 SeekableByteChannel的position接口可以实现文件的指针移转
        FileChannel fc = FileChannel.open(path, CREATE, WRITE, SYNC).position(RECLEN * 2);

        ByteBuffer buffer = ByteBuffer.wrap("Johe Doe".getBytes());

        fc.write(buffer);

        fc.close();

        buffer.clear();


        // 为了演示所以使用File.newByteChannel
        // 实践上直接使用 FileChannel.open()更加方便
//        fc = FileChannel.open(path, READ).position(RECLEN * 2);
        SeekableByteChannel sbc;
        sbc = ((SeekableByteChannel)Files.newByteChannel(path, READ)).position(RECLEN * 2);

        sbc.read(buffer);

        sbc.close();

        System.out.println(new String(buffer.array()));



    }
}
