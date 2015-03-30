/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;


import Model.CommandLineHandler;
import Model.MultiCastClient;
import Model.MultiCastServer;
import Model.NServer;
import Model.Server;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.util.Scanner;

/**
 *
 * @author Sekou
 */
public class Chat {

    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        // TODO code application logic here
        
        ///TP8 - Not used anymore
        /*
        InetAddress address =InetAddress.getByName("172.20.10.2");
        int port = 15000;
        Server server = new Server( address, port );
        server.start();
        */
        /*
        InetAddress group_address = InetAddress.getByName("228.0.0.4");
        
        MultiCastClient client = new MultiCastClient(group_address, 45000, "Sekou");
        client.start();
        */
        CommandLineHandler command = new CommandLineHandler();
        command.command(args);
        /*
        InetAddress address =InetAddress.getByName("172.20.10.2");
        int port = 15001;
        NServer server = new NServer(address, port);
        server.start();
        */ 
        
                
       }
      
        
       
        
       
        
    }
    

