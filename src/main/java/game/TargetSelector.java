package game;

import card.Card;
import java.util.ArrayList;
import java.util.List;
import parser.commons.TargetProperty;
import ui.selections.TargetSelectorUI;

/**
 * Created by frede on 2017-06-08.
 */
public  abstract class TargetSelector {
    public TargetSelector(){
      
    }
    
    public Player getPlayer(GameBoard gameBoard, Player callingPlayer, TargetProperty target){
        switch(target.target.value){
            case "opponent":
                return gameBoard.getOtherPlayer(callingPlayer);
            case "your":
                return callingPlayer;
        }
        return null;
    }
    
    public Card getCard(GameBoard gameBoard, Player callingPlayer, TargetProperty targetProperty) {
        switch (targetProperty.target.value) {
            case "choice": {
                switch(targetProperty.modifier.value){
                    case "opponent":
                        return choseOpponentCard(gameBoard, callingPlayer);
                    case "your":
                        return choseYourCard(gameBoard, callingPlayer);
                    case "opponent-bench":
                        return choseOpponentBench(gameBoard, callingPlayer);
                    case "your-bench":
                        return choseYourBench(gameBoard, callingPlayer);
                }
            }
            case "your-active": {
                return callingPlayer.getActivePokemon();
            }
            case "opponent-active": {
                return gameBoard.getOtherPlayer(callingPlayer).getActivePokemon();
            }
            case "opponent":
                return choseOpponentCard(gameBoard, callingPlayer);
            default: {
                return null;
            }
        }
    }
    
    public abstract Card choseOpponentCard(GameBoard gameBoard, Player callingPlayer);
    
    public abstract Card choseOpponentBench(GameBoard gameBoard, Player callingPlayer);
    
    public abstract Card choseYourCard(GameBoard gameBoard, Player callingPlayer);
    
    public abstract Card choseYourBench(GameBoard gameBoard, Player callingPlayer);
    
}
