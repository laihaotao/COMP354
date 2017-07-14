package selectdeck;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;

/**
 * Author:  Eric(Haotao) Lai
 * Date:    2017-07-13
 * E-mail:  haotao.lai@gmail.com
 * Website: http://laihaotao.me
 */


public class NormalDeckFileReader extends DeckReader {

    public NormalDeckFileReader(String path) {
        super(path);
    }

    @Override
    public ObservableList<String> getOptionList() {
        File[] files = getDirectoryFiles();
        ObservableList<String> options = FXCollections.observableArrayList();
        for (File f : files) {
            options.add(f.getName());
        }
        return options;
    }

    public File[] getDirectoryFiles() {
        File dir = new File(deckPath);
        if (dir.isDirectory()) {
            return dir.listFiles();
        }
        return null;
    }
}
