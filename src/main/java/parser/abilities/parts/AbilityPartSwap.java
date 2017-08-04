package parser.abilities.parts;

import card.Card;
import card.PokemonCard;
import game.GameBoard;
import game.Player;
import game.SpecialAbility;
import game.effectstatus.Effect;
import java.util.ArrayList;
import java.util.List;
import parser.abilities.properties.TargetProperty;

public class AbilityPartSwap extends AbilityPart{

    private final TargetProperty source;
    private final TargetProperty destination;
    
    public AbilityPartSwap(TargetProperty source, TargetProperty destination) {
        super("swap");
        this.source = source;
        this.destination = destination;
        
        properties.add(source);
        properties.add(destination);
    }

    @Override
    public boolean use(GameBoard targetBoard, Player owner, Card callingCard) {
        Card sourceCard = (PokemonCard) owner.getTarget(targetBoard, callingCard, source);
        Card destinationCard = (PokemonCard) owner.getTarget(targetBoard, callingCard, destination);
        if (sourceCard instanceof  PokemonCard && destinationCard instanceof PokemonCard) {
            PokemonCard p1 = (PokemonCard)sourceCard;
            PokemonCard p2 = (PokemonCard)destinationCard;
            // remove all status effects
            p1.getEffects().clear();
            
            SearchResult p1Source = getCardSource(targetBoard, p1);
            SearchResult p2Source = getCardSource(targetBoard, p2);
            
            if(p1Source != null && p2Source != null){
                if(p1Source.source.size() > 0) {
                    p1Source.source.remove(p1);
                    p1Source.source.add(p2);
                }else {
                    p1Source.player.setActivePokemon(p2);
                }
                
                if(p2Source.source.size() > 0) {
                    p2Source.source.remove(p2);
                    p2Source.source.add(p1);
                }else{
                    p2Source.player.setActivePokemon(p1);
                }
            }
            return true;
        }
        return false;
    }

    public SearchResult getCardSource(GameBoard board, Card card){
        List<Card> cards1 = getCardSource(board.getPlayer1(), card);
        List<Card> cards2 = getCardSource(board.getPlayer2(), card);
        
        if(cards1 != null){
            return new SearchResult(board.getPlayer1(), cards1);
        }
        if(cards2 != null){
            return new SearchResult(board.getPlayer2(), cards2);
        }
        
        return null;
    }
    
    private List<Card> getCardSource(Player player, Card card){
        if(player.getActivePokemon() == card)
            return new ArrayList<>();
        if(player.getHand().contains(card))
            return player.getHand();
        if(player.getBench().contains(card))
            return player.getBench();
        
        return null;
    }
    
    private class SearchResult{
        public Player player;
        public List<Card> source;
        
        public SearchResult(Player player, List<Card> source){

            this.player = player;
            this.source = source;
        }
    }
    
    @Override
    public String getDescriptionString() {
        return "Swap from " + source + " to " +destination;
    }
}
