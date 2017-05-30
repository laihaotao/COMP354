package ui;

import game.Player;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
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
    private HBox activeCard;

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
        
        ScrollPane handScroll = new ScrollPane(handCards);

        
        
        
        
        benchCards = new HBox();
        benchCards.getStyleClass().add("Bench");
        activeCard = new HBox();

        //Add them in "reverse" order for them to display from bottom to top
        centerCardArea.getChildren().addAll(activeCard, benchCards, handScroll );

        deck = new Label("");
        deck.getStyleClass().add("Deck");

        this.setCenter(centerCardArea);
        this.setRight(deck);
        refreshView();
    }

    public void refreshView(){

        deck.setText(String.valueOf(player.getDeck().size()));

        //Add hand cards
        handCards.getChildren().clear();

        Label handLabel = new Label("Hand");
        handLabel.setOnMouseClicked(event->registeredListeners.forEach(listener->listener.onHandCardClicked(player, null)));

        handCards.getChildren().add(handLabel);

        //For now, just add 5 cardViews to make sure it displays

        player.getHand().forEach((card)->{
            CardView cardView = new CardView(card);

            cardView.setOnMouseClicked((event -> {
                registeredListeners.forEach(listener->listener.onHandCardClicked(player, card));
            }));

            handCards.getChildren().add(cardView);
        });


        //Add bench cards
        benchCards.getChildren().clear();

        Label benchLabel = new Label("Bench");
        benchLabel.setOnMouseClicked(event -> registeredListeners.forEach(listener->listener.onBenchCardClicked(player, null)));

        benchCards.getChildren().add(benchLabel);

        player.getBench().forEach((card)->{
            CardView cardView = new CardView(card);

            cardView.setOnMouseClicked((event -> {
                registeredListeners.forEach(listener->listener.onBenchCardClicked(player, card));
            }));

            benchCards.getChildren().add(cardView);
        });


        //set the current active card
        activeCard.getChildren().clear();

        Label activeLabel = new Label("Active");
        activeLabel.setOnMouseClicked(event->registeredListeners.forEach(listener->listener.onActiveCardClicked(player, null)));

        activeCard.getChildren().add(activeLabel);

        if(player.getActivePokemon() != null){

            CardView cardView = new CardView(player.getActivePokemon());

            cardView.setOnMouseClicked((event -> {
                registeredListeners.forEach(listener->listener.onActiveCardClicked(player, player.getActivePokemon()));
            }));

            activeCard.getChildren().add(cardView);
        }

    }
    
    public void registerListener(PlayerViewListener listener){
        registeredListeners.add(listener);
    }

}
