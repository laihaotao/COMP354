package ui;

import card.Card;
import card.pokemon.PokemonCard;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

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

    //TODO A Card instance should be passed to this once it is properly implemented
    public CardView(Card card){

        this.getStyleClass().add("Card");

        if(card.getSelected()){
            this.getStyleClass().add("Selected");
        }

        topInfo = new HBox();
        topHealthInfo  = new VBox();

        //TODO display actual card info

        //displays health and damage values
        healthLabel = new Label("30");
        damageLabel = new Label("10");

        healthLabel.getStyleClass().add("Health");
        damageLabel.getStyleClass().add("Damage");

        //topHealthInfo.getChildren().addAll(healthLabel, damageLabel);

        topInfo.getChildren().addAll(new Label(card.getCardName()), topHealthInfo);

        //Display abilities
        abilitiesInfo = new VBox();
        if(card instanceof PokemonCard){
            PokemonCard pokemonCard = (PokemonCard)card;
            pokemonCard.getAbilities().forEach((ability -> {
                AbilityView abilityView = new AbilityView(ability);
                abilityView.setOnMouseClicked((event -> {
                        
                }));
                abilitiesInfo.getChildren().add(abilityView);
            }));
        }

        setTop(topInfo);
        setCenter(abilitiesInfo);
    }
}
