import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.BoardView;
import ui.StartPane;

/**
 * Main application class, used to start the game and initialzie the gui
 */
public class GameApp extends Application {

    //TODO Pull out to config / constants class?
    public static final int WINDOW_WIDTH = 650, WINDOW_HEIGHT = 400;

    public static final String WINDOW_TITLE = "Pokemon";

    public static void main(String[] args) {
        System.out.println("Starting Pokemon game");
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle(WINDOW_TITLE);

        StartPane root = new StartPane();

        //TODO board and players here and pass that to BoardView
        BoardView boardView = new BoardView();

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
