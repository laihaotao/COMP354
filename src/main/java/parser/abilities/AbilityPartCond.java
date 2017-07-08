package parser.abilities;

import game.GameBoard;
import game.Player;

/**
 * Created by frede on 2017-07-07.
 */
public class AbilityPartCond extends AbilityPart{

    public AbilityPart truePart;
    public AbilityPart falsePart;
    
    public AbilityPartCond() {
        super("cond");
    }

    public void setResults(AbilityPart truePart, AbilityPart falsePart){
        this.truePart = truePart;
        this.falsePart = falsePart;
    }
    
    @Override
    public void use(GameBoard targetBoard, Player owner) {
        
    }

    @Override
    public String getDescriptionString() {
        return null;
    }
}
