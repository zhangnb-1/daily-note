package com.ningboz.mylearnproject.web_copy;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

class NioSubReactor implements Runnable {

    private static final String INDEX_HTML_RESPONSE = "HTTP/1.1 200 OK\r\nContent-Type: text/html; charset=UTF-8\r\nContent-Length: 70\r\n\r\n<head><link rel=\"icon\" href=\"data:;base64,=\"></head>10214304412 袁凡";
    private static final String PLAIN_TEXT_RESPONSE_TEMPLATE = "HTTP/1.1 200 OK\r\nContent-Type: text/plain; charset=UTF-8\r\n\r\n";
    private static final int BUFFER_SIZE = 4096;
    private static final int INITIAL_POOL_SIZE = 100;

    // Sub-reactor's selector
    private Selector subReactorSelector;
    // Number of CPU cores in the current machine environment
    private static int coreNum = 1;
    // Static thread pool, each thread corresponds to a sub-reactor
    // Sub-reactor id
    private int id;
    // Buffer pool
    private final BlockingQueue<ByteBuffer> bufferPool;

    public NioSubReactor(int id) throws IOException {
        this.subReactorSelector = Selector.open();
        this.id = id;
        this.bufferPool = new LinkedBlockingQueue<>();
        initializeBufferPool();
    }

    private void initializeBufferPool() {
        for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
            bufferPool.add(ByteBuffer.allocate(BUFFER_SIZE));
        }
    }

    private ByteBuffer getBufferFromPool() throws InterruptedException {

        ByteBuffer buffer = bufferPool.poll();
        if (buffer == null) {
            buffer = ByteBuffer.allocate(BUFFER_SIZE);
        }
        return buffer;
    }

    private void returnBufferToPool(ByteBuffer buffer) {
        buffer.clear();
        bufferPool.offer(buffer);
    }

    /**
     * Add channel to this sub-reactor
     * Register to the sub-reactor's selector
     * Create a handler class for this channel
     *
     * @param socketChannel
     * @throws ClosedChannelException
     */
    public void addSocketChannel(SocketChannel socketChannel) throws ClosedChannelException {
        // The channel passed in from the main selector is registered to the sub-reactor
        socketChannel.register(subReactorSelector, SelectionKey.OP_READ);
    }

    public void wakeup() {
        // Wake up the selector blocked by the select method
        subReactorSelector.wakeup();
    }

    /**
     * Event loop
     *
     * @throws IOException
     */
    public void sub_selectLoop() throws IOException {

        while (true) {
            subReactorSelector.select();
            Set<SelectionKey> selectionKeys = subReactorSelector.selectedKeys();
            for (SelectionKey selectionKey : selectionKeys) {
                if (selectionKey.isReadable()) {
                    selectionKey.interestOps(0);
                    try {
                        new ReadHandler(selectionKey).handleRead();
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                        try {
                            selectionKey.channel().close();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        selectionKey.cancel();
                    }
                }

            }
            selectionKeys.clear();
        }
    }

    public void run() {
        try {
            sub_selectLoop();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Channel data processing inner class
     *
     * @author CringKong
     */
    class ReadHandler implements Runnable {

        private final SelectionKey key;

        public ReadHandler(SelectionKey key) {
            this.key = key;
        }

        public void handleRead() throws IOException, InterruptedException {
            SocketChannel socketChannel = (SocketChannel) key.channel();
            ByteBuffer buffer = getBufferFromPool();
            int bytesRead = socketChannel.read(buffer);

            if (bytesRead == -1) {
                socketChannel.close();
                key.cancel();
                returnBufferToPool(buffer);
                return;
            } else {
                // Handle the request
                buffer.flip();
                byte[] data = new byte[buffer.remaining()];
                buffer.get(data);
                String request = new String(data, StandardCharsets.UTF_8);
                char[] chars = request.toCharArray();
                StringBuilder method_builder = new StringBuilder();
                StringBuilder path_builder= new StringBuilder();
                StringBuilder http_builder= new StringBuilder();
                int i;
                for (i = 0; i < chars.length; i++) {
                    if(chars[i] == ' '){
                        break;
                    }
                    method_builder.append(chars[i]);
                }
                for(i++; i < chars.length; i++) {
                    if(chars[i] == ' '){
                        break;
                    }
                    path_builder.append(chars[i]);
                }

                for(i++; i < chars.length; i++) {
                    if (chars[i] == '\r' && chars[i + 1] == '\n') {
                        break;
                    }
                    http_builder.append(chars[i]);
                }
                String method = method_builder.toString();
                String path = path_builder.toString();
                String http = http_builder.toString();
                if (!http.equals("HTTP/1.1")) {
                    socketChannel.close();
                    returnBufferToPool(buffer);
                    return;
                }



                if (method.equals("GET") && path.equals("/index.html")) {
                    // Respond to the request
                    buffer.clear();
                    buffer.put(INDEX_HTML_RESPONSE.getBytes(StandardCharsets.UTF_8));
                    buffer.flip();
                    socketChannel.write(buffer);
                } else {
                    socketChannel.close();
                }
                buffer.clear();
            }

            // Re-register read event
            key.interestOps(SelectionKey.OP_READ);
            key.selector().wakeup();
            returnBufferToPool(buffer);
        }

        @Override
        public void run() {
            try {
                handleRead();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
                try {
                    key.channel().close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                key.cancel();
            }
        }
    }
}