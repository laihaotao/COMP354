package card;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

import parser.cards.CardParser;
import parser.cards.DeckParser;
import entry.Config;

/**
 * Created by Martin on 2017-07-03.
 */


public class OutOfBoundTest {

	@Test
	public void test() throws IOException, ClassNotFoundException {
		
        CardParser cardParser = new CardParser(Config.FILE_PATH_CARDS_TXT);
        DeckParser deckParser1 = new DeckParser(Config.FILE_PATH_DECK1_TXT, cardParser);
        DeckParser deckParser2 = new DeckParser(Config.FILE_PATH_DECK2_TXT, cardParser);
        ArrayList<Card> deck1 = (ArrayList<Card>) deckParser1.getDeck();
        ArrayList<Card> deck2 = (ArrayList<Card>) deckParser2.getDeck();
		System.out.print(deck1.size());
		System.out.print(deck2.size());
        int deckLength = 60;

        assertEquals(deck1.size(), deckLength);
        assertEquals(deck2.size(), deckLength);
		
	}

}
