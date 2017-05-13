package ui;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

/**
 * Class used to display info about a player
 * including current deck, hand, bench, ...
 */
public class PlayerView extends BorderPane {

    //TODO this needs to take a player object
    public PlayerView(){

        //For now, we just display label to show it works
        //Should display actual info later on when player class is done

        this.setCenter(new Label("Player"));
    }

}
