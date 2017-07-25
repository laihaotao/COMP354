package parser.abilities.conditions;

import game.GameBoard;
import game.Player;
import parser.abilities.Property.TargetProperty;

public class ConditionHealed extends Condition {
    
    public TargetProperty targetProperty;
    
    public ConditionHealed(TargetProperty targetProperty){
        this.targetProperty = targetProperty;
    }

    @Override
    public boolean evaluate(GameBoard gameBoard, Player owner) {
        return false;
    }
}
