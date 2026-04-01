package com.ningboz.mylearnproject.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class NettyServer {
    /**
     * 接受请求的处理组
     */
    private final NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);

    /**
     * 实际处理请求的处理组
     */
    private final NioEventLoopGroup workerGroup = new NioEventLoopGroup(2);


    /**
     * 初始化netty
     *
     * @param host hostName
     * @param port portName
     */
    public void initNettyServer(String host, int port) {
        // 引导启动
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        serverBootstrap
                // 绑定起来
                .group(bossGroup, workerGroup)
                // 设置channel的类型 【Nio】
                .channel(NioServerSocketChannel.class)
                // 设置队列大小
                .option(ChannelOption.SO_BACKLOG, 1024)
                // 发送缓冲区大小
                .option(ChannelOption.SO_SNDBUF, 256 * 1024)
                // 接收缓冲区大小
                .option(ChannelOption.SO_RCVBUF, 256 * 1024)
                // 设置TCP长连接 两小时内没有数据的通信时,TCP会自动发送一个活动探测数据报文
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                // 自定义handler【就是一个初始化器 每一个channel都会执行这个initChannel()】 这是直接匿名内部类 也可以写个class去extends
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) {
                        ChannelPipeline pipeline = ch.pipeline();
                        // 添加上编解码
                        pipeline.addLast("decoder", new StringDecoder());
                        pipeline.addLast("encoder", new StringEncoder());

                        // 添加上自己的处理器
                        pipeline.addLast("nettyServiceHandler", new NettyServiceHandler());
                    }
                });

        // 绑定对应的host和port
        serverBootstrap.bind(host, port);

        System.out.println("netty启动成功！");

    }

    /**
     *  关闭netty服务
     */

    public void closeNettyServer(){
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();

        System.out.println("netty关闭成功!");
    }
}
