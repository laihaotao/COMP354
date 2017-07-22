package parser.abilities;

import game.GameBoard;
import game.Player;
import parser.commons.StatusProperty;
import parser.commons.TargetProperty;

public class AbilityPartApplystat extends AbilityPart{

    private StatusProperty statusType;
    private TargetProperty target;

    public AbilityPartApplystat(StatusProperty statusType, TargetProperty target) {
        super("ApplyStat");
        this.statusType = statusType;
        this.target = target;
        
        properties.add(statusType);
        properties.add(target);
    }

    @Override
    public boolean use(GameBoard targetBoard, Player owner) {

        return false;
    }

    @Override
    public String getDescriptionString() {
        return "Apply stat "+statusType+" on " + target;
    }
}
