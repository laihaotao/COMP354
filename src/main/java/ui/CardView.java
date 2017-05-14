package ui;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * Displays info about a card
 */
public class CardView extends BorderPane{

    //Info box that is visible on top of the card
    private VBox topInfo;

    //TODO A Card instance should be passed to this once it is properly implemented
    public CardView(){

        this.getStyleClass().add("Card");

        topInfo = new VBox();

        //TODO display actual card info

        topInfo.getChildren().add(new Label("A Card"));

        setTop(topInfo);
    }
}
