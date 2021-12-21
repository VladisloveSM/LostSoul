package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    public static void main(String[] args) {
        Socket socket = null;
        BufferedReader br = null;
        try
        {
            socket = new Socket(InetAddress.getLocalHost(), 8071);
            PrintStream ps = new PrintStream(socket.getOutputStream());
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            for (int i = 1; i <= 10; i++) {
                ps.println("PING");
                System.out.println(br.readLine());
                Thread.sleep(1_000);
            }

        } catch (UnknownHostException uhe) {
            System.err.println("Адрес не доступен: " + uhe);
        } catch (IOException io) {
            System.err.println("Ошибка I/O потока: " + io);
        } catch (InterruptedException ie) {
            System.err.println("Ошибка потока выполнения: " + ie);
        } finally {
            if(br != null ) {
                try {
                    br.close();
                } catch (IOException io) {
                    io.printStackTrace();
                }
            }
            if(socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
