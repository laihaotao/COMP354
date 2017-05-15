
import game.GameBoard;
import game.Player;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ui.BoardView;
import ui.StartPane;


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
        boardView.updateView();

    }
    
    

    
    
    
}
