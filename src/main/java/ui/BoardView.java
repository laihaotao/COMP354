package ui;

import card.Card;
import card.Ability;
import game.GameBoard;
import game.Player;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ui.events.PlayerViewListener;

/**
 * Used to display info about the boardgame
 */
public class BoardView extends BorderPane implements PlayerViewListener{

    final static Logger logger = LogManager.getLogger(BoardView.class.getName());
    
    private PlayerView[] playerViews;

    private GameBoard gameBoard;

    public BoardView() {}

    public BoardView(GameBoard gameBoard) {

        this.gameBoard = gameBoard;
        
        //NOTE: This will crash if the resource folder isn't set up correctly
        this.getStylesheets().add("style.css");

        //Create player array, we know it will always be two in a pokemon game
        playerViews = new PlayerView[2];

        //Initialzie player views
        //TODO Once we get a player class, we pass that to PlayerView
        playerViews[0] = new PlayerView(gameBoard.getPlayer1(), true);
        playerViews[1] = new PlayerView(gameBoard.getPlayer2(), false);

        for (PlayerView playerView : playerViews) {
            playerView.registerListener(this);
        }
        
        //create end game button
        Button endTurnBtn = new Button("End Turn");
        endTurnBtn.setOnAction((e) -> {
            gameBoard.onEndTurnButtonClicked();
            refreshView();
        });


        //Put end game button on the center, with players on top / bottom
        this.setCenter(endTurnBtn);
        this.setTop(playerViews[1]);
        this.setBottom(playerViews[0]);

        refreshView();
    }

    /**
     * Updates the board view and player views to display new info
     */
    public void refreshView() {

        for (PlayerView playerView : playerViews) {
            playerView.refreshView();
        }
        /**
        //We need to redo rotate every yupdate in case
        //player 2's pane changed dimensions
        PlayerView p2View = playerViews[1];
        ObservableList<Transform> p2Transforms = p2View.getTransforms();
        p2Transforms.clear();
        //The rotate should have the origin at the center of the player view
        p2Transforms.add(new Rotate(180, p2View.getWidth() / 2, p2View.getHeight() / 2));
         **/

    }



    @Override
    public void onHandCardClicked(Player player, Card card) {        
        gameBoard.onHandCardClicked(player, card);
        refreshView();
    }

    @Override
    public void onBenchCardClicked(Player player, Card card) {
        gameBoard.onBenchCardClicked(player, card);
        refreshView();
    }

    @Override
    public void onActiveCardClicked(Player player, Card card) {
        gameBoard.onActiveCardClicked(player, card);
        refreshView();
    }

    @Override
    public void onActiveAbilityClicked(Player player, Card card, Ability ability) {
        gameBoard.onActiveAbilityClicked(player, card, ability);
        refreshView();
    }
    
    @Override
    public void onRetreatButtonClicked(Player player){
        gameBoard.onRetreatButtonClicked(player);
        refreshView();
    }
}
