import java.util.ArrayList;
import java.util.List;

class Event {
	private String name;

	public Event(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}

class EventBus {
	private List<Subscriber> subscribers;

	public EventBus() {
		this.subscribers = new ArrayList<>();
	}

	public void subscribe(Subscriber subscriber) {
		subscribers.add(subscriber);
	}

	public void unsubscribe(Subscriber subscriber) {
		subscribers.remove(subscriber);
	}

	public void publish(Event event) {
		for (Subscriber subscriber : subscribers) {
			subscriber.onEvent(event);
		}
	}
}

interface Subscriber {
	void onEvent(Event event);
}

class ExampleSubscriber implements Subscriber {
	private String name;

	public ExampleSubscriber(String name) {
		this.name = name;
	}

	@Override
	public void onEvent(Event event) {
		System.out.println("Subscriber " + name + " received event: " + event.getName());
	}
}

public class EventBusPattern {
	public static void main(String[] args) {
		EventBus eventBus = new EventBus();

		Subscriber subscriber1 = new ExampleSubscriber("Subscriber 1");
		Subscriber subscriber2 = new ExampleSubscriber("Subscriber 2");

		eventBus.subscribe(subscriber1);
		eventBus.subscribe(subscriber2);

		Event event = new Event("Example Event");
		eventBus.publish(event);

		eventBus.unsubscribe(subscriber1);

		Event anotherEvent = new Event("Another Event");
		eventBus.publish(anotherEvent);
	}
}