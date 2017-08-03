package parser.abilities.parts;

import card.Card;
import card.PokemonCard;
import game.GameBoard;
import game.Player;
import parser.abilities.properties.TargetProperty;
import parser.abilities.properties.TokenProperty;
import parser.tokenizer.Token;

public class AbilityPartRedamage extends AbilityPart{
    private TargetProperty source;
    private TargetProperty target;
    private Token amount;


    public AbilityPartRedamage(TargetProperty source, TargetProperty target, Token amount) {
        super("ReDamage");
        this.source = source;
        this.target = target;
        this.amount = amount;

        properties.add(source);
        properties.add(target);
        properties.add(new TokenProperty("amount", amount));
    }

    @Override
    public boolean use(GameBoard targetBoard, Player owner) {
        Card damageSource = owner.getTarget(targetBoard, source);
        int damageAmount = amount.evaluateAsExpression(targetBoard, owner);
        
        boolean didSomething = false;
        for(int i=0; i < damageAmount; i++)
        {
            Card damageTarget = owner.getTarget(targetBoard, target);
            if(damageTarget instanceof PokemonCard && damageSource instanceof  PokemonCard){
                PokemonCard pokemonSource = (PokemonCard)damageSource;
                PokemonCard pokemonTarget = (PokemonCard)damageTarget;
                didSomething = true;
                pokemonSource.setDamage(pokemonSource.getDamage()-1);
                targetBoard.applyDamageToCard(pokemonTarget, 1);
            }else
            {
                break;
            }
        }
        //TODO implement
        return didSomething;
    }

    @Override
    public String getDescriptionString() {
        return "De-damage from " + source + " to " + target + " "+amount.getDisplayString() + "times";
    }
}
