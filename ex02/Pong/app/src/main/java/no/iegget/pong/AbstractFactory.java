package no.iegget.pong;

/**
 * Created by iver on 17/02/16.
 */
public abstract class AbstractFactory {
    abstract Player getPlayer(String type);
}
