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
        switch(target.target.value){
            case "your":{
                //TODO shuffle
            }break;
            
            case "opponent":{
                //TODO shuffle
            }break;
        }
        return false;
    }

    @Override
    public String getDescriptionString() {
        return "Suffles " + target;
    }
}
