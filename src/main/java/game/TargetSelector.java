package game;

import card.Card;
import parser.commons.TargetProperty;
import ui.selections.TargetSelectorUI;

/**
 * Created by frede on 2017-06-08.
 */
public class TargetSelector {
    public TargetSelector(){
      
    }
    
    public Card getCard(GameBoard gameBoard, Player callingPlayer, TargetProperty targetProperty){
      switch(targetProperty.target.value){
        case "choice":{
            return getChoiceCard(gameBoard, callingPlayer, targetProperty);
        }

        case "opponent-active":{
            return getOpponentActive(gameBoard, callingPlayer);
        }

        default: {
          return null;
        }
      }
    }
    
    public Card getChoiceCard(GameBoard gameBoard, Player callingPlayer, TargetProperty targetProperty){
        return TargetSelectorUI.getTarget(gameBoard, callingPlayer, targetProperty);
    }
    
    public Card getOpponentActive(GameBoard gameBoard, Player callingPlayery){
        return gameBoard.getOtherPlayer(callingPlayery).getActivePokemon();
    }
}
