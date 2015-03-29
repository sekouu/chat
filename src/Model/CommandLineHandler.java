/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import gnu.getopt.Getopt;
import gnu.getopt.LongOpt;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;



/**
 *
 * @author Sekou
 */
public class CommandLineHandler {
    LongOpt[] longopts = new LongOpt[5];
    StringBuffer sb = new StringBuffer();
    NServer nio_server;
    Server s_server;
    int port;
    InetAddress address;
    
    
    
    
    public CommandLineHandler()
    {
        
    }
    public void command ( String [] args) throws UnknownHostException, IOException
    {
   
    longopts[0] = new LongOpt("address", LongOpt.REQUIRED_ARGUMENT , sb, 'a');
    longopts[1] = new LongOpt("help", LongOpt.NO_ARGUMENT , null, 'h');
    longopts[2] = new LongOpt("nio", LongOpt.REQUIRED_ARGUMENT , sb, 'n');
    longopts[3] = new LongOpt("port", LongOpt.REQUIRED_ARGUMENT , sb, 'p');
    longopts[4] = new LongOpt("server", LongOpt.NO_ARGUMENT , null, 's');
    
    Getopt g = new Getopt("Chat", args, "a:hncp:s", longopts);
    int c ;
    int server_mode = 0; // if the server is used as a  normal socket, the value is 0 nio mode is 1
       
    while ((c = g.getopt()) != -1){
        
        switch (c){
            case 'a':
                System.out.println("IP address : " + g.getOptarg());
                address =InetAddress.getByName(g.getOptarg());
                break;
                
            case 'h' :
                System.out.println("Help \n" );
                System.out.println(" -a, --address      set the IP address\n");
                System.out.println(" -h, --help         display this help and quit\n");
                System.out.println(" -n, --nio          use NIOs for the server\n");
                System.out.println(" -p, --port         set the port\n");
                System.out.println(" -s, --server       start the server\n");
                System.out.println("-c,  --client       Open a window");
                break;
                
            case 'n':
                System.out.println("NIOs are now use for the server: " );
                nio_server = new NServer(address, port);
                server_mode =1;
                break;
                
            case 'p' :
                System.out.println("Port number used : "+ g.getOptarg());
                port =Integer.parseInt( g.getOptarg());
                break;
            case 's':
                System.out.println("Server activated ");
                if (server_mode == 1)
                     nio_server.start();
                else {
                    s_server = new Server(address, port);
                    s_server.start();
                }
                break;
            case 'c':
                System.out.println("Opening a window");
                break;
                 
                
            default:
                System.out.println("Invalid option");
        }
    }
    
    }
   

    
}
