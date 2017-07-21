package parser.abilities;

import game.GameBoard;
import game.Player;
import parser.commons.TargetProperty;
import parser.commons.TokenProperty;
import parser.tokenizer.Token;

public class AbilityPartReenergize extends AbilityPart{
    
    private TargetProperty source;
    private TargetProperty target;
    private Token amount;

    public AbilityPartReenergize(TargetProperty source, TargetProperty target, Token amount) {
        super("DeEnergize");
        this.source = source;
        this.target = target;
        this.amount = amount;

        properties.add(source);
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
        return "De-energize from " + source + " to " + target + " "+amount.getDisplayString() + "times";
    }
}
