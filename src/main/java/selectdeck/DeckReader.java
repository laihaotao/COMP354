package selectdeck;

import javafx.collections.ObservableList;

import java.io.IOException;

/**
 * Author:  Eric(Haotao) Lai
 * Date:    2017-07-13
 * E-mail:  haotao.lai@gmail.com
 * Website: http://laihaotao.me
 */


public abstract class DeckReader {

    protected String deckPath;

    public DeckReader(String path) {
        this.deckPath = path;
    }

    public abstract ObservableList<String> getOptionList() throws IOException;
}
