package parser.commons;

import card.Card;
import card.PokemonCard;
import game.GameBoard;
import game.Player;

public class FilterPokemon extends Filter{
    public String category = null;

    public void setCategory(String category){
        this.category = category;
    }

    @Override
    public boolean evaluate(GameBoard targetBoard, Player owner,Card card) {
        return card instanceof PokemonCard;
    }

    public String toString(){
        return "filter pokemons " + ((category!=null)?" of cat "+category:"");
    }

}
