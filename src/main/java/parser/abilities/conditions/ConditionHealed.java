package parser.abilities.conditions;

import card.Card;
import card.PokemonCard;
import game.GameBoard;
import game.Player;
import parser.abilities.parts.AbilityPart;
import parser.abilities.properties.TargetProperty;

public class ConditionHealed extends Condition {
    
    public TargetProperty targetProperty;
    
    public ConditionHealed(TargetProperty targetProperty){
        this.targetProperty = targetProperty;
    }

    @Override
    public boolean evaluate(AbilityPart caller, GameBoard gameBoard, Player owner, Card callingCard) {
        Card card = owner.getTarget(gameBoard, callingCard, targetProperty);
        if(card instanceof PokemonCard){
            return ((PokemonCard)card).hasBeenHealed();
        }
        return false;
    }
}
