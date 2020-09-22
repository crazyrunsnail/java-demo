package person.davino.netty.demo.chatexample;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.DefaultEventExecutor;
import io.netty.util.concurrent.GlobalEventExecutor;

public class MyChatServerHandler extends SimpleChannelInboundHandler<String> {

    // static 非常重要
    private static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端建立连接 ---> remoteAddress: " + ctx.channel().remoteAddress());
        channels.writeAndFlush("客户端[" + ctx.channel().remoteAddress() + "]加入\n");
        channels.add(ctx.channel());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端失去连接 ---> remoteAddress: " + ctx.channel().remoteAddress());
        ctx.writeAndFlush("客户端[" + ctx.channel().remoteAddress() + "]退出\n");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println("客户端上线 ---> remoteAddress: " + channel.remoteAddress() + " 上线");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println("客户端上线 ---> remoteAddress: " + channel.remoteAddress() + " 下线");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel channel = ctx.channel();
        System.out.println("客户端上线 ---> remoteAddress: " + channel.remoteAddress());
        channels.stream().forEach(c -> {
            if (channel != c) {
                c.writeAndFlush("客户端[" + channel.remoteAddress() + "]说:" + msg +"\n");
            } else {
                c.writeAndFlush("自己说: " + msg + "\n");
            }
        });
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }


}
