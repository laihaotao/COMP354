
import game.GameBoard;
import game.Player;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.Normalizer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import card.Card;
import ui.BoardView;
import ui.StartPane;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.Normalizer;
import java.io.FileNotFoundException;


/**
 * Main application class, used to start the game and initialize the gui
 */
public class GameApp extends Application {
	
	
	
	
    static Logger log = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
    
    //TODO Pull out to config / constants class?
    public static final int WINDOW_WIDTH = 1000, WINDOW_HEIGHT = 800;

    public static final String WINDOW_TITLE = "Pokemon";

    public static void main(String[] args) {
        log.info("Starting pokemon game!");
        log.error("error");
        
        launch(args);
        

        Player player = new Player();
        Player opponent = new Player();
        try {
			player.createDeck();
			opponent.createDeck();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			System.out.println("InstantiationException!!");
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			System.out.println("IllegalAccessException!!");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("ClassNotFoundException!!");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("IOException!!");
			e.printStackTrace();
		}
        System.out.println("Player's Deck:");
        player.printDeck();
        System.out.println("Opponent's Deck:");
        opponent.printDeck();
        
        
        
    }

    public void start(Stage primaryStage) throws Exception {

    	primaryStage.setTitle(WINDOW_TITLE);

        StartPane root = new StartPane();

        GameBoard gameBoard = new GameBoard(new Player(), new Player());
        
        //TODO board and players here and pass that to BoardView
        BoardView boardView = new BoardView(gameBoard);

        root.setCurrentView(boardView);

        primaryStage.setScene(new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT));
        primaryStage.show();

        //TODO add support for resizing
        primaryStage.setResizable(false);

        //This needs to be called since primaryStage.show() changes dimensions of panes
        //This means that any transformatons need to be re-applied to the views
        boardView.refreshView();

    }
    
    
    

    

    
    
    
}
