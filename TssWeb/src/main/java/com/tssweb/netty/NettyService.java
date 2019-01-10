package com.tssweb.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xiaodj on 2018/11/27.
 */
public class NettyService implements Runnable {

    //public static boolean bCmd = false;
    private static Map<String, ChannelHandlerContext> channelMap;
    private int iport;  //端口号
    static {
        channelMap = new HashMap<String, ChannelHandlerContext>();
    }

    public static Map<String, ChannelHandlerContext> getChannelMap() {
        return channelMap;
    }

    public static void setChannelMap(Map<String, ChannelHandlerContext> channelMap) {
        NettyService.channelMap = channelMap;
    }

    public NettyService(int port){
        this.iport = port;
    }

    @Override
    public void run() {
        System.out.print("Netty Service Start....");
        //监听连接线程
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        //处理消息线程
        EventLoopGroup workerGroup = new NioEventLoopGroup(1);
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            //注册handler
                            socketChannel.pipeline().addLast(new NettyInHandler());
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            // 绑定端口，开始接收进来的连接 
            ChannelFuture future = bootstrap.bind(iport).sync();
            System.out.print("bind port:" + iport);
            // 等待服务器socket关闭
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}
