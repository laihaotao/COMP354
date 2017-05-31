package ui.selections;

import card.Card;
import game.GameBoard;
import game.Player;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javax.swing.JDialog;
import parser.commons.TargetProperty;

/**
 * Created by frede on 2017-05-29.
 */
public class TargetSelector {
  
  public static Card getTarget(GameBoard board, Player callingPlayer, TargetProperty target){
      Alert alert = new Alert(AlertType.CONFIRMATION);
    
      alert.setTitle("Target Selector");
      alert.setHeaderText("Please chose the target card");
      alert.setContentText("Options: ");
      
      HashMap<ButtonType, Card> possibleResults = new HashMap<>();
      
      alert.getButtonTypes().clear();
      
      switch(target.target.value){
        case "choice":{
            switch(target.modifier.value){
              case "opponent-bench":{
                Player otherPlayer = board.getOtherPlayer(callingPlayer);
                otherPlayer.getBench().forEach((card)->{
                  ButtonType buttonType = new ButtonType(card.getCardName());
                  alert.getButtonTypes().add(buttonType);
                  possibleResults.put(buttonType, card);
                });
              }
            }
        }break;
        default:
        {
            Player otherPlayer = board.getOtherPlayer(callingPlayer);
            otherPlayer.getHand().forEach((card)->{
                ButtonType buttonType = new ButtonType(card.getCardName());
                alert.getButtonTypes().add(buttonType);
                possibleResults.put(buttonType, card);
            });
            
        }break;
      }
      
      alert.getButtonTypes().add(new ButtonType("Cancel", ButtonData.CANCEL_CLOSE));
      
      Optional<ButtonType> result = alert.showAndWait();
      
      if(result.isPresent()){
          return possibleResults.get(result.get());
      }
      
      return null;
      
  }

}
