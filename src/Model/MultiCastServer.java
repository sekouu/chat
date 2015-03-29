/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;


import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 *
 * @author Sekou
 */
public class MultiCastServer extends Thread {
    
    private MulticastSocket server_socket;
    private InetAddress address;
    private int port;
    private DatagramPacket packet_message;
    private byte[] message_buf;
    private String text;
    
    
    public MultiCastServer( InetAddress address, int port) throws IOException
    {
        server_socket = new MulticastSocket();
        this.address = address;
        this.port = port;
        server_socket = new MulticastSocket(port);
        server_socket.joinGroup(address);
    
    }
    
    public void run ()
    {
      
        ByteArrayInputStream reader;
        
        while (true)
        {
            message_buf = new byte[512];
            packet_message = new DatagramPacket(message_buf, message_buf.length);
            try {
                server_socket.receive(packet_message);
                text = (new DataInputStream(new ByteArrayInputStream(message_buf))).readUTF();
                System.out.println(text);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        
    }
     
    
    
    
}
