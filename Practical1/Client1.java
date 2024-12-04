package Practical1;

import java.io.*;
import java.net.*;

public class Client1 {

  public static void main(String[] args) {
    try (
      Socket socket = new Socket("localhost", 5000);
      BufferedReader input = new BufferedReader(
        new InputStreamReader(System.in)
      );
      PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
      BufferedReader in = new BufferedReader(
        new InputStreamReader(socket.getInputStream())
      )
    ) {
      System.out.println("Connected to the server.");
      String userInput;

      while ((userInput = input.readLine()) != null) {
        out.println(userInput);
        if ("Over".equalsIgnoreCase(userInput)) break;
        System.out.println("Server response: " + in.readLine());
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
