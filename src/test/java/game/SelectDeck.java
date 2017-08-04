package game;

import entry.GameApp;
import org.junit.Test;
import selectdeck.NormalDeckFileReader;
import util.TestResultHelper;

import java.io.*;
import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;

/**
 * Author:  Eric(Haotao) Lai
 * Date:    2017-07-13
 * E-mail:  haotao.lai@gmail.com
 * Website: http://laihaotao.me
 */


public class SelectDeck {

    @Test
    public void test() throws IOException {
        String deckPath = GameApp.class.getClassLoader().getResource("decks/").getPath();
        NormalDeckFileReader reader = new NormalDeckFileReader(deckPath);
        File[] actual = reader.getDirectoryFiles();
        ArrayList<String> expected = TestResultHelper.readResultFile("DeckResult.txt");

        
        
        for (int i = 0; i < actual.length; i ++) {
            assertEquals(expected.get(i), actual[i].getName());
        }
    }
}
