package parser.abilities.filters;

import card.Card;
import card.PokemonCard;
import game.GameBoard;
import game.Player;

public class FilterCategory extends Filter{
    private String category;
    
    public FilterCategory(String category){

        this.category = category;
    }
    public boolean evaluate(GameBoard targetBoard, Player owner, Card card){
        if(card instanceof PokemonCard){
            PokemonCard pokemonCard = (PokemonCard)card;
            return pokemonCard.getPokemonType().equalsIgnoreCase(category);
        }
        return false;
    }
    public String toString(){
        return "Filters cards of type "+category;
    }
}
