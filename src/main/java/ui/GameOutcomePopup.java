/*
 * description:  The class of trainer card
 * author(s):    frede
 * reviewer(s):
 * date:         2017-06-01
 */

package ui;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class GameOutcomePopup {

    public static void display(String player, boolean won) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setContentText(player + " " + (won ? "won" : "lost") + " the game!");

        alert.showAndWait();
        System.exit(0);
    }

}
