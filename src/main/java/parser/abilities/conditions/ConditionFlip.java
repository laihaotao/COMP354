package parser.abilities.conditions;

import game.Coin;
import game.Coin.CoinStatus;
import game.GameBoard;
import game.Player;
import parser.abilities.parts.AbilityPart;

/**
 * Created by frede on 2017-07-17.
 */
public class ConditionFlip extends Condition {

    @Override
    public boolean evaluate(AbilityPart caller, GameBoard gameBoard, Player owner) {
        return Coin.flip() == CoinStatus.Head;
    }
}
