package com.ningboz.mylearnproject.web_copy;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class NioMainReactor {

    // Main reactor's selector
    private Selector reactorSelector;
    // Main reactor's server channel
    private ServerSocketChannel serverSocketChannel;
    // Sub-reactor array
    private NioSubReactor[] subReactor;
    // Number of CPU cores
    int coreNum;

    // Main reactor's thread pool
    private ExecutorService main_threadPool;

    /**
     * Main reactor constructor
     * Responsible for determining the listening port, opening the selector, opening the channel, and initializing the sub-reactor array
     *
     * @param port
     * @throws IOException
     */
    public NioMainReactor(int port) throws IOException {
        reactorSelector = Selector.open();
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(reactorSelector, SelectionKey.OP_ACCEPT);
        serverSocketChannel.bind(new InetSocketAddress(port));
        // Get the number of CPU cores in the current machine environment
        coreNum = 20;
        subReactor = new NioSubReactor[coreNum];
        main_threadPool = Executors.newFixedThreadPool(coreNum);
        for (int i = 0; i < subReactor.length; i++) {
            subReactor[i] = new NioSubReactor(i);
            main_threadPool.submit(subReactor[i]);
        }
    }

    /**
     * Main reactor's event loop, used to receive external socket requests and distribute them to the sub-selectors
     *
     * @throws IOException
     */
    public void main_selectLoop() throws IOException {
        int index = 0;
        while (true) {
            reactorSelector.select();
            Set<SelectionKey> selectionKeys = reactorSelector.selectedKeys();
            for (SelectionKey selectionKey : selectionKeys) {
                if (selectionKey.isAcceptable()) {
                    ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
                    // The load balancing strategy is round-robin distribution
                    accept(serverSocketChannel, index);
                    index = (index + 1) % coreNum;
                }
                selectionKeys.remove(selectionKey);
            }
        }
    }

    /**
     * Responsible for distributing the received SocketChannel to the sub-reactor
     *
     * @param serverSocketChannel
     * @param index
     * @throws IOException
     */
    private void accept(ServerSocketChannel serverSocketChannel, int index) throws IOException {
        SocketChannel socketChannel = serverSocketChannel.accept();
        if (socketChannel != null) {
            socketChannel.configureBlocking(false);
            // Distribute to subReactor for processing
            NioSubReactor currentSubReactor = subReactor[index];
            currentSubReactor.addSocketChannel(socketChannel);
            currentSubReactor.wakeup();
        }
    }
}