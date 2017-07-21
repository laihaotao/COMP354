package parser.abilities;

import game.GameBoard;
import game.Player;
import parser.commons.TargetProperty;

public class AbilityPartShuffle extends AbilityPart{

    private TargetProperty target;

    public AbilityPartShuffle(TargetProperty target) {
        super("shuffle");
        this.target = target;
        
        properties.add(target);
    }

    @Override
    public boolean use(GameBoard targetBoard, Player owner) {
        return false;
    }

    @Override
    public String getDescriptionString() {
        return "Suffles " + target;
    }
}
