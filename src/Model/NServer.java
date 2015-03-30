/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Logger;


/**
 * @author Sekou
 */
public class NServer extends AbstractMultichatServer {

    private InetAddress bindAddress;
    private int port;
    private ServerSocketChannel server_socket;
    private SocketChannel client_socket;
    private Selector selector;
    private Set<SelectionKey> keys;
    private Iterator<SelectionKey> keyIterator;
    private ByteBuffer byte_buffer;
    private Charset charset;
    private CharBuffer char_buffer;
    private Logger logger;

    public NServer(InetAddress bindAddress, int port, Logger logger) throws IOException {
        super(bindAddress, port);
        server_socket = ServerSocketChannel.open();
        selector = Selector.open();
        server_socket.configureBlocking(false); // Selector initialization
        server_socket.register(selector, SelectionKey.OP_ACCEPT);//Selector initialization
        server_socket.bind(new InetSocketAddress(bindAddress, port));
        byte_buffer = ByteBuffer.allocate(8192);//Allocate a buffer with a capacity of 8192 bytes?
        charset = Charset.defaultCharset();
        this.logger=logger;
    }


    public void start() {
        try {
            while (true) {
                selector.select();
                keys = selector.selectedKeys();
                keyIterator = keys.iterator();
                while (keyIterator.hasNext()) {
                    SelectionKey key = keyIterator.next();
                    if (key.isAcceptable()) {

                        client_socket = server_socket.accept();
                        client_socket.configureBlocking(false);
                        client_socket.register(selector, SelectionKey.OP_READ);


                    }
                    if (key.isReadable()) {

                        ((SocketChannel) key.channel()).read(byte_buffer);
                        byte_buffer.flip(); //Switch the buffer from writing mode to reading mode
                        char_buffer = charset.decode(byte_buffer);
                        System.out.println(char_buffer);
                        byte_buffer.compact();

                    }
                    keyIterator.remove();
                }

            }

        } catch (IOException ex) {
            logger.warning(ex.getMessage());
        }
    }
}
