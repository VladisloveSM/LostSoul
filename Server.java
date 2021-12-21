package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        try
        {

            ServerSocket server = new ServerSocket(8071);
            System.out.println("initialized");
            while (true)
            {
                Socket socket = server.accept();
                System.out.println(socket.getInetAddress().getHostName() + " connected");

                ServerThread thread = new ServerThread(socket);
                thread.start();
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }

}
