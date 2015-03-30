/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import View.ChatWindow;
import gnu.getopt.Getopt;
import gnu.getopt.LongOpt;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;


/**
 * @author Sekou
 */


public class CommandLineHandler {
    LongOpt[] longopts = new LongOpt[5];
    StringBuffer sb = new StringBuffer();
    NServer nio_server;
    Server s_server;
    MultiCastClient m_client;
    int port;
    InetAddress address;
    String nickname;
    Scanner sc = new Scanner(System.in);


    public CommandLineHandler() {

    }

    public void command(String[] args) throws UnknownHostException, IOException {

        longopts[0] = new LongOpt("address", LongOpt.REQUIRED_ARGUMENT, sb, 'a');
        longopts[1] = new LongOpt("help", LongOpt.NO_ARGUMENT, null, 'h');
        longopts[2] = new LongOpt("nio", LongOpt.REQUIRED_ARGUMENT, sb, 'n');
        longopts[3] = new LongOpt("port", LongOpt.REQUIRED_ARGUMENT, sb, 'p');
        longopts[4] = new LongOpt("server", LongOpt.NO_ARGUMENT, null, 's');

        Getopt g = new Getopt("Chat", args, "a:hncmp:s", longopts);
        int c;
        int server_mode = 0; // if the server is used as a  normal socket, the value is 0 nio mode is 1

        //Languages keep
        ResourceBundle bundle = ResourceBundle.getBundle("bundles.ApplicationBundle", Locale.ENGLISH) ;

        while ((c = g.getopt()) != -1) {

            switch (c) {
                case 'a':
                    System.out.println("IP address : " + g.getOptarg());
                    address = InetAddress.getByName(g.getOptarg());
                    break;

                case 'h':
                    System.out.println(bundle.getString("help"));
                    System.out.println(bundle.getString("-a"));
                    System.out.println(bundle.getString("-d"));
                    System.out.println(bundle.getString("-h"));
                    System.out.println(bundle.getString("-m"));
                    System.out.println(bundle.getString("-n"));
                    System.out.println(bundle.getString("-p"));
                    System.out.println(bundle.getString("-s"));
                    System.out.println(bundle.getString("-c"));
                    break;

                case 'n':
                    System.out.println("NIOs are now use for the server: ");
                    nio_server = new NServer(address, port);
                    server_mode = 1;
                    break;

                case 'p':
                    System.out.println("Port number used : " + g.getOptarg());
                    port = Integer.parseInt(g.getOptarg());
                    break;
                case 'm':
                    System.out.println("Nickname :");
                    nickname = sc.nextLine();

                    m_client = new MultiCastClient(address, port, nickname);
                    m_client.start();
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
                    ChatWindow window = new ChatWindow();
                    window.main(args);
                    break;


                default:
                    System.out.println("Invalid option");

            }
        }

    }


}
