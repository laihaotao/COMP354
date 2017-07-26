package parser.abilities.filters;

import card.Card;
import card.PokemonCard;
import game.GameBoard;
import game.Player;
import java.util.ArrayList;
import java.util.List;
import parser.abilities.properties.TargetProperty;

public class FilterEvolveFrom extends Filter {

    private TargetProperty target;

    public FilterEvolveFrom(TargetProperty target){

        this.target = target;
    }

    public List<Card> evaluate(GameBoard targetBoard, Player owner, List<Card> cards){
        List<Card> filteredCards = new ArrayList<>();
        Card targetCard = owner.getTarget(targetBoard, target);
        if(targetCard instanceof PokemonCard) {
            PokemonCard targetPokemon = (PokemonCard)targetCard; 
            cards.forEach(card -> {
                if (card instanceof PokemonCard) {
                    PokemonCard pokemonCard = (PokemonCard)card;
                    if(pokemonCard.getEvolvesFrom().equalsIgnoreCase(targetPokemon.getCardName())){
                        filteredCards.add(card);    
                    }
                    
                }
            });
        }
        return filteredCards;
    }
    
    public String toString(){
        return "filters evolve-from "+target;
    }
}
