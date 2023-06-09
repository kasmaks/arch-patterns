import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class P2PPatternTest {
	private Peer peer1;

	@BeforeEach
	void setup() {
		peer1 = new Peer("127.0.0.1", 5000);
		peer2 = new Peer("127.0.0.1", 6000);
		peer3 = new Peer("127.0.0.1", 7000);
	}

	@Test
	void testSendMessage() {
		Peer connectedPeer1 = Mockito.mock(Peer.class);
		Peer connectedPeer2 = Mockito.mock(Peer.class);

		peer1.connectToPeer(connectedPeer1);
		peer1.connectToPeer(connectedPeer2);

		peer1.sendMessage("Hello from Peer 1!");

		Mockito.verify(connectedPeer1).receiveMessage("Hello from Peer 1!");
		Mockito.verify(connectedPeer2).receiveMessage("Hello from Peer 1!");
	}

	@Test
	void testReceiveMessage() {
		Peer connectedPeer1 = Mockito.mock(Peer.class);
		Peer connectedPeer2 = Mockito.mock(Peer.class);

		peer1.connectToPeer(connectedPeer1);
		peer1.connectToPeer(connectedPeer2);

		peer1.receiveMessage("Hello from Peer 2!");

		// Verify that peer1 propagated the message to the connected peers
		Mockito.verify(connectedPeer1).receiveMessage("Hello from Peer 2!");
		Mockito.verify(connectedPeer2).receiveMessage("Hello from Peer 2!");
	}
}