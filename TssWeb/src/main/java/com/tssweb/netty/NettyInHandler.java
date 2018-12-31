package com.tssweb.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.springframework.stereotype.Component;

import static com.tssweb.netty.DataEngine.msgQueue;

/**
 * Created by xiaodj on 2018/11/27.
 */
@Component
public class NettyInHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        //保存客户端通道
        NettyService.getChannelMap().put("1", ctx);
        System.out.print("客户端已连接");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        //移除客户端通道
        NettyService.getChannelMap().remove("1");
        System.out.print("客户端已断开");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //接收到客户端发送过来的消息
        ByteBuf buf = (ByteBuf)msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        if (req.length < 30)    //小于30的长度，自动上传的数据是不完整的
            return;
        msgQueue.offer(req);
        buf.release();
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        //出现异常
       cause.printStackTrace();
       ctx.close();
    }
}