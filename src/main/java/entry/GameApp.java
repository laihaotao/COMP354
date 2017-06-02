package entry;

import game.Ai_Player;
import game.GameBoard;
import game.Player;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileFilter;
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

    @Override
    public void start(Stage primaryStage) {
        selectDeck(primaryStage);
    }

    private void selectDeck(Stage primaryStage) {
        GridPane root = new GridPane();
        String f1 = "deck1.txt", f2 = "deck2.txt";

        ObservableList<String> player1List = getOptionList();
        ObservableList<String> player2List = getOptionList();

        final ComboBox<String> comboBox1 = new ComboBox<>(player1List);
        final ComboBox<String> comboBox2 = new ComboBox<>(player2List);
        comboBox1.setValue(f1);
        comboBox2.setValue(f2);

        Button startBtn = new Button();
        startBtn.setText("Start Game");
        startBtn.setOnAction(event -> {
            try {
                System.out.println(comboBox1.getValue());
                System.out.println(comboBox2.getValue());

                startGame(primaryStage, comboBox1.getValue(), comboBox2.getValue());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        root.setVgap(4);
        root.setHgap(5);
        root.add(new Label("Human Player: "), 1, 1);
        root.add(comboBox1, 2, 1);
        root.add(new Label("AI Player: "), 1, 2);
        root.add(comboBox2, 2, 2);

        root.add(startBtn, 2, 3);

        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 400, 300));
        primaryStage.setTitle("Select Deck File ");

        primaryStage.show();
    }

    private ObservableList<String> getOptionList() {
        File[] files = getDirectoryFiles();
        ObservableList<String> options = FXCollections.observableArrayList();
        for (File f : files) {
            options.add(f.getName());
        }
        return options;
    }

    private void startGame(Stage primaryStage, String fileNm1, String fileNm2) throws Exception {
        primaryStage.setTitle(WINDOW_TITLE);
        StartPane root = new StartPane();


        GameBoard gameBoard = startGame(fileNm1, fileNm2);

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

    private File[] getDirectoryFiles() {
        String resRoot = getClass().getResource("/").getPath();
        return getFile(resRoot);
    }

    private static File[] getFile(String path) {
        return new File(path).listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                // return all file whose name contains "deck"
                return pathname.getName().contains("deck");
            }
        });
    }

}
