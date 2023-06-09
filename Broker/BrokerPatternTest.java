import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class BrokerPatternTest {
	private Broker broker;
	private MessageListener listener1;
	private MessageListener listener2;

	@BeforeEach
	void setup() {
		broker = new Broker();
		listener1 = Mockito.mock(MessageListener.class);
		listener2 = Mockito.mock(MessageListener.class);
	}

	@Test
	void testSubscribeAndPublish() {
		broker.subscribe(listener1);
		broker.subscribe(listener2);

		broker.publish("Hello, World!");

		Mockito.verify(listener1).onMessage("Hello, World!");
		Mockito.verify(listener2).onMessage("Hello, World!");
	}

	@Test
	void testUnsubscribeAndPublish() {
		broker.subscribe(listener1);
		broker.subscribe(listener2);

		broker.unsubscribe(listener1);

		broker.publish("Goodbye!");

		Mockito.verify(listener1, Mockito.never()).onMessage(Mockito.anyString());
		Mockito.verify(listener2).onMessage("Goodbye!");
	}
}