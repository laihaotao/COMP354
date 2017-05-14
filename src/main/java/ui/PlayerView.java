package ui;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * Class used to display info about a player
 * including current deck, hand, bench, ...
 */
public class PlayerView extends BorderPane {

    //List that constains player hand cards
    private HBox handCards;

    //List that contains player bench cards
    private HBox benchCards;

    //Pane that constains the active card. This is not an HBox since their can only be one active pokemon card
    private StackPane activeCard;

    private Label deck;

    //TODO this needs to take a player object
    public PlayerView(){

        //VBox that constians the hand, bench and active pokemon
        VBox centerCardArea = new VBox();

        handCards = new HBox();
        benchCards = new HBox();
        activeCard = new StackPane();

        //Add them in "reverse" order for them to display from bottom to top
        centerCardArea.getChildren().addAll(activeCard, benchCards, handCards );

        deck = new Label("30");
        deck.getStyleClass().add("Deck");

        this.setCenter(centerCardArea);
        this.setRight(deck);
        refreshView();
    }

    public void refreshView(){

        //TODO display actual player card info

        //Add hand cards
        handCards.getChildren().clear();

        handCards.getChildren().add(new Label("Hand"));

        //For now, just add 5 cardViews to make sure it displays
        for(int i=0; i < 5; i++){
            handCards.getChildren().add(new CardView());
        }


        //Add bench cards
        benchCards.getChildren().clear();
        benchCards.getChildren().add(new Label("Bench"));

        //For now, just add 3 cardViews to make sure it displays
        for(int i=0; i < 3; i++){
            benchCards.getChildren().add(new CardView());
        }


        //set the current active card
        activeCard.getChildren().clear();
        activeCard.getChildren().add(new CardView());
    }

}
