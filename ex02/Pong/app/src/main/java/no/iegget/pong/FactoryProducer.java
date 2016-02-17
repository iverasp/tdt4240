package no.iegget.pong;

/**
 * Created by iver on 17/02/16.
 */
public class FactoryProducer {
    public static AbstractFactory getFactory(String choice) {
        if (choice.equalsIgnoreCase("player")) return new PlayerFactory();
        return null;
    }
}
