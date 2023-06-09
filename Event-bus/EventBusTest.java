import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class EventBusTest {
	private EventBus eventBus;
	private Subscriber subscriber1;
	private Subscriber subscriber2;

	@BeforeEach
	void setup() {
		eventBus = new EventBus();
		subscriber1 = Mockito.mock(Subscriber.class);
		subscriber2 = Mockito.mock(Subscriber.class);
	}

	@Test
	void testSubscribeAndPublish() {
		eventBus.subscribe(subscriber1);
		eventBus.subscribe(subscriber2);

		Event event = new Event("Test Event");
		eventBus.publish(event);

		Mockito.verify(subscriber1).onEvent(event);
		Mockito.verify(subscriber2).onEvent(event);
	}

	@Test
	void testUnsubscribe() {
		eventBus.subscribe(subscriber1);
		eventBus.subscribe(subscriber2);

		eventBus.unsubscribe(subscriber2);

		Event event = new Event("Test Event");
		eventBus.publish(event);

		Mockito.verify(subscriber1).onEvent(event);
		Mockito.verify(subscriber2, Mockito.never()).onEvent(Mockito.any(Event.class));
	}
}