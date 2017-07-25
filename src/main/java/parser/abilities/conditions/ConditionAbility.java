package parser.abilities.conditions;

import game.GameBoard;
import game.Player;

public class ConditionAbility extends Condition{

    @Override
    public boolean evaluate(GameBoard gameBoard, Player owner) {
        return false;
    }
}
