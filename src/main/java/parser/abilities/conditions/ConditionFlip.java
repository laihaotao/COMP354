package parser.abilities.conditions;

import game.Coin;
import game.Coin.CoinStatus;
import game.GameBoard;
import game.Player;

/**
 * Created by frede on 2017-07-17.
 */
public class ConditionFlip extends Condition {

    @Override
    public boolean evaluate(GameBoard gameBoard, Player owner) {
        return Coin.flip() == CoinStatus.Head;
    }
}
