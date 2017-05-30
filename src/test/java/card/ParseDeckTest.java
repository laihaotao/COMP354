package card;

import org.junit.Test;
import parser.cards.CardParser;
import parser.cards.DeckParser;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Eric on 5/30/2017.
 */
public class ParseDeckTest {

    @Test
    public void parseDeckTest() throws IOException, ClassNotFoundException {
        CardParser cardParser = new CardParser("cards.txt");
        DeckParser deckParser = new DeckParser("deck1.txt", cardParser);

        ArrayList<Card> deck = (ArrayList<Card>) deckParser.getDeck();

        for (Card card : deck) {
            System.out.println(card.getCardName());
        }
    }
}
