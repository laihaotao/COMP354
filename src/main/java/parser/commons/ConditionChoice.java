package parser.commons;

import game.GameBoard;
import game.Player;

public class ConditionChoice extends Condition{

    @Override
    public boolean evaluate(GameBoard gameBoard, Player owner) {
        return false;
    }
}
