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
    public void connect(String ip, String port) throws Exception {
        try {
            echoSocket = new Socket(ip, parseInt(port));
            out = echoSocket.getOutputStream();
            in = echoSocket.getInputStream();

        } catch (UnknownHostException e) {
            throw new Exception("Don't know about host: " + ip);
        } catch (IOException e) {
            throw new Exception("Couldn't get I/O for "
                    + "the connection to: " + ip);
        }
    }

    public String send(String message) {
        String response = null;
        try {
            PrintWriter writer = new PrintWriter(out, true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            // Send a message to the server
            writer.println(message);

            // Receive the response from the server
            response = reader.readLine();
            System.out.println("Received response from server: " + response);

            // Close the streams
            reader.close();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
            HelperService.showErrorMessage(e.getMessage());
        }

        return response;
    }

    public void close() throws IOException {
        out.close();
        in.close();
        echoSocket.close();
    }
}
