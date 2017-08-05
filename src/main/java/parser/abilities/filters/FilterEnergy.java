package parser.abilities.filters;

import card.Card;
import card.EnergyCard;
import game.GameBoard;
import game.Player;

public class FilterEnergy extends Filter{
    public String category = null;

    public void setCategory(String category){
        this.category = category;
    }

    @Override
    public boolean evaluate(GameBoard targetBoard, Player owner,Card card) {
        if(category != null){
            if(card instanceof EnergyCard){
                EnergyCard energyCard = (EnergyCard)card;
                if(category.equalsIgnoreCase(energyCard.getEnergyType().toString()));
            }
        }
        return card instanceof EnergyCard;
    }

    public String toString(){
        return "filter pokemons " + ((category!=null)?" of cat "+category:"");
    }
}
