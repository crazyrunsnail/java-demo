package person.davino.netty.demo.replaydecoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class MyLongDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("MyLongDecoder invoke!");
        if (in.readableBytes() < 8) {
            return;
        }

        long l = in.readLong();
        out.add(l);
    }
}
