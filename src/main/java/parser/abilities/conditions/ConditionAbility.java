package parser.abilities.conditions;

import card.Card;
import game.GameBoard;
import game.Player;
import parser.abilities.parts.AbilityPart;

public class ConditionAbility extends Condition{

    AbilityPart abilityPart;
    
    public ConditionAbility(AbilityPart abilityPart){
        this.abilityPart = abilityPart;
    }
    
    @Override
    public boolean evaluate(AbilityPart caller, GameBoard gameBoard, Player owner, Card callingCard) {
        //TODO implement
        return abilityPart.use(gameBoard, owner, callingCard);
    }
}
