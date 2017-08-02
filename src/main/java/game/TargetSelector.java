package game;

import card.Card;
import parser.abilities.properties.TargetProperty;

/**
 * Created by frede on 2017-06-08.
 */
public  abstract class TargetSelector {
    public TargetSelector(){
      
    }
    
    private Card lastCardTarget;
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
    
    public Card getCard(GameBoard gameBoard, Player callingPlayer, TargetProperty targetProperty) {
        switch (targetProperty.target.value) {
            case "your":
                return storeAndReturn(choseYourCard(gameBoard, callingPlayer));
            case "last":
                return lastCardTarget;
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
            default: {
                return null;
            }
        }
    }
    
    private Card storeAndReturn(Card card){
        lastCardTarget = card;
        return card;
    }
    
    private Player storeAndReturn(Player player){
        lastPlayerTarget = player;
        return player;
    }
    
    public Player getLastPlayerTarget(){
        return lastPlayerTarget;
    }
    
    public Card getLastCardTarget(){
        return lastCardTarget;
    }
    
    public abstract Card choseOpponentCard(GameBoard gameBoard, Player callingPlayer);
    
    public abstract Card choseOpponentBench(GameBoard gameBoard, Player callingPlayer);
    
    public abstract Card choseYourCard(GameBoard gameBoard, Player callingPlayer);
    
    public abstract Card choseYourBench(GameBoard gameBoard, Player callingPlayer);
    
}
