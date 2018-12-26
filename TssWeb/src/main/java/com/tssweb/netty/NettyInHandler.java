package com.tssweb.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import static com.tssweb.netty.DataEngine.msgQueue;

/**
 * Created by xiaodj on 2018/11/27.
 */
public class NettyInHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        //客户端连接
        System.out.print("客户端已连接");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        //客户端连接断开
        System.out.print("客户端已断开");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //super.channelRead(ctx, msg);
        //接收到客户端发送过来的消息
        ByteBuf buf = (ByteBuf)msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        if (req.length < 5)
            return;
        //1.验证校验位
        if(!judgeCheckSum(req))
            return;
        //2.判断是否为心跳消息
        if (req[0] == 0x0C)
            return;
        //线程同步
        msgQueue.offer(req);
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
//        cause.printStackTrace();
//        ctx.close();
    }

    //校验位
    public Boolean judgeCheckSum(byte[] msgBytes){
        byte iSum = 0;
        for (int i = 0; i < msgBytes.length - 1; i++)
        {
            iSum += msgBytes[i];
        }
        byte iCheck = msgBytes[msgBytes.length - 1];
        if ((~iSum + 1) != iCheck)
            return false;
        return true;
    }
}