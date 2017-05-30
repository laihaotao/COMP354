package card;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import parser.cards.CardParser;
import parser.cards.DeckParser;
import util.TestResultHelper;

import java.io.IOException;
import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Eric on 5/30/2017.
 */
public class ParseDeckTest {

    final static Logger logger = LogManager.getLogger(ParseDeckTest.class.getName());;

    @Before
    public void before() {
//        logger.(Level.ERROR);
    }

    @Test
    public void parseDeckTest() throws IOException, ClassNotFoundException {

        ArrayList<String> expected = TestResultHelper.readResultFile("ParseDeck1Result.txt");

        CardParser cardParser = new CardParser("cards.txt");
        DeckParser deckParser = new DeckParser("deck1.txt", cardParser);

        ArrayList<Card> deck = (ArrayList<Card>) deckParser.getDeck();

        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), deck.get(i).getCardName());
        }
    }
}
