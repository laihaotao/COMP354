/*
 * description:  Parent pane used to display current view
 * author(s):    frede
 * reviewer(s):
 * date:         2017-05-17
 */

package ui;

import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class StartPane extends StackPane {

    //Pane being currently viewed
    private Pane currentView;

    public StartPane() {

    }

    /**
     * Set view to a certain pane
     *
     * @param pane new view pane
     */
    public void setCurrentView(Pane pane) {
        currentView = pane;
        //remove previous pane
        this.getChildren().clear();

        //add current view pane
        this.getChildren().add(pane);
    }

}
