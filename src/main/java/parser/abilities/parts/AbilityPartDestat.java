package parser.abilities.parts;

import card.Card;
import card.PokemonCard;
import game.GameBoard;
import game.Player;
import game.effectstatus.Normal;
import parser.abilities.properties.TargetProperty;

public class AbilityPartDestat extends AbilityPart{


    private TargetProperty targetProperty;

    public AbilityPartDestat(TargetProperty targetProperty) {
        super("DeStat");
        this.targetProperty = targetProperty;
        
        properties.add(targetProperty);
    }

    @Override
    public boolean use(GameBoard targetBoard, Player owner, Card callingCard) {
        Card card = owner.getTarget(targetBoard,callingCard, targetProperty);
        if(card instanceof PokemonCard){
            PokemonCard pokemonCard = (PokemonCard)card;
            pokemonCard.setEffect(new Normal(pokemonCard));
            return true;
        }
        
        return false;
    }

    @Override
    public String getDescriptionString() {
        return null;
    }
}
