import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

class Peer {
	private String ipAddress;
	private int port;
	private List<Peer> connectedPeers;

	public Peer(String ipAddress, int port) {
		this.ipAddress = ipAddress;
		this.port = port;
		this.connectedPeers = new ArrayList<>();
	}

	public void connectToPeer(Peer peer) {
		connectedPeers.add(peer);
		peer.connectedPeers.add(this);
	}

	public void disconnectFromPeer(Peer peer) {
		connectedPeers.remove(peer);
		peer.connectedPeers.remove(this);
	}

	public void sendMessage(String message) {
		for (Peer peer : connectedPeers) {
			try (Socket socket = new Socket(peer.ipAddress, peer.port)) {
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

				out.println(message);

				String response = in.readLine();
				System.out.println("Received response from " + peer.ipAddress + ":" + peer.port + ": " + response);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void receiveMessage(String message) {
        System.out.println("Received message: " + message);
        for (Peer peer : connectedPeers) {
            peer.propagateMessage(message);
        }
    }

	private void propagateMessage(String message) {
        System.out.println("Propagating message: " + message);
    }

	public void startListening() {
		new Thread(() -> {
			try (ServerSocket serverSocket = new ServerSocket(port)) {
				System.out.println("Listening on " + ipAddress + ":" + port);

				while (true) {
					Socket clientSocket = serverSocket.accept();
					handleClient(clientSocket);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}).start();
	}

	private void handleClient(Socket clientSocket) {
		new Thread(() -> {
			try {
				BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

				String message = in.readLine();
				System.out.println("Received message: " + message);

				String response = "Message received!";
				out.println(response);
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					clientSocket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
}

class P2PPattern {
	public static void main(String[] args) {
		Peer peer1 = new Peer("127.0.0.1", 5000);
		Peer peer2 = new Peer("127.0.0.1", 6000);
		Peer peer3 = new Peer("127.0.0.1", 7000);

		peer1.connectToPeer(peer2);
		peer1.connectToPeer(peer3);

		peer1.startListening();
		peer2.startListening();
		peer3.startListening();

		peer1.sendMessage("Hello from Peer 1!");
		peer2.sendMessage("Hello from Peer 2!");
		peer3.sendMessage("Hello from Peer 3!");
	}
}