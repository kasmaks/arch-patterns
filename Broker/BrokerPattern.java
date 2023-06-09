import java.util.ArrayList;
import java.util.List;

interface MessageListener {
	void onMessage(String message);
}

class Broker {
	private List<MessageListener> listeners;

	public Broker() {
		listeners = new ArrayList<>();
	}

	public void subscribe(MessageListener listener) {
		listeners.add(listener);
	}

	public void unsubscribe(MessageListener listener) {
		listeners.remove(listener);
	}

	public void publish(String message) {
		for (MessageListener listener : listeners) {
			listener.onMessage(message);
		}
	}
}

class Subscriber implements MessageListener {
	private String name;

	public Subscriber(String name) {
		this.name = name;
	}

	@Override
	public void onMessage(String message) {
		System.out.println(name + " received message: " + message);
	}
}

class BrokerPattern {
	public static void main(String[] args) {
		Broker broker = new Broker();

		Subscriber subscriber1 = new Subscriber("Subscriber 1");
		Subscriber subscriber2 = new Subscriber("Subscriber 2");

		broker.subscribe(subscriber1);
		broker.subscribe(subscriber2);

		broker.publish("Hello, World!");

		broker.unsubscribe(subscriber1);

		broker.publish("Goodbye!");
	}
}