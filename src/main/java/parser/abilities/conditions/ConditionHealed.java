package parser.abilities.conditions;

import card.Card;
import card.PokemonCard;
import game.GameBoard;
import game.Player;
import parser.abilities.properties.TargetProperty;

public class ConditionHealed extends Condition {
    
    public TargetProperty targetProperty;
    
    public ConditionHealed(TargetProperty targetProperty){
        this.targetProperty = targetProperty;
    }

    @Override
    public boolean evaluate(GameBoard gameBoard, Player owner) {
        Card card = owner.getTarget(gameBoard, targetProperty);
        if(card instanceof PokemonCard){
            return ((PokemonCard)card).hasBeenHealed();
        }
        return false;
    }
}
