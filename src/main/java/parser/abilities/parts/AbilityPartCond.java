package parser.abilities.parts;

import game.GameBoard;
import game.Player;
import java.util.List;
import parser.abilities.conditions.Condition;

/**
 * Created by frede on 2017-07-07.
 */
public class AbilityPartCond extends AbilityPart{

    public Condition condition;
    
    public List<AbilityPart> trueParts;
    public List<AbilityPart> falseParts;
    
    public AbilityPartCond(Condition condition, List<AbilityPart> trueParts, List<AbilityPart> falseParts) {
        super("cond");
        this.condition = condition;
        this.trueParts = trueParts;
        this.falseParts = falseParts;
    }
    
    @Override
    public boolean use(GameBoard targetBoard, Player owner) {
        if(condition == null) {
            return false;
        }
        if(condition.evaluate(targetBoard, owner)){
            trueParts.forEach(part->{
                part.use(targetBoard, owner);
            });
        }else{
            falseParts.forEach(part->{
                part.use(targetBoard, owner);
            });
        }
        return true;
    }

    @Override
    public String getDescriptionString() {
        
        StringBuilder descriptionBuilder = new StringBuilder();
        descriptionBuilder.append((condition==null?"Condition":condition.getClass().getSimpleName())+":");
        
        trueParts.forEach(part->{
            if(part != null) {
                descriptionBuilder.append(part.getDescriptionString());
                descriptionBuilder.append(" and ");
            }
        });
        descriptionBuilder.replace(descriptionBuilder.length()-5, descriptionBuilder.length(), "");

        descriptionBuilder.append(" else ");
        
        falseParts.forEach(part->{
            if(part != null) {
                descriptionBuilder.append(part.getDescriptionString());
                descriptionBuilder.append(" and ");
            }
        });
        descriptionBuilder.replace(descriptionBuilder.length()-5, descriptionBuilder.length(), "");

        return descriptionBuilder.toString();
    }
}
