package game;

import card.Card;
import java.util.ArrayList;
import java.util.List;
import parser.abilities.properties.TargetProperty;

/**
 * Created by frede on 2017-06-08.
 */
public  abstract class TargetSelector {
    public TargetSelector(){
      
    }
    
    private List<Card> targetBuffer = new ArrayList<>();

    private Player lastPlayerTarget;
    
    public Player getPlayer(GameBoard gameBoard, Player callingPlayer, TargetProperty target){
        switch(target.target.value){
            case "opponent":
                return gameBoard.getOtherPlayer(callingPlayer);
            case "your":
                return callingPlayer;
            case "last":
                return lastPlayerTarget;
        }
        return null;
    }
    
    public Card getCard(GameBoard gameBoard, Player callingPlayer, Card callingCard, TargetProperty targetProperty) {
        switch (targetProperty.target.value) {
            case "your":
                return storeAndReturn(choseYourCard(gameBoard, callingPlayer));
            case "last":
                return targetBuffer.get(targetBuffer.size()-1);
            case "choice": {
                switch(targetProperty.modifier.value){
                    case "opponent":
                        return storeAndReturn(choseOpponentCard(gameBoard, callingPlayer));
                    case "your":
                        return storeAndReturn(choseYourCard(gameBoard, callingPlayer));
                    case "opponent-bench":
                        return storeAndReturn(choseOpponentBench(gameBoard, callingPlayer));
                    case "your-bench":
                        return storeAndReturn(choseYourBench(gameBoard, callingPlayer));
                }
            }
            case "your-active": {
                return storeAndReturn(callingPlayer.getActivePokemon());
            }
            case "opponent-active": {
                return storeAndReturn(gameBoard.getOtherPlayer(callingPlayer).getActivePokemon());
            }
            case "opponent":
                return storeAndReturn(choseOpponentCard(gameBoard, callingPlayer));
            case "self":
                return callingCard;
            default: {
                return null;
            }
        }
    }
    
    private Card storeAndReturn(Card card){
        targetBuffer.add(card);
        return card;
    }
    
    private Player storeAndReturn(Player player){
        lastPlayerTarget = player;
        return player;
    }
    
    public Player getLastPlayerTarget(){
        return lastPlayerTarget;
    }
    
    public Card getLastCardTarget(int rollback){
        return targetBuffer.get(targetBuffer.size()-1-rollback);
    }
    
    public abstract Card choseOpponentCard(GameBoard gameBoard, Player callingPlayer);
    
    public abstract Card choseOpponentBench(GameBoard gameBoard, Player callingPlayer);
    
    public abstract Card choseYourCard(GameBoard gameBoard, Player callingPlayer);
    
    public abstract Card choseYourBench(GameBoard gameBoard, Player callingPlayer);
    
}
