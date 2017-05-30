
import game.Ai_Player;
import game.GameBoard;
import game.Player;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import card.Card;

import card.pokemon.PokemonCard;
import parser.abilities.AbilitiesParser;
import parser.abilities.AbilityTemplate;

import parser.tokenizer.LanguageTokenizer;
import ui.BoardView;
import ui.StartPane;

import parser.cards.CardParser;
import parser.cards.DeckParser;


/**
 * Main application class, used to start the game and initialize the gui
 */
public class GameApp extends Application {
	
	
	
	
    static Logger log = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
    
    //TODO Pull out to config / constants class?
    public static final int WINDOW_WIDTH = 1000, WINDOW_HEIGHT = 800;

    public static final String WINDOW_TITLE = "Pokemon";

    public static void main(String[] args) throws IOException {
        log.info("Starting pokemon game!");
        log.error("error");
        
        
        
        launch(args);


        
        
    }

    public void start(Stage primaryStage) throws Exception {


    	primaryStage.setTitle(WINDOW_TITLE);

        StartPane root = new StartPane();

        /*
         these cards i added are just for testing the code will change need to be changed to the real
                cards after the deck has been sent to its class

         */
        Card [] cards = new Card[60];
        int [] retreatEnergyCost = new int [11];

        for(int i = 0 ; i<retreatEnergyCost.length; i++)
        {
            retreatEnergyCost[i] = i*5;
        }
        for(int i = 0 ; i<60; i++)
        {
            cards[i] = new PokemonCard( );

        }
        ArrayList<Card> deck = new ArrayList() ;
        for(int i = 0 ; i<60; i++)
        {
            deck.add(cards[i]);
        }
        GameBoard gameBoard;
        gameBoard = new GameBoard(new Player(deck), new Ai_Player(deck));

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


        AbilitiesParser abilitiesParser = new AbilitiesParser("abilities.txt");
        AbilityTemplate[] abilities = abilitiesParser.parse();

    }
    
    
    

    

    
    
    
}
