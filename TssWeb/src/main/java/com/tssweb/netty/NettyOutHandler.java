package com.tssweb.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import org.springframework.stereotype.Component;

@Component
public class NettyOutHandler extends ChannelOutboundHandlerAdapter {

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        //super.write(ctx, msg, promise);
        if (msg instanceof byte[]){
            byte[] bytesWrite = (byte[])msg;
            ByteBuf buf = ctx.alloc().buffer(bytesWrite.length);
            buf.writeBytes(bytesWrite);
            ctx.writeAndFlush(buf).addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    //下发成功
                }
            });
        }
    }

    static public boolean sendMsg(Integer uid, byte[] bytes){
        NettyOutHandler nettyOutHandler = new NettyOutHandler();
        ChannelHandlerContext ctx = NettyService.getChannelMap().get("1");
        try {
            nettyOutHandler.write(ctx, bytes, null);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
