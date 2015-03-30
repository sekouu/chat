/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.Socket;


/**
 * @author Sekou
 */
public class ClientHandler implements Runnable {

    private final Socket client_socket; // Client socket (created after a connection acceptance)
    BufferedReader in; // Read the client socket buffer
    BufferedWriter out;
    private final Server server;
    private final String nickname;

    public ClientHandler(Socket client_socket, BufferedReader in, BufferedWriter out, Server server, String nickname) throws IOException {
        this.client_socket = client_socket;
        this.in = in;
        this.out = out;
        this.server = server;
        this.nickname = nickname;

    }

    @Override
    public void run() {

        try {
            out.write("Welcome to the chat !\n");
            out.flush();

            while (true) {
                String message = "/nick " + nickname + ": " + in.readLine() + "\n";
                System.out.println("Data received by the server : " + message);
                server.messageBroadcast(client_socket, server.getList(), message);
            }
        } catch (IOException ex) {

        }
    }
}
    

