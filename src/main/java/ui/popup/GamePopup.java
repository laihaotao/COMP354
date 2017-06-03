/*
 * description:  The class of trainer card
 * author(s):    frede
 * reviewer(s):
 * date:         2017-06-01
 */

package ui.popup;

import card.Card;
import game.Player;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import ui.CardView;

import java.util.List;

public class GamePopup {

    public static void displayGameResult(String player, boolean won) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setContentText(player + " " + (won ? "won" : "lost") + " the game!");

        alert.showAndWait();
        System.exit(0);
    }

    public static void displayDiscardPile(Player player, List<Card> pile) {
        Alert popup = new Alert(AlertType.NONE);
        popup.setHeaderText(null);
        popup.setContentText("List of the discard cards");

        VBox vBox = new VBox();
        ScrollPane handScroll = new ScrollPane(vBox);
        vBox.getChildren().add(handScroll);
        pile.forEach(card -> {
            CardView cardView = new CardView(player, card);
            handScroll.getChildrenUnmodifiable().add(cardView);
        });

        popup.getDialogPane().setContent(vBox);
        popup.showAndWait();
    }

}
