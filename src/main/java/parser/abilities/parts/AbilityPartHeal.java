package parser.abilities.parts;

import card.Card;
import card.PokemonCard;
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
        Card card = owner.getTarget(targetBoard, target);
        if(card instanceof PokemonCard){
            PokemonCard pokemonCard = (PokemonCard)card;
            pokemonCard.heal(amount.evaluateAsExpression(targetBoard, owner));
            return true;
        }
        return false;
    }

    @Override
    public String getDescriptionString() {
        return "Heals "+ target + " for "+amount.getDisplayString();
    }
}
