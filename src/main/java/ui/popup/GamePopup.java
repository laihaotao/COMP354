/*
 * description:  The class of trainer card
 * author(s):    frede
 * reviewer(s):
 * date:         2017-06-01
 */

package ui.popup;

import card.Card;
import game.Player;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ui.CardView;
import ui.events.DiscardPileOnClickListener;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class GamePopup {

    private static Logger logger = LogManager.getLogger(GamePopup.class.getName());

    public static void displayGameResult(String player, boolean won) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setContentText(player + " " + (won ? "won" : "lost") + " the game!");

        alert.showAndWait();
        System.exit(0);
    }

    public static void displayDiscardPile(Player player, List<Card> pile,
                                          DiscardPileOnClickListener listener) {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        URL location = ClassLoader.getSystemClassLoader().getResource("ui/discardpile.fxml");
        logger.debug("discardpile.fxml file's location: " + location);
        loader.setLocation(location);
        try {
            BorderPane borderPane = loader.load();
            Scene scene = new Scene(borderPane);

            ObservableList<Node> childrenList = borderPane.getChildren();
            for (Node child : childrenList) {
                if (child instanceof ScrollPane) {
                    ScrollPane scrollPane = (ScrollPane) child;

                    // todo now only for testing purpose, it will display the hand cards
                    VBox box = new VBox();
                    for (Card c : player.getHand()) {
                        Button button = new Button(new CardView(player, c).toString());
                        button.setOnMouseClicked(event -> {
                            listener.onClickDiscardCard(c);
                        });
                        box.getChildren().add(button);
                    }

                    scrollPane.setContent(box);
                }
            }

            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
