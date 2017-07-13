package parser.abilities;

import game.GameBoard;
import game.Player;
import parser.tokenizer.TokenCondition;

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
    public boolean use(GameBoard targetBoard, Player owner) {
        return false;
    }

    @Override
    public String getDescriptionString() {
        return "Condition: "+((truePart != null)?truePart.getDescriptionString():"") + ((falsePart != null)?" else "+falsePart.getDescriptionString():"");
    }
}
