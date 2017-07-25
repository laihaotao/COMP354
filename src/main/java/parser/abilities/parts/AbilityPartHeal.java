package parser.abilities.parts;

import game.GameBoard;
import game.Player;
import parser.abilities.properties.TargetProperty;
import parser.abilities.properties.TokenProperty;
import parser.tokenizer.Token;

public class AbilityPartHeal extends AbilityPart{

    private TargetProperty target;
    private Token amount;
    
    public AbilityPartHeal(TargetProperty target, Token amount) {
        super("heal");
        
        this.target = target;
        this.amount = amount;
        
        properties.add(target);
        properties.add(new TokenProperty("Amount", amount));
    }

    @Override
    public boolean use(GameBoard targetBoard, Player owner) {
        return false;
    }

    @Override
    public String getDescriptionString() {
        return "Heals "+ target + " for "+amount.getDisplayString();
    }
}
