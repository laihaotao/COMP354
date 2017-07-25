package parser.commons;

import card.Card;
import game.GameBoard;
import game.Player;
import java.util.ArrayList;
import java.util.List;
import parser.tokenizer.Token;
import parser.tokenizer.TokenInteger;

public class FilterSide extends Filter {

    private final String side;
    private final Token amount;

    public FilterSide(String side, Token amount){

        this.side = side;
        this.amount = amount;
    }
    
    public List<Card> evaluate(GameBoard targetBoard, Player owner,List<Card> cards){
        List<Card> filteredCard = new ArrayList<>();
        
        int num = amount.evaluateAsExpression(targetBoard, owner);
        if(side.equalsIgnoreCase("bottom")){
            for(int i=0; i < num; i++){
                filteredCard.add(cards.get(i));
            }
        }else if(side.equalsIgnoreCase("top")){
            for(int i=cards.size()-1; i > cards.size()-num-1; i--){
                filteredCard.add(cards.get(i));
            }
        }
        return filteredCard;
    }
    
    public String toString(){
        return "filters "+side + " for " + amount.getDisplayString();
    }
}
