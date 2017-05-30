package card;

import org.junit.Test;
import parser.cards.CardParser;
import util.ResourceReader;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by ERIC_LAI on 2017-05-29.
 */
public class ParseCardTest {

    @Test
    public void parseCard() throws IOException {
        CardParser cardParser = new CardParser("cards.txt");
    }
}
