package card;

import org.junit.Test;
import parser.cards.CardParser;
import util.TestResultHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by ERIC_LAI on 2017-05-29.
 */
public class ParseCardTest {

    @Test
    public void parseCard() throws IOException {
        ArrayList<String> expected = TestResultHelper.readResultFile("ParseCardResult.txt");
        CardParser cardParser = new CardParser("cards.txt");

        HashMap<Integer, Card> map = cardParser.getCardMap();

        int j = 0;
        for (int i = 1; i <= map.size(); i++) {
            while (!map.containsKey(i)) i++;
            String name = map.get(i).getCardName();
            String expectedStr = expected.get(j++);
            assertEquals(expectedStr, name);
        }
    }
}
