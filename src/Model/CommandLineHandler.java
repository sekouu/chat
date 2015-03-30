/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import View.ChatWindow;
import gnu.getopt.Getopt;
import gnu.getopt.LongOpt;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.*;


/**
 * @author Sekou
 */


public class CommandLineHandler {
    protected static Logger logger;
    LongOpt[] longopts = new LongOpt[6];
    StringBuffer sb = new StringBuffer();
    NServer nio_server;
    Server s_server;
    MultiCastClient m_client;
    int port;
    InetAddress address;
    String nickname;
    Scanner sc = new Scanner(System.in);


    public CommandLineHandler(Logger logger) {
        this.logger = logger;
    }

    public void command(String[] args) {

        longopts[0] = new LongOpt("address", LongOpt.REQUIRED_ARGUMENT, sb, 'a');
        longopts[1] = new LongOpt("help", LongOpt.NO_ARGUMENT, null, 'h');
        longopts[2] = new LongOpt("nio", LongOpt.REQUIRED_ARGUMENT, sb, 'n');
        longopts[3] = new LongOpt("port", LongOpt.REQUIRED_ARGUMENT, sb, 'p');
        longopts[4] = new LongOpt("server", LongOpt.NO_ARGUMENT, null, 's');
        longopts[5] = new LongOpt("debug", LongOpt.NO_ARGUMENT, null, 'd');

        Getopt g = new Getopt("Chat", args, "da:hncmp:s", longopts);
        int c;
        int server_mode = 0; // if the server is used as a  normal socket, the value is 0 nio mode is 1

        //Languages keep
        ResourceBundle bundle = ResourceBundle.getBundle("bundles.ApplicationBundle", Locale.FRENCH);

        while ((c = g.getopt()) != -1) {

            switch (c) {
                case 'a':
                    System.out.println("IP address : " + g.getOptarg());
                    logger.info("set IP address at " + g.getOptarg());
                    try {
                        address = InetAddress.getByName(g.getOptarg());
                    } catch (UnknownHostException e) {
                        logger.warning(e.getMessage());
                    }
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
                    logger.info("NIOs are now use for the server");
                    try {
                        nio_server = new NServer(address, port, logger);
                        server_mode = 1;
                    } catch (IOException e) {
                        logger.warning(e.getMessage());
                    }

                    break;

                case 'p':
                    System.out.println("Port number used : " + g.getOptarg());
                    logger.info("Setting port at " + g.getOptarg());
                    port = Integer.parseInt(g.getOptarg());
                    break;

                case 'm':
                    System.out.println("Nickname :");
                    nickname = sc.nextLine();
                    logger.info("Nickname set as " + nickname);
                    try {
                        m_client = new MultiCastClient(address, port, nickname, logger);
                        m_client.start();
                    } catch (IOException e) {
                        logger.warning(e.getMessage());
                    }

                    break;

                case 's':
                    System.out.println("Server activated ");
                    if (server_mode == 1)
                        nio_server.start();
                    else {
                        s_server = new Server(address, port);
                        try {
                            s_server.start();
                        } catch (IOException e) {
                            logger.warning(e.getMessage());
                        }
                    }
                    logger.info("Server start");
                    break;

                case 'c':
                    System.out.println("Opening a window");
                    ChatWindow window = new ChatWindow();
                    window.main(args);
                    break;

                case 'd':
                    System.out.println("Debugging mode :");
                    logger.addHandler(new ConsoleHandler());
                    break;

                default:
                    System.out.println("Invalid option");
                    break;

            }
        }

    }


}
