package ui;

import game.Player;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * Created by frede on 2017-06-01.
 */
public class GameOutcomePopup {
  
  public static void display(String player, boolean won){
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setContentText(player + " "+(won? "won":"lost") + " the game!");

    alert.showAndWait();
    System.exit(0);
  }

}
