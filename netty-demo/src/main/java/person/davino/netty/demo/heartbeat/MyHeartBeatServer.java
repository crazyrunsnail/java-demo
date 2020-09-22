package person.davino.netty.demo.heartbeat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import person.davino.netty.demo.chatexample.MyChatServerHandler;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

public class MyHeartBeatServer {

    public static void main(String[] args) throws InterruptedException {

        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(boss, worker).channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new IdleStateHandler(5, 10, 15, TimeUnit.SECONDS));
                            pipeline.addLast(new MyHeartBeatHandler());
                        }
                    });
            ChannelFuture channelFuture = bootstrap.bind(8888).sync();
            System.out.println("Server start on 8888....");
            channelFuture.channel().closeFuture().sync();
        } finally {
            System.out.println("Shutdown.");
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }


    }

}
