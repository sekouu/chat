/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * @author Sekou
 */

public class Server {

    private ServerSocket server_socket;
    private final InetAddress bindAddress; //Local address
    private final int port; //Automatically generated port
    private Socket newclient_socket;
    private final Map<Socket, BufferedWriter> client_list;
    private final Map<Socket, String> nickname_list;
    private BufferedReader in; // Read the client socket buffer
    private BufferedWriter out;
    private final Scanner sc = new Scanner(System.in);


    public Server(InetAddress bindAddress, int port) {
        this.bindAddress = bindAddress;
        this.port = port;
        this.client_list = new HashMap();
        this.nickname_list = new HashMap();

    }

    public Map<Socket, BufferedWriter> getList() {
        return client_list;
    }

    public void messageBroadcast(Socket client_socket, Map<Socket, BufferedWriter> client_list, String message) throws IOException {
        Iterator it = client_list.keySet().iterator();
        for (Map.Entry<Socket, BufferedWriter> entry : client_list.entrySet()) {
            if (entry.getKey() != client_socket) {

                entry.getValue().write(message); //Get the  corresponding bufferedwriter in the Map and write the message in the V object (here its BufferedWriter)
                entry.getValue().flush();
            }
        }

    }

    // Not used anymore -- Being used to receive data from a simple client
    private void readDataFromClient(Socket client_socket) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(client_socket.getInputStream()));
        while (true)
            System.out.println("Data received :" + in.readLine());
    }

    public void start() throws IOException {
        server_socket = new ServerSocket(port, 5, bindAddress);
        while (true) {
            newclient_socket = server_socket.accept();

            in = new BufferedReader(new InputStreamReader(newclient_socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(newclient_socket.getOutputStream()));
            out.write("Nickname :");
            out.flush();
            String nickname = in.readLine();
            nickname_list.put(newclient_socket, nickname);
            client_list.put(newclient_socket, out);
            (new Thread(new ClientHandler(newclient_socket, in, out, this, nickname))).start();

        }
    }

}
   
    

