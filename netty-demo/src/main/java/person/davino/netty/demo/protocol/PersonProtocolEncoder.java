package person.davino.netty.demo.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.nio.charset.StandardCharsets;

public class PersonProtocolEncoder extends MessageToByteEncoder<PersonProtocol> {


    @Override
    protected void encode(ChannelHandlerContext ctx, PersonProtocol msg, ByteBuf out) throws Exception {
        System.out.println("PersonProtocolEncoder Invoke!");
        out.writeInt(msg.getHeader());
        out.writeBytes(msg.getContent().getBytes(StandardCharsets.UTF_8));
    }

}
