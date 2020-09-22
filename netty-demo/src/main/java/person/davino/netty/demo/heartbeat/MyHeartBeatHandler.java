package person.davino.netty.demo.heartbeat;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;

public class MyHeartBeatHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            String eventType = "";
            switch (event.state()) {
                case WRITER_IDLE:
                    eventType = "写闲时";
                    break;
                case READER_IDLE:
                    eventType = "读闲时";
                    break;
                case ALL_IDLE:
                    eventType = "读写闲时";
                    break;
            }
            System.out.println(ctx.channel().remoteAddress() + "超时事件:" + eventType);
            ctx.channel().close();
        }
    }
}
