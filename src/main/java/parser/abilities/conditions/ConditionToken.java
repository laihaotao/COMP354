package parser.abilities.conditions;

import game.GameBoard;
import game.Player;
import parser.tokenizer.TokenCondition;

public class ConditionToken extends Condition {

    private TokenCondition tokenCondition;

    public ConditionToken(TokenCondition tokenCondition){

        this.tokenCondition = tokenCondition;
    }

    @Override
    public boolean evaluate(GameBoard gameBoard, Player owner) {
        return tokenCondition.evaluateAsExpression(gameBoard, owner) == 1;
    }
}
