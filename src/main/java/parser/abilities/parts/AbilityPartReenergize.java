package parser.abilities.parts;

import card.Card;
import card.PokemonCard;
import game.GameBoard;
import game.Player;
import parser.abilities.properties.TargetProperty;
import parser.abilities.properties.TokenProperty;
import parser.cards.EnergyCost;
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
    public boolean use(GameBoard targetBoard, Player owner, Card callingCard) {
        Card energySource = owner.getTarget(targetBoard,callingCard, source);
        int energyAmount = amount.evaluateAsExpression(targetBoard, owner);

        boolean didSomething = false;

        Card energyTarget = owner.getTarget(targetBoard,callingCard, target);
        if(energyTarget instanceof PokemonCard && energySource instanceof  PokemonCard){
            PokemonCard pokemonSource = (PokemonCard)energySource;
            PokemonCard pokemonTarget = (PokemonCard)energySource;
            didSomething = true;
            EnergyCost transferEnergy = pokemonSource.getEnergyAttached().deenergy(energyAmount);
            pokemonTarget.getEnergyAttached().add(transferEnergy);
        }   
        
        //TODO implement
        return didSomething;
    }

    @Override
    public String getDescriptionString() {
        return "Re-energize from " + source + " to " + target + " "+amount.getDisplayString() + "times";
    }
}
