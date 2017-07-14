package card;

import entry.Config;
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
import static org.junit.Assert.assertEquals;

/**
 * Created by Eric on 5/30/2017.
 */
public class ParseDeckTest {

    static Logger logger = LogManager.getLogger(ParseDeckTest.class.getName());;

    @Before
    public void before() {
//        logger. (Level.ERROR);
    }

    @Test
    public void parseTwoDeckTest() throws IOException, ClassNotFoundException {
        CardParser cardParser = new CardParser(Config.FILE_PATH_CARDS_TXT);
        DeckParser deckParser1 = new DeckParser(Config.FILE_PATH_DECK1_TXT, cardParser);
        DeckParser deckParser2 = new DeckParser(Config.FILE_PATH_DECK2_TXT, cardParser);

        ArrayList<Card> deck1 = (ArrayList<Card>) deckParser1.getDeck();
        ArrayList<Card> deck2 = (ArrayList<Card>) deckParser2.getDeck();

        
        
        System.out.println("------------");
        for (Card card : deck1) {
            if (card instanceof PokemonCard) {
                System.out.print(card.getCardName() + ":");
                ArrayList<Ability> abilities = (ArrayList<Ability>) ((PokemonCard) card).getAbilities();
                for (Ability a : abilities) {
                    System.out.print(" " + a.getTemplate().name + ";");
                }
                System.out.println();
            }
        }
        System.out.println("------------");

    }

    @Test
    public void parseSingleDeckTest() throws IOException, ClassNotFoundException {

        ArrayList<String> expected = TestResultHelper.readResultFile("ParseDeck1Result.txt");

        CardParser cardParser = new CardParser(Config.FILE_PATH_CARDS_TXT);
        DeckParser deckParser = new DeckParser(Config.FILE_PATH_DECK1_TXT, cardParser);

        ArrayList<Card> deck = (ArrayList<Card>) deckParser.getDeck();

        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), deck.get(i).getCardName());
        }
    }
    
    

}
