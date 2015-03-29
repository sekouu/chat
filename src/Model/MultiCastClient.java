/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
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
       while(true) {
			  String texte = client_input.readLine();
			  sendMessage(texte);
       }
    }
    catch (Exception e) {
       e.printStackTrace();
    }
  } 

  void sendMessage(String text) throws Exception {
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
}
    
    
    
    


