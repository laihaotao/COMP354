/*
 * description:  Class used to display info about a player
 *               including current deck, hand, bench
 * author(s):    frede
 * reviewer(s):
 * date:         2017-05-17
 */

package ui;

import card.PokemonCard;
import game.Player;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ui.events.PlayerViewListener;

import java.util.ArrayList;
import java.util.List;

public class PlayerView extends BorderPane {

    private Player player;

    //List that constains player hand cards
    private HBox handCards;

    //List that contains player bench cards
    private HBox benchCards;

    //Pane that constains the active card. This is not an HBox since their can only be one active
    // pokemon card
    private HBox activeCard;

    private Label deck;

    private CardView discardPile;

    private VBox pileBox;

    private Button discardPileBtn;

    /**
     * List of registered listeners
     */
    private List<PlayerViewListener> registeredListeners = new ArrayList<>();

    public PlayerView(Player player, boolean directionUp) {

        this.player = player;

        //VBox that constians the hand, bench and active pokemon
        VBox centerCardArea = new VBox();
        centerCardArea.getStyleClass().add("CardArea");

        handCards = new HBox();
        handCards.getStyleClass().add("Hand");

        ScrollPane handScroll = new ScrollPane(handCards);

        benchCards = new HBox();
        benchCards.getStyleClass().add("Bench");

        ScrollPane benchScroll = new ScrollPane(benchCards);

        activeCard = new HBox();

        //Add them in "reverse" order for them to displayGameResult from bottom to top
        if (directionUp) {
            centerCardArea.getChildren().addAll(activeCard, benchScroll, handScroll);
        } else {
            centerCardArea.getChildren().addAll(handScroll, benchScroll, activeCard);
        }
        deck = new Label("");
        deck.getStyleClass().add("Deck");

        pileBox = new VBox();
        pileBox.getChildren().add(deck);

        // create check the button for show the whole pile
        discardPileBtn = new Button("Discard Pile("
                + player.getDiscardPile().size() + "):");
        discardPileBtn.setOnMouseClicked((event -> {
            registeredListeners.forEach(listener -> listener
                    .onDiscardPileClicked(player));
        }));
        discardPileBtn.setVisible(true);

        this.setCenter(centerCardArea);
        this.setRight(pileBox);
        refreshView();
    }

    public void refreshView() {

        deck.setText(String.valueOf(player.getDeck().size()));

        //Add hand cards
        handCards.getChildren().clear();

        Label handLabel = new Label("Hand");
        handLabel.setOnMouseClicked(event -> registeredListeners.forEach(listener -> listener
                .onHandCardClicked(player, null)));

        handCards.getChildren().add(handLabel);

        //For now, just add 5 cardViews to make sure it displays

        player.getHand().forEach((card) -> {
            CardView cardView = new CardView(player, card);

            cardView.setOnMouseClicked((event -> {
                registeredListeners.forEach(listener -> listener.onHandCardClicked(player, card));
            }));

            handCards.getChildren().add(cardView);
        });


        //Add bench cards
        benchCards.getChildren().clear();

        Label benchLabel = new Label("Bench");
        benchLabel.setOnMouseClicked(event -> registeredListeners.forEach(listener -> listener
                .onBenchCardClicked(player, null)));

        benchCards.getChildren().add(benchLabel);

        player.getBench().forEach((card) -> {
            CardView cardView = new CardView(player, card);

            cardView.setOnMouseClicked((event -> {
                registeredListeners.forEach(listener -> listener.onBenchCardClicked(player, card));
            }));

            benchCards.getChildren().add(cardView);
        });


        //set the current active card
        activeCard.getChildren().clear();

        Label activeLabel = new Label("Active");
        activeLabel.setOnMouseClicked(event -> registeredListeners.forEach(listener -> listener
                .onActiveCardClicked(player, null)));

        activeCard.getChildren().add(activeLabel);

        if (player.getActivePokemon() != null) {

            CardView cardView = new CardView(player, player.getActivePokemon());
            cardView.getStyleClass().add("active");
            cardView.setOnMouseClicked((event -> {
                //registeredListeners.forEach(listener->listener.onActiveCardClicked(player,
                // player.getActivePokemon()));
            }));
            registeredListeners.forEach(listener -> cardView.registerListener(listener));

            PokemonCard pokemonCard = (PokemonCard) player.getActivePokemon();

            Button retreatButton = new Button("Retreat " + pokemonCard.getRetreatEnergyCost()
                    .toCondensedString());
            retreatButton.setOnAction(event -> {
                registeredListeners.forEach(listener -> listener.onRetreatButtonClicked(player));
            });

            activeCard.getChildren().addAll(retreatButton, cardView);
        }

        pileBox.getChildren().clear();
        pileBox.getChildren().add(new Label("Deck: "));
        pileBox.getChildren().add(deck);
        pileBox.getChildren().add(discardPileBtn);

        // update the discard view
        if (player.getDiscardPile().size() > 0) {
            pileBox.getChildren().add(new CardView(player, player.getDiscardPile().get(player
                    .getDiscardPile().size() - 1)));

        }

    }

    public void registerListener(PlayerViewListener listener) {
        registeredListeners.add(listener);
    }

}
