package ui.selections;

import card.Card;
import game.Player;
import java.util.HashMap;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;

/**
 * Created by frede on 2017-06-01.
 */
public class RewardSelector {
  
  public static Card getReward(Player player){
    Alert alert = new Alert(AlertType.CONFIRMATION);

    alert.setTitle("Target Selector");
    alert.setHeaderText("Please chose the target card");
    alert.setContentText("Options: ");

    HashMap<ButtonType, Card> possibleResults = new HashMap<>();

    alert.getButtonTypes().clear();

    player.getPrizes().forEach(card -> {
        ButtonType buttonType = new ButtonType("Prize");
        alert.getButtonTypes().add(buttonType);
        possibleResults.put(buttonType, card);
    });
    

    alert.getButtonTypes().add(new ButtonType("Cancel", ButtonData.CANCEL_CLOSE));

    Optional<ButtonType> result = alert.showAndWait();

    if(result.isPresent()){
      return possibleResults.get(result.get());
    }

    return null;    
  }
}
