package ui;

import card.abilities.Ability;
import java.util.Arrays;
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
        // need to fix: *****************setCenter(new Label(Arrays.toString(ability.getCost())));
    }

}
