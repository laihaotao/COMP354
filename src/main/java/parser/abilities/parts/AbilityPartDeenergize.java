package parser.abilities.parts;

import card.PokemonCard;
import game.GameBoard;
import game.Player;
import parser.abilities.properties.TargetProperty;
import parser.abilities.properties.TokenProperty;
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
        PokemonCard target = (PokemonCard) owner.getTarget(targetBoard, this.target);
        int energyAmount = this.amount.evaluateAsExpression(targetBoard, owner);
        target.deenergy(energyAmount);
        return true;
    }

    @Override
    public String getDescriptionString() {
        return "De-energize " + target + " for "+amount.getDisplayString();
    }
}
