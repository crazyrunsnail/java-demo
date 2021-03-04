package person.davino.netty.demo.protocol;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.StandardCharsets;

public class MyClientHandler extends SimpleChannelInboundHandler<PersonProtocol> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, PersonProtocol msg) throws Exception {
        System.out.println("收到客户端消息: " + msg.toString());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        PersonProtocol protocol = new PersonProtocol();
        protocol.setContent("客户端CLInt已连接");
        protocol.setHeader(protocol.getContent().getBytes(StandardCharsets.UTF_8).length);
        ctx.writeAndFlush(protocol);
    }

}
