package parser.abilities.conditions;

import card.Card;
import game.GameBoard;
import game.Player;
import parser.abilities.parts.AbilityPart;
import parser.tokenizer.TokenCondition;

public class ConditionToken extends Condition {

    private TokenCondition tokenCondition;

    public ConditionToken(TokenCondition tokenCondition){

        this.tokenCondition = tokenCondition;
    }

    @Override
    public boolean evaluate(AbilityPart caller, GameBoard gameBoard, Player owner, Card callingCard) {
        return tokenCondition.evaluateAsExpression(gameBoard, owner) == 1;
    }
}
