/*
 * description:  Displays info about a card
 * author(s):    frede
 * reviewer(s):
 * date:         2017-05-17
 */

package ui;

import card.Card;
import card.EnergyCard;
import card.PokemonCard;
import card.TrainerCard;
import game.GameBoard;
import game.Player;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ui.events.PlayerViewListener;

public class CardView extends BorderPane {

    //Info box that is visible on top of the card
    private HBox topInfo;
    private VBox topHealthInfo;

    private Label healthLabel;
    private Label damageLabel;

    private VBox abilitiesInfo;

    private Player player;

    private List<PlayerViewListener> registeredListeners;

    private Card card;
    
    boolean smallView;
    
    public CardView(GameBoard targetBoard, Player player, Card card, boolean smallView) {
        this.card = card;
        this.player = player;
        this.getStyleClass().add("Card");

        if (card.getSelected()) {
            this.getStyleClass().add("Selected");
        }

        topInfo = new HBox();
        topHealthInfo = new VBox();
        
        this.smallView = smallView;

        //TODO displayGameResult actual card info


        topInfo.getChildren().addAll(new Label(card.getCardName()), topHealthInfo);

        //Display abilities
        abilitiesInfo = new VBox();

        if (card instanceof PokemonCard) {
            PokemonCard pokemonCard = (PokemonCard) card;
            abilitiesInfo.getChildren().add(new Label(pokemonCard.getEnergyAttached()
                    .toCondensedString()));
            //displays health and damage values
            healthLabel = new Label(String.valueOf(pokemonCard.getHp()));
            damageLabel = new Label(String.valueOf(pokemonCard.getDamage()));

            healthLabel.getStyleClass().add("Health");
            damageLabel.getStyleClass().add("Damage");

            topHealthInfo.getChildren().addAll(healthLabel, damageLabel);

            topInfo.setOnMouseClicked(event -> registeredListeners.forEach((listener -> {
                listener.onActiveCardClicked(player, card);
            })));

            if(!smallView) {
                pokemonCard.getAbilities().forEach((ability -> {
                    AbilityView abilityView = new AbilityView(targetBoard, player, ability);
                    abilityView.setOnMouseClicked((event -> {
                        registeredListeners.forEach((listener -> {
                            listener.onActiveAbilityClicked(player, card, ability);
                        }));
                    }));
                    abilitiesInfo.getChildren().add(abilityView);
                }));
            }
        } else if (card instanceof TrainerCard) {
            TrainerCard trainerCard = (TrainerCard) card;
            AbilityView abilityView = new AbilityView(targetBoard, player, trainerCard.getAbility());
            abilitiesInfo.getChildren().add(abilityView);

        } else if (card instanceof EnergyCard) {

        }

        setTop(topInfo);
        setCenter(abilitiesInfo);
        setBottom(new Label(card.getCardType().toString()));
        registeredListeners = new ArrayList<>();
    }

    public void registerListener(PlayerViewListener listener) {
        registeredListeners.add(listener);
    }

    @Override
    public String toString() {
        return card.getCardName() +'\n' + card.getType();
    }
}
