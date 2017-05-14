package ui;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

/**
 * Displays info about an ability
 */
public class AbilityView extends BorderPane{

    //TODO pass ability object
    public AbilityView(){

        this.getStyleClass().add("Ability");

        Label name = new Label("Ability");
        name.getStyleClass().add("Name");

        setLeft(name);
        setCenter(new Label("Description is going to go here"));
    }

}
