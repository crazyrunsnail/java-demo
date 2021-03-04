package person.davino.netty.demo.basecomponents;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.charset.StandardCharsets;

/**
 *  堆内
 *  direct
 *  composite
 */
public class ByteBufDemo {

    public static void main(String[] args) {
        // 数据是共享的
        ByteBuf byteBuf = Unpooled.copiedBuffer("Netty!", StandardCharsets.UTF_8);
        System.out.println(byteBuf.toString(StandardCharsets.UTF_8));
        System.out.println(byteBuf.isReadable());
        System.out.println(byteBuf.isDirect());
        System.out.println(byteBuf.capacity());
        System.out.println(byteBuf.readerIndex());
        System.out.println(byteBuf.writerIndex());


        byteBuf.setByte(0, (byte) 'n');
        System.out.println(">------------<");
        System.out.println(byteBuf.capacity());
        System.out.println(byteBuf.readerIndex());
        System.out.println(byteBuf.writerIndex());

        byteBuf.writeBytes("!".getBytes(StandardCharsets.UTF_8));
        System.out.println(">------------<");
        System.out.println(byteBuf.capacity());
        System.out.println(byteBuf.readerIndex());
        System.out.println(byteBuf.writerIndex());
        System.out.println(byteBuf.toString(StandardCharsets.UTF_8));

        // slice共享底层数组
        /*ByteBuf slice = byteBuf.slice(0, 3);
        byteBuf.setByte(0, (byte) 'J');
        System.out.println(byteBuf.getByte(0) == slice.getByte(0)); // true*/

        // 不改变byteBuf的readerIndex和writeIndex
        if (byteBuf.hasArray()) {
            System.out.println(new String(byteBuf.array(), StandardCharsets.UTF_8));
        }
        System.out.println(byteBuf.readerIndex());
        System.out.println(byteBuf.writerIndex());



    }
}
