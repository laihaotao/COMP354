package parser.abilities.conditions;

import game.GameBoard;
import game.Player;

/**
 * Created by frede on 2017-07-17.
 */
public abstract class Condition {
    public abstract boolean evaluate(GameBoard gameBoard, Player owner);

}
