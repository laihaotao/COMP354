package ui;

import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;

/**
 * Used to display info about the boardgame
 */
public class BoardView extends BorderPane {

    private PlayerView[] playerViews;

    //TODO this needs to take the BoardGame as a param and display info about it
    public BoardView() {


        //NOTE: This will crash if the resource folder isn't set up correctly
        this.getStylesheets().add("style.css");

        //Create player array, we know it will always be two in a pokemon game
        playerViews = new PlayerView[2];

        //Initialzie player views
        //TODO Once we get a player class, we pass that to PlayerView
        playerViews[0] = new PlayerView();
        playerViews[1] = new PlayerView();


        //create end game button
        Button endTurnBtn = new Button("End Turn");
        endTurnBtn.setOnAction((e) -> {
            //TODO Pass end game to boardgame class
        });


        //Put end game button on the center, with players on top / bottom
        this.setCenter(endTurnBtn);
        this.setTop(playerViews[1]);
        this.setBottom(playerViews[0]);

        updateView();
    }

    /**
     * Updates the board view and player views to display new info
     */
    public void updateView() {

        //TODO update player views

        //We need to redo rotate every yupdate in case
        //player 2's pane changed dimensions
        PlayerView p2View = playerViews[1];
        ObservableList<Transform> p2Transforms = p2View.getTransforms();
        p2Transforms.clear();
        //The rotate should have the origin at the center of the player view
        p2Transforms.add(new Rotate(180, p2View.getWidth() / 2, p2View.getHeight() / 2));

    }

}
