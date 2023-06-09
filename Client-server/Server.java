import java.io.*;
import java.net.*;

public class Server {
	public static void main(String[] args) {
		try {
			ServerSocket serverSocket = new ServerSocket(8080);
			System.out.println("Server started. Waiting for client connection...");

			Socket clientSocket = serverSocket.accept();
			System.out.println("Client connected.");

			BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);

			String clientMessage = input.readLine();
			String response = processClientMessage(clientMessage);

			output.println(response);

			output.close();
			input.close();
			clientSocket.close();
			serverSocket.close();

			System.out.println("Server stopped.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static String processClientMessage(String message) {
		return "Server received: " + message;
	}
}