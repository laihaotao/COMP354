package parser.abilities.conditions;

import game.GameBoard;
import game.Player;
import parser.abilities.parts.AbilityPart;

/**
 * Created by frede on 2017-07-17.
 */
public abstract class Condition {
    public abstract boolean evaluate(AbilityPart caller, GameBoard gameBoard, Player owner);

}
