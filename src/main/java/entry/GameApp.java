package entry;

import card.Card;
import game.Ai_Player;
import game.GameBoard;
import game.Player;
import game.ai.InteliigentPlayer;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import parser.cards.CardParser;
import parser.cards.DeckParser;
import ui.BoardView;
import ui.StartPane;
import util.ResourceReader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Main application class, used to start the game and initialize the gui
 */
public class GameApp extends Application {


    private static Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    //TODO Pull out to config / constants class?
    public static final int WINDOW_WIDTH = 1000, WINDOW_HEIGHT = 800;

    private static final String WINDOW_TITLE = "Pokemon Go Back";

    public static void main(String[] args) throws IOException {
        logger.info("Starting pokemon game!");
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        selectDeck(primaryStage);
    }

    private void selectDeck(Stage primaryStage) {
        GridPane root = new GridPane();
        String f1 = Config.PATH_FILE_DECK1_TXT;
        String f2 = Config.PATH_FILE_DECK2_TXT;

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
                logger.debug(comboBox1.getValue());
                logger.debug(comboBox2.getValue());

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

        primaryStage.setResizable(true);
        primaryStage.setScene(new Scene(root, 400, 300));
        primaryStage.setTitle("Select Deck File ");

        primaryStage.show();
    }

    private void startGame(Stage primaryStage, String fileNm1, String fileNm2) throws Exception {
        primaryStage.setTitle(WINDOW_TITLE);
        StartPane root = new StartPane();

        GameBoard gameBoard = getGameBoard(fileNm1, fileNm2);

        //TODO board and players here and pass that to BoardView
        BoardView boardView = new BoardView(gameBoard);
        root.setCurrentView(boardView);
        primaryStage.setScene(new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT));
        primaryStage.show();

        //TODO add support for resizing
        primaryStage.setResizable(true);

        //This needs to be called since primaryStage.show() changes dimensions of panes
        //This means that any transformatons need to be re-applied to the views
        boardView.refreshView();

        //AbilitiesParser abilitiesParser = new AbilitiesParser("abilities.txt");
        //AbilityTemplate[] abilities = abilitiesParser.parse();

//        CardDebugParser cardDebugParser = new CardDebugParser("cards.txt");
//        cardDebugParser.parse();
    }

    private GameBoard getGameBoard(String deck1FileNm, String deck2FileNm)
            throws IOException, ClassNotFoundException {
        CardParser cardParser = new CardParser(Config.FILE_PATH_CARDS_TXT);
        DeckParser deck1Parser = new DeckParser(deck1FileNm, cardParser);
        DeckParser deck2Parser = new DeckParser(deck2FileNm, cardParser);

        List<Card> player1Deck = deck1Parser.getDeck();
        List<Card> player2Deck = deck2Parser.getDeck();

        Player player1 = new Player(player1Deck);
        InteliigentPlayer player2 = new InteliigentPlayer(player2Deck);

        player1.setName("human player");
        player2.setName("AI player");

        return new GameBoard(player1, player2);
    }

    private ObservableList<String> getOptionList() {
        ArrayList<File> files = getDirectoryFiles();
        ObservableList<String> options = FXCollections.observableArrayList();
        for (File f : files) {
            options.add(f.getName());
        }
        return options;
    }

    private ArrayList<File> getDirectoryFiles() {
        InputStream is = ResourceReader.readFile("deck_description.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        ArrayList<File> list = new ArrayList<>();
        String line;

        try {
            while ((line = br.readLine()) != null) {
                list.add(new File(line));
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

//    private File[] getFile(String path) {
//        return new File(path).listFiles(pathname -> {
//            // return all file whose name contains "deck"
//            logger.debug("getFile: " + pathname.getName());
//            return pathname.getName().contains("deck");
//        });
//    }

}
