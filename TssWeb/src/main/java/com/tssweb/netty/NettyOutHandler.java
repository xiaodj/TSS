package com.tssweb.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import org.springframework.stereotype.Component;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Component
public class NettyOutHandler extends ChannelOutboundHandlerAdapter {
//    private Lock cmdLock = new ReentrantLock();
//
//    public Lock getCmdLock() {
//        return cmdLock;
//    }
//
//    public void setCmdLock(Lock cmdLock) {
//        this.cmdLock = cmdLock;
//    }

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

                }
            });
        }

    }

    static public boolean sendMsg(Integer uid, byte[] bytes){
        Boolean bflag = false;
        NettyOutHandler nettyOutHandler = new NettyOutHandler();
        ChannelHandlerContext ctx = NettyService.getChannelMap().get("1");
        try {
            nettyOutHandler.write(ctx, bytes, null);
            for (int i = 0; i < 5; i++){
                if(DataEngine.cmdQueue.size() > 0){
                    bflag = DataEngine.cmdQueue.poll();
                    break;
                }
                else
                    Thread.sleep(1000);//睡眠1秒
            }
        } catch (Exception e) {
            return false;
        }
        return bflag;
    }
}
