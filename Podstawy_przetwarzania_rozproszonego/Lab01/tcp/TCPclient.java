// Podstawy Programowania Rozproszonego

package tcp;

import java.io.*;
import java.net.*;

class TCPclient {
    public static void main(String argv[]) throws Exception {
        String text;
        String modifiedText;
        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
        try (Socket clientSocket = new Socket()) {
            clientSocket.bind(new InetSocketAddress(2011));
            clientSocket.connect(new InetSocketAddress("192.168.0.157", 8080));
            BufferedWriter serverOutput = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            BufferedReader serverInput = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            System.out.print("Enter text and press ENTER\n>");
            text = userInput.readLine();
            serverOutput.write(text + "\n");
            serverOutput.flush();
            modifiedText = serverInput.readLine();
            System.out.println("Response from server: " + modifiedText);
        }
    }
}
