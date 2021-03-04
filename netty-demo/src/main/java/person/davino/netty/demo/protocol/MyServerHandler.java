package person.davino.netty.demo.protocol;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.StandardCharsets;

public class MyServerHandler extends SimpleChannelInboundHandler<PersonProtocol> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, PersonProtocol msg) throws Exception {
        System.out.println("read from clinet : " + msg);

        PersonProtocol protocol = new PersonProtocol();
        protocol.setContent("收到你的消息:" + msg.getContent());
        protocol.setHeader(protocol.getContent().getBytes(StandardCharsets.UTF_8).length);
        ctx.writeAndFlush(protocol);
    }
}
