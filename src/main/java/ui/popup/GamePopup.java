/*
 * description:  The class of trainer card
 * author(s):    frede
 * reviewer(s):
 * date:         2017-06-01
 */

package ui.popup;

import card.Card;
import card.PokemonCard;
import game.GameBoard;
import game.Player;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ui.CardView;
import ui.events.PopupOnClickListener;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class GamePopup {

    private static Logger logger = LogManager.getLogger(GamePopup.class.getName());

    public static void displayGameResult(String player, boolean won) {
        System.out.println(player + " " + (won ? "won" : "lost") + " the game!");
        Platform.runLater(() -> {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText(player + " " + (won ? "won" : "lost") + " the game!");
            alert.showAndWait();
            System.exit(0);
        });
    }

    public static void displayMessage(String msg) {
        Platform.runLater(() -> {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText(msg);
            alert.showAndWait();
        });
    }

    public static void displayDiscardPile(GameBoard gameboard, Player player,
                                          List<Card> pile, PopupOnClickListener listener) {

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

                    VBox box = new VBox();
                    for (Card c : pile) {
                        Button button = new Button(new CardView(gameboard, player, c, false)
                                .toString());
                        button.setOnMouseClicked(event -> {
                            listener.onClick(c);
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


    public static void displayPokemonsInHand(GameBoard gameboard, Player player,
                                             List<PokemonCard> pokemonInHand,
                                             PopupOnClickListener listener) {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        URL location = ClassLoader.getSystemClassLoader().getResource("ui/select.fxml");
        loader.setLocation(location);
        try {
            BorderPane borderPane = loader.load();
            Scene scene = new Scene(borderPane);
            ObservableList<Node> childrenList = borderPane.getChildren();
            for (Node child : childrenList) {
                if (child instanceof Label) {
                    Label label = (Label) child;
                    label.setText("Select Active Pokemon");
                }
                if (child instanceof ScrollPane) {
                    ScrollPane scrollPane = (ScrollPane) child;

                    VBox box = new VBox();
                    for (PokemonCard c : pokemonInHand) {
                        Button button = new Button(new CardView(gameboard, player, c, false)
                                .toString());
                        button.setOnMouseClicked(event -> {
                            listener.onClick(c);
                            stage.hide();
                        });
                        box.getChildren().add(button);

                    }

                    scrollPane.setContent(box);
                }
            }

            stage.setScene(scene);
            stage.setAlwaysOnTop(true);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void displaySearchCards(GameBoard gameboard, Player player,
                                          List<Card> filterList,
                                          PopupOnClickListener listener) {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        URL location = ClassLoader.getSystemClassLoader().getResource("ui/select.fxml");
        loader.setLocation(location);
        try {
            BorderPane borderPane = loader.load();
            Scene scene = new Scene(borderPane);
            ObservableList<Node> childrenList = borderPane.getChildren();
            for (Node child : childrenList) {
                if (child instanceof Label) {
                    Label label = (Label) child;
                    label.setText("Searching Result");
                }
                if (child instanceof ScrollPane) {
                    ScrollPane scrollPane = (ScrollPane) child;

                    VBox box = new VBox();
                    for (Card c : filterList) {
                        Button button = new Button(new CardView(gameboard, player, c, false)
                                .toString());
                        button.setOnMouseClicked(event -> {
                            listener.onClick(c);
                            stage.hide();
                        });
                        box.getChildren().add(button);
                    }

                    scrollPane.setContent(box);
                }
            }

            stage.setScene(scene);
            stage.setAlwaysOnTop(true);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
