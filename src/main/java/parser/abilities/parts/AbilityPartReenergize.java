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

        
        if( energySource instanceof  PokemonCard){
            PokemonCard pokemonSource = (PokemonCard)energySource;
            
            didSomething = true;
            EnergyCost transferEnergy = pokemonSource.getEnergyAttached().deenergy(energyAmount);

            for(int i=0; i < transferEnergy.fight; i++){
                Card energyTarget = owner.getTarget(targetBoard,callingCard, target);
                if(energyTarget instanceof PokemonCard) {
                    PokemonCard pokemonTarget = (PokemonCard) energyTarget;
                    pokemonTarget.getEnergyAttached().fight++;
                }
            }

            for(int i=0; i < transferEnergy.psychic; i++){
                Card energyTarget = owner.getTarget(targetBoard,callingCard, target);
                if(energyTarget instanceof PokemonCard) {
                    PokemonCard pokemonTarget = (PokemonCard) energyTarget;
                    pokemonTarget.getEnergyAttached().psychic++;
                }
            }

            for(int i=0; i < transferEnergy.lightning; i++){
                Card energyTarget = owner.getTarget(targetBoard,callingCard, target);
                if(energyTarget instanceof PokemonCard) {
                    PokemonCard pokemonTarget = (PokemonCard) energyTarget;
                    pokemonTarget.getEnergyAttached().lightning++;
                }
            }

            for(int i=0; i < transferEnergy.water; i++){
                Card energyTarget = owner.getTarget(targetBoard,callingCard, target);
                if(energyTarget instanceof PokemonCard) {
                    PokemonCard pokemonTarget = (PokemonCard) energyTarget;
                    pokemonTarget.getEnergyAttached().water++;
                }
            }
            for(int i=0; i < transferEnergy.colorless; i++){
                Card energyTarget = owner.getTarget(targetBoard,callingCard, target);
                if(energyTarget instanceof PokemonCard) {
                    PokemonCard pokemonTarget = (PokemonCard) energyTarget;
                    pokemonTarget.getEnergyAttached().colorless++;
                }
            }
            
            
        }   
        
        //TODO implement
        return didSomething;
    }

    @Override
    public String getDescriptionString() {
        return "Re-energize from " + source + " to " + target + " "+amount.getDisplayString() + "times";
    }
}
