package parser.abilities;

import game.GameBoard;
import game.Player;
import parser.commons.Condition;
import parser.tokenizer.TokenCondition;

/**
 * Created by frede on 2017-07-07.
 */
public class AbilityPartCond extends AbilityPart{

    public Condition condition;
    
    public AbilityPart truePart;
    public AbilityPart falsePart;
    
    public AbilityPartCond(Condition condition) {
        super("cond");
        this.condition = condition;
    }

    public void setResults(AbilityPart truePart, AbilityPart falsePart){
        this.truePart = truePart;
        this.falsePart = falsePart;
    }
    
    @Override
    public boolean use(GameBoard targetBoard, Player owner) {
        if(condition == null) {
            return false;
        }
        if(condition.evaluate(targetBoard, owner)){
            if(truePart != null){
                return truePart.use(targetBoard, owner);
            }
        }else{
            if(falsePart != null){
                return falsePart.use(targetBoard, owner);
            }
        }
        return true;
    }

    @Override
    public String getDescriptionString() {
        return (condition==null?"Condition":condition.getClass().getSimpleName())+":"+((truePart != null)?truePart.getDescriptionString():"") + ((falsePart != null)?" else "+falsePart.getDescriptionString():"");
    }
}
