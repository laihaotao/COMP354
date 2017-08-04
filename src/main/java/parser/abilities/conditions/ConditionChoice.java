package parser.abilities.conditions;

import card.Card;
import game.GameBoard;
import game.Player;
import parser.abilities.parts.AbilityPart;

public class ConditionChoice extends Condition{
    
    public ConditionChoice()
    {
        
    }
    
    @Override
    public boolean evaluate(AbilityPart caller, GameBoard gameBoard, Player owner, Card callingCard) {
        return owner.ShouldDoAbility(gameBoard, caller);
    }
}
