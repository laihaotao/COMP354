package parser.abilities;

import game.GameBoard;
import game.Player;
import parser.commons.TargetProperty;

public class AbilityPartDestat extends AbilityPart{


    private TargetProperty targetProperty;

    public AbilityPartDestat(TargetProperty targetProperty) {
        super("DeStat");
        this.targetProperty = targetProperty;
        
        properties.add(targetProperty);
    }

    @Override
    public boolean use(GameBoard targetBoard, Player owner) {
        return false;
    }

    @Override
    public String getDescriptionString() {
        return null;
    }
}
