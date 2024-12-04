import java.util.*;

public class PubSubSystem {

  // Interface defining a Subscriber
  interface Subscriber {
    void receiveMessage(String topic, String message);
    String getName();
  }

  // Concrete implementation of Subscriber
  static class ConcreteSubscriber implements Subscriber {

    private final String name;

    public ConcreteSubscriber(String name) {
      this.name = name;
    }

    @Override
    public void receiveMessage(String topic, String message) {
      System.out.println(
        name + " received message on topic '" + topic + "': " + message
      );
    }

    @Override
    public String getName() {
      return name;
    }
  }

  // Message broker handling subscriptions and message distribution
  static class MessageBroker {

    private final Map<String, List<Subscriber>> topics = new HashMap<>();

    public void subscribe(String topic, Subscriber subscriber) {
      topics.putIfAbsent(topic, new ArrayList<>());
      topics.get(topic).add(subscriber);
      System.out.println(
        subscriber.getName() + " subscribed to topic: " + topic
      );
    }

    public void publish(String topic, String message) {
      if (topics.containsKey(topic)) {
        System.out.println("\nPublishing message to topic: " + topic);
        for (Subscriber subscriber : topics.get(topic)) {
          subscriber.receiveMessage(topic, message);
        }
      } else {
        System.out.println("No subscribers for topic: " + topic);
      }
    }
  }

  // Publisher class to publish messages via the broker
  static class Publisher {

    private final MessageBroker broker;

    public Publisher(MessageBroker broker) {
      this.broker = broker;
    }

    public void publishMessage(String topic, String message) {
      broker.publish(topic, message);
    }
  }

  // Main method to demonstrate the Pub-Sub system
  public static void main(String[] args) {
    MessageBroker broker = new MessageBroker();

    Subscriber alice = new ConcreteSubscriber("Alice");
    Subscriber bob = new ConcreteSubscriber("Bob");

    broker.subscribe("Tech", alice);
    broker.subscribe("Sports", bob);
    broker.subscribe("Tech", bob);

    Publisher techPublisher = new Publisher(broker);
    Publisher sportsPublisher = new Publisher(broker);

    techPublisher.publishMessage("Tech", "New AI model released!");
    sportsPublisher.publishMessage("Sports", "Football World Cup starts soon!");
    techPublisher.publishMessage("Politics", "Election results announced.");
  }
}
