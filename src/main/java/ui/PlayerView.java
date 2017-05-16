package ui;

import game.Player;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import ui.events.PlayerViewListener;

/**
 * Class used to display info about a player
 * including current deck, hand, bench, ...
 */
public class PlayerView extends BorderPane {

    private Player player;
    
    //List that constains player hand cards
    private HBox handCards;

    //List that contains player bench cards
    private HBox benchCards;

    //Pane that constains the active card. This is not an HBox since their can only be one active pokemon card
    private StackPane activeCard;

    private Label deck;

    /**
     * List of registered listeners
     */
    private List<PlayerViewListener> registeredListeners = new ArrayList<PlayerViewListener>();
    
    public PlayerView(Player player){
        
        this.player = player;

        //VBox that constians the hand, bench and active pokemon
        VBox centerCardArea = new VBox();
        centerCardArea.getStyleClass().add("CardArea");

        handCards = new HBox();
        handCards.getStyleClass().add("Hand");

        benchCards = new HBox();
        benchCards.getStyleClass().add("Bench");
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
            
            CardView cardView = new CardView();
            
            cardView.setOnMouseClicked((event -> {
                registeredListeners.forEach(listener->listener.onHandCardClicked(player, null));
            }));
            
            handCards.getChildren().add(cardView);
        }


        //Add bench cards
        benchCards.getChildren().clear();
        benchCards.getChildren().add(new Label("Bench"));

        //For now, just add 3 cardViews to make sure it displays
        for(int i=0; i < 3; i++){
            CardView cardView = new CardView();

            cardView.setOnMouseClicked((event -> {
                registeredListeners.forEach(listener->listener.onBenchCardClicked(player, null));
            }));
            
            benchCards.getChildren().add(cardView);
        }


        //set the current active card
        activeCard.getChildren().clear();

        CardView cardView = new CardView();

        cardView.setOnMouseClicked((event -> {
            registeredListeners.forEach(listener->listener.onActiveCardClicked(player, null));
        }));
        
        activeCard.getChildren().add(cardView);
    }
    
    public void registerListener(PlayerViewListener listener){
        registeredListeners.add(listener);
    }

}
