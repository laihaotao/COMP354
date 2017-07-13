package game;

import entry.GameApp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.ResourceReader;

import java.io.*;
import java.util.ArrayList;

/**
 * Author:   Eric(Haotao) Lai
 * Date:     ${Date}
 * E-mail:   haotao.lai@gmail.com
 * Website:  http://laihaotao.me
 */
public class SelectDeck {

    private String deckPath;

    public SelectDeck(String path) {
        this.deckPath = path;
    }

    public ObservableList<String> getOptionList() {
        File[] files = getDirectoryFiles();
        ObservableList<String> options = FXCollections.observableArrayList();
        for (File f : files) {
            options.add(f.getName());
        }
        return options;
    }

    private File[] getDirectoryFiles() {

        File dir = new File(deckPath);
        if (dir.isDirectory()) {
            return dir.listFiles();
        }
        return null;

//        InputStream is = ResourceReader.readFile("deck_description.txt");
//        BufferedReader br = new BufferedReader(new InputStreamReader(is));
//
//        ArrayList<File> list = new ArrayList<>();
//        String line;
//
//        try {
//            while ((line = br.readLine()) != null) {
//                list.add(new File(line));
//            }
//            br.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return list;
    }

//    public static String getPath() {
//
//
//    }
}
