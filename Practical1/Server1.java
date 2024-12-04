package Practical1;

import java.io.*;
import java.net.*;

public class Server1 {
  public static void main(String[] args) {
    try (ServerSocket serverSocket = new ServerSocket(5000)) {
      System.out.println("Server started, waiting for a client...");
      try (Socket socket = serverSocket.accept()) {
        System.out.println("Client connected.");
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        String line;
        while ((line = in.readLine()) != null) {
          System.out.println("Received from client: " + line);
          if ("Over".equalsIgnoreCase(line)) break;
          out.println(line.toUpperCase());
        }
      }
      System.out.println("Connection closed.");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

