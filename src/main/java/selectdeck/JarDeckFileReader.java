package selectdeck;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Author:  Eric(Haotao) Lai
 * Date:    2017-07-13
 * E-mail:  haotao.lai@gmail.com
 * Website: http://laihaotao.me
 */


public class JarDeckFileReader extends DeckReader {

    public JarDeckFileReader(String path) {

        // todo: need to be test on windons!!!
        // use substring to eliminate the "file:"
        super(path.substring(5));
    }

    @Override
    public ObservableList<String> getOptionList() throws IOException {
        ObservableList<String> options = FXCollections.observableArrayList();

        int lastIdx = deckPath.lastIndexOf("!");
        String jarPath = deckPath.substring(0, lastIdx);
        Enumeration<JarEntry> entries = new JarFile(jarPath).entries();

        while (entries.hasMoreElements()) {
            JarEntry entry = entries.nextElement();
            if (entry.getName().contains("decks/") && entry.getName().length() > "decks/".length()) {
                options.add(entry.getName().substring(6));
            }
        }
        return options;
    }
}
