package com.example.sdcliente.Services;

import com.example.sdcliente.Helpers.HelperService;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

import static java.lang.Integer.*;

public class SocketService {
    Socket echoSocket = null;
    OutputStream out = null;

    InputStream in = null;
    public void connect(String ip, String port) throws IOException {
        try {
            echoSocket = new Socket(ip, parseInt(port));
            out = echoSocket.getOutputStream();
            in = echoSocket.getInputStream();

        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: " + ip);
            throw e;
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for "
                    + "the connection to: " + ip);
            throw e;
        }
    }

    public void send(String message) {
        try {
            PrintWriter writer = new PrintWriter(out, true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            // Send a message to the server
            writer.println(message);

            // Receive the response from the server
            String response = reader.readLine();
            System.out.println("Received response from server: " + response);

            // Close the streams and socket
            reader.close();
            writer.close();
        } catch (IOException e) {
            HelperService.showErrorMessage(e.getMessage());
        }
    }

    public void close() throws IOException {
        out.close();
        in.close();
        echoSocket.close();
    }
}
