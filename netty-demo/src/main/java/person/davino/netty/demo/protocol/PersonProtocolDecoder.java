package person.davino.netty.demo.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class PersonProtocolDecoder extends ReplayingDecoder<Void> {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("PersonProtocolDecoder invoke!");
        PersonProtocol personProtocol = new PersonProtocol();
        personProtocol.setHeader(in.readInt());
        personProtocol.setContent(in.readBytes(personProtocol.getHeader()).toString(StandardCharsets.UTF_8));
        out.add(personProtocol);
    }
}
