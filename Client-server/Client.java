import java.io.*;
import java.net.*;

public class Client {
	public static void main(String[] args) {
		try {
			Socket socket = new Socket("localhost", 8080);

			BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

			String message = "Hello, Server!";
			output.println(message);
			System.out.println("Sent: " + message);

			String serverResponse = input.readLine();
			System.out.println("Received: " + serverResponse);

			output.close();
			input.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}