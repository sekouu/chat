/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 *
 * @author Sekou
 */
public class MultiCastClient extends Thread {
    
    private InetAddress address;
    private MulticastSocket client_socket;
    private int port;
    private DatagramPacket packet_message;
    private String nickname;
      private byte[] message_buf;
    private String text;
    
    public MultiCastClient( InetAddress address, int port, String nick) throws IOException
    {
        this.port = port;
        this.address = address;
        this.nickname = nick;
        client_socket = new MulticastSocket(port);
        client_socket.joinGroup(address);
        
        
    }
    
    public void run ()
    {
       BufferedReader client_input;
            try {
       client_input = new BufferedReader(new InputStreamReader(System.in));
       
       new Thread (new IncomingMessage()).start();
       
       while(true) {
                         
                          String texte = client_input.readLine();
			
                             
                              sendMessage(texte);
                       
                          }
                          
                          
                                             
       }
    
    catch (Exception e) {
       e.printStackTrace();
    }
    }
  
  public void sendMessage(String text) throws Exception {
		byte[] contenuMessage;
		DatagramPacket message;
	
		ByteArrayOutputStream output = new ByteArrayOutputStream(); 
		text = nickname + " : " + text ;
                //System.out.println(text);
		(new DataOutputStream(output)).writeUTF(text); 
		contenuMessage = output.toByteArray();
		message = new DatagramPacket(contenuMessage, contenuMessage.length, address, port);
		client_socket.send(message);
  }
  
  public String getText()
  {
      return text;
  }
  private class IncomingMessage extends Thread{
    
      public void IncomingMessage()
  {
   
  }
      public void run()
      {
          while (true)
       {
            message_buf = new byte[512];
            packet_message = new DatagramPacket(message_buf, message_buf.length);
            try {
                client_socket.receive(packet_message);
                text = (new DataInputStream(new ByteArrayInputStream(message_buf))).readUTF();
                System.out.println(text);
                
            }
            catch (Exception e) {
                e.printStackTrace();
            }
       }
          
      }
  }
  
  
  
  
}
    
    
    
    


