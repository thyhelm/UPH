// Podstawy Programowania Rozproszonego

package tcp;

import java.io.*;
import java.net.*;

public class TCPserver
{
   public static void main(String argv[]) throws Exception
      {
         String clientText;
         String clientTextUppercase;
         ServerSocket serverSocket = new ServerSocket(8080);

         while(true)
         {
             try (Socket socket = serverSocket.accept()) {
                 System.out.println("Accepted client: <"+socket.getInetAddress()+":"+
                         socket.getPort()+"> on port: "+socket.getLocalPort());
                 BufferedReader clientInput =
                         new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 BufferedWriter clientOutput =
                         new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                 clientText = clientInput.readLine();
                 System.out.println("Received: " + clientText);
                 clientTextUppercase = clientText.toUpperCase() + "\n";
                 clientOutput.write(clientTextUppercase);
                 clientOutput.flush();
             }
         }
      }
}