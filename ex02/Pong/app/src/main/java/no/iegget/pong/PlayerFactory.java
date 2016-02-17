package no.iegget.pong;

/**
 * Created by iver on 17/02/16.
 */
public class PlayerFactory extends AbstractFactory {


    @Override
    Player getPlayer(String type) {
        if (type == null) return null;
        if (type.equalsIgnoreCase("paddle")) return new Paddle(20, 200);
        return null;
    }
}
