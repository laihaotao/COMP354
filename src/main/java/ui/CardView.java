package ui;

import card.Card;
import card.pokemon.PokemonCard;
import game.Player;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ui.events.PlayerViewListener;

/**
 * Displays info about a card
 */
public class CardView extends BorderPane{

    //Info box that is visible on top of the card
    private HBox topInfo;
    private VBox topHealthInfo;

    private Label healthLabel;
    private Label damageLabel;

    private VBox abilitiesInfo;
    
    private Player player;
    
    private List<PlayerViewListener> registeredListeners;

    //TODO A Card instance should be passed to this once it is properly implemented
    public CardView(Player player, Card card){
        this.player = player;
        this.getStyleClass().add("Card");

        if(card.getSelected()){
            this.getStyleClass().add("Selected");
        }

        topInfo = new HBox();
        topHealthInfo  = new VBox();

        //TODO display actual card info

       

        topInfo.getChildren().addAll(new Label(card.getCardName()), topHealthInfo);

        //Display abilities
        abilitiesInfo = new VBox();
        if(card instanceof PokemonCard){
            PokemonCard pokemonCard = (PokemonCard)card;
            
            //displays health and damage values
            healthLabel = new Label(String.valueOf(pokemonCard.getHp()));
            damageLabel = new Label(String.valueOf(pokemonCard.getDamage()));

            healthLabel.getStyleClass().add("Health");
            damageLabel.getStyleClass().add("Damage");

            topHealthInfo.getChildren().addAll(healthLabel, damageLabel);
            
            
            pokemonCard.getAbilities().forEach((ability -> {
                AbilityView abilityView = new AbilityView(ability);
                abilityView.setOnMouseClicked((event -> {
                        registeredListeners.forEach((listener -> {
                            listener.onActiveAbilityClicked(player, card, ability);
                        }));
                }));
                abilitiesInfo.getChildren().add(abilityView);
            }));
        }

        setTop(topInfo);
        setCenter(abilitiesInfo);

        registeredListeners = new ArrayList<>();
    }
    
    public void registerListener(PlayerViewListener listener){
        registeredListeners.add(listener);
    }
}
