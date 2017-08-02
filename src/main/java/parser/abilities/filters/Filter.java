package parser.abilities.filters;

import card.Card;
import game.GameBoard;
import game.Player;
import java.util.ArrayList;
import java.util.List;

public class Filter {
    
    public List<Card> evaluate(GameBoard targetBoard, Player owner,List<Card> cards){
        List<Card> filteredCards = new ArrayList<>();
        cards.forEach(card->{
            if(evaluate(targetBoard, owner, card)){
                filteredCards.add(card);
            }
        });
        return filteredCards;
    }
    
    public boolean evaluate(GameBoard targetBoard, Player owner, Card card){
        return true;
    }
    
    public String toString(){
        return "filters nothing";
    }
    
    
}
