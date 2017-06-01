package entry;

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

import ui.BoardView;
import ui.StartPane;

import parser.cards.CardParser;
import parser.cards.DeckParser;


/**
 * Main application class, used to start the game and initialize the gui
 */
public class GameApp extends Application {


    private static Logger log = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    //TODO Pull out to config / constants class?
    public static final int WINDOW_WIDTH = 1000, WINDOW_HEIGHT = 800;

    private static final String WINDOW_TITLE = "Pokemon";

    public static void main(String[] args) throws IOException {
        log.info("Starting pokemon game!");
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle(WINDOW_TITLE);
        StartPane root = new StartPane();


        GameBoard gameBoard = startGame("deck1.txt", "deck2.txt");

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

        //AbilitiesParser abilitiesParser = new AbilitiesParser("abilities.txt");
        //AbilityTemplate[] abilities = abilitiesParser.parse();

//        CardDebugParser cardDebugParser = new CardDebugParser("cards.txt");
//        cardDebugParser.parse();
    }

    private GameBoard startGame(String deck1FileNm, String deck2FileNm)
            throws IOException, ClassNotFoundException {
        CardParser cardParser = new CardParser("cards.txt");
        DeckParser deck1Parser = new DeckParser(deck1FileNm, cardParser);
        DeckParser deck2Parser = new DeckParser(deck2FileNm, cardParser);

        List<Card> player1Deck = deck1Parser.getDeck();
        List<Card> player2Deck = deck2Parser.getDeck();

        return new GameBoard(new Player(player1Deck), new Ai_Player(player2Deck));
    }


}
