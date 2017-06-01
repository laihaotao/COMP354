package ui;

import card.Ability;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

/**
 * Displays info about an ability
 */
public class AbilityView extends BorderPane{

    //TODO pass ability object
    public AbilityView(Ability ability){

        this.getStyleClass().add("Ability");

        Label name = new Label(ability.getTemplate().name);
        name.getStyleClass().add("Name");

        setLeft(name);
        setCenter(new Label(ability.getEnergyCost().toCondensedString()));
    }

}
