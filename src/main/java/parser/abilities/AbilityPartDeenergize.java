package parser.abilities;

import game.GameBoard;
import game.Player;
import parser.commons.TargetProperty;
import parser.commons.TokenProperty;
import parser.tokenizer.Token;

public class AbilityPartDeenergize extends AbilityPart{

    private TargetProperty target;
    private Token amount;
    
    public AbilityPartDeenergize(TargetProperty target, Token amount) {
        super("DeEnergize");
        this.target = target;
        this.amount = amount;
        
        properties.add(target);
        properties.add(new TokenProperty("amount", amount));
    }

    @Override
    public boolean use(GameBoard targetBoard, Player owner) {
        //TODO implement
        return false;
    }

    @Override
    public String getDescriptionString() {
        return "De-energize " + target + " for "+amount.getDisplayString();
    }
}
