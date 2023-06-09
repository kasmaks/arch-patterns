import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerTest {
	@Mock
	private Socket mockClientSocket;

	@Mock
	private BufferedReader mockClientInput;

	@Mock
	private PrintWriter mockClientOutput;

	private Server server;

	@BeforeEach
	public void setup() throws IOException {
		MockitoAnnotations.openMocks(this);

		server = new Server();
		Mockito.when(mockClientSocket.getInputStream()).thenReturn(mockClientInput);
		Mockito.when(mockClientSocket.getOutputStream()).thenReturn(mockClientOutput);
	}

	@Test
	public void testProcessClientMessage() {
		String clientMessage = "Hello, Server!";
		String expectedResponse = "Server received: " + clientMessage;

		try {
			Mockito.when(mockClientInput.readLine()).thenReturn(clientMessage);
		} catch (IOException e) {
			e.printStackTrace();
		}

		String actualResponse = server.processClientMessage(clientMessage);

		Assertions.assertEquals(expectedResponse, actualResponse);
	}

	@Test
	public void testClientCommunication() throws IOException {
		String clientMessage = "Hello, Server!";
		String expectedServerResponse = "Server received: " + clientMessage;
		String expectedClientResponse = "Received: " + expectedServerResponse;

		Mockito.when(mockClientInput.readLine()).thenReturn(clientMessage);

		server.handleClient(mockClientSocket);

		Mockito.verify(mockClientOutput).println(expectedServerResponse);
	}
}