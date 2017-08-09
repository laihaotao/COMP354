package entry;

import card.Card;
import card.PokemonCard;
import game.GameBoard;
import game.Player;
import selectdeck.DeckReader;
import selectdeck.JarDeckFileReader;
import selectdeck.NormalDeckFileReader;
import game.ai.IntelligentPlayer;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
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

import java.io.*;
import java.util.List;


/**
 * Main application class, used to start the game and initialize the gui
 */
public class GameApp extends Application {


    private static Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    //TODO Pull out to config / constants class?
    public static final int WINDOW_WIDTH = 1000, WINDOW_HEIGHT = 800;

    private static final String WINDOW_TITLE = "Pokemon Go Back";
    private static String deckPath = GameApp.class.getClassLoader().getResource("decks/").getPath();
    private static GameBoard gameBoard = null;

    public static void main(String[] args) throws IOException {

        logger.info("Starting pokemon game!");
        logger.info(deckPath);
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            selectDeck(primaryStage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void selectDeck(Stage primaryStage) throws IOException {
        GridPane root = new GridPane();
        String default_f1 = Config.FILE_PATH_DECK1_TXT;
        String default_f2 = Config.FILE_PATH_DECK2_TXT;

        DeckReader deckReader;
        if (deckPath.contains("jar")) {
            deckReader = new JarDeckFileReader(deckPath);
        } else {
            deckReader = new NormalDeckFileReader(deckPath);
        }
        ObservableList<String> player1List = deckReader.getOptionList();
        ObservableList<String> player2List = deckReader.getOptionList();

        final ComboBox<String> comboBox1 = new ComboBox<>(player1List);
        final ComboBox<String> comboBox2 = new ComboBox<>(player2List);
        comboBox1.setValue(default_f1);
        comboBox2.setValue(default_f2);

        CheckBox aiBox = new CheckBox("AI vs AI");

        CheckBox debugBox = new CheckBox("Debug");

        Button startBtn = new Button();
        startBtn.setText("Start Game");
        startBtn.setOnAction(event -> {
            try {
                logger.debug(comboBox1.getValue());
                logger.debug(comboBox2.getValue());
                Config.DEBUG = debugBox.isSelected();
                startGame(primaryStage, comboBox1.getValue(), comboBox2.getValue(), aiBox.isSelected());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        root.setVgap(4);
        root.setHgap(5);
        root.add(new Label("Human Player: "), 1, 1);
        root.add(comboBox1, 2, 1);
        root.add(aiBox, 3, 1);
        root.add(new Label("AI Player: "), 1, 2);
        root.add(comboBox2, 2, 2);

        root.add(startBtn, 2, 3);
        root.add(debugBox, 1, 4);
        primaryStage.setResizable(true);
        primaryStage.setScene(new Scene(root, 400, 300));
        primaryStage.setTitle("Select Deck File ");

        primaryStage.show();
    }

    private boolean containsPokemon(List<Card> cards){
        for (Card card : cards) {
            if(card instanceof PokemonCard){
                if(((PokemonCard)card).getEvolvesFrom() == null){
                    return true;
                }
            }
        }
        return false;
    }



    private void checkMulligans(Player player1, Player player2){
        boolean player1HasPokemon = containsPokemon(player1.getHand());
        boolean player2HasPokemon = containsPokemon(player2.getHand());

        if(!player1HasPokemon){
            player1.drawNewCards();
            if(player2HasPokemon){
                if(player2.shouldDrawMulCard()){
                    player2.drawOneCard();
                }
            }
        }

        if(!player2HasPokemon){
            player2.drawNewCards();
            if(player1HasPokemon){
                if(player1.shouldDrawMulCard()){
                    player1.drawOneCard();
                }
            }
        }

        if(!player1HasPokemon || !player2HasPokemon){
            checkMulligans(player1, player2);
        }
    }

    private void startGame(Stage primaryStage, String fileNm1, String fileNm2, boolean allAI) throws Exception {
        primaryStage.setTitle(WINDOW_TITLE);
        StartPane root = new StartPane();

        GameBoard gameBoard = getGameBoard(fileNm1, fileNm2, allAI);

        checkMulligans(gameBoard.getPlayer1(), gameBoard.getPlayer2());

        gameBoard.getPlayer1().chooseActivePokemon(gameBoard);
        gameBoard.getPlayer2().chooseActivePokemon(gameBoard);
        
        
        

        BoardView boardView = new BoardView(gameBoard);
        root.setCurrentView(boardView);
        primaryStage.setScene(new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT));
        primaryStage.show();

        //TODO add support for resizing
        primaryStage.setResizable(true);

        boardView.refreshView();

        if (gameBoard.getPlayer1() instanceof IntelligentPlayer && gameBoard.getPlayer2() instanceof IntelligentPlayer) {
            new Thread(() -> gameBoard.onEndTurnButtonClicked()).start();
        }
    }

    private GameBoard getGameBoard(String deck1FileNm, String deck2FileNm, boolean allAI)
            throws IOException, ClassNotFoundException {

        CardParser cardParser = new CardParser(Config.FILE_PATH_CARDS_TXT);
        DeckParser deck1Parser = new DeckParser(deck1FileNm, cardParser);
        DeckParser deck2Parser = new DeckParser(deck2FileNm, cardParser);

        List<Card> player1Deck = deck1Parser.getDeck();
        List<Card> player2Deck = deck2Parser.getDeck();

        Player player1 = allAI ? new IntelligentPlayer(player1Deck) : new Player(player1Deck);
        Player player2 = new IntelligentPlayer(player2Deck);

        player1.setName("human player");
        player2.setName("AI player");

        gameBoard = new GameBoard(player1, player2);
        return gameBoard;
    }
}
