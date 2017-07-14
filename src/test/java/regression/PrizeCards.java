package regression;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import card.Card;
import entry.Config;
import game.Player;
import game.ai.IntelligentPlayer;
import parser.cards.CardParser;
import parser.cards.DeckParser;

public class PrizeCards {

	@Test
	public void test() throws IOException, ClassNotFoundException {
		/*
		 * 2017-06-01
		 * It was noticed that the amount of prize cards was 7 instead of 6. This simple regression test is to
		 * make sure that the number of prize cards is 6.
		 * 
		 */
		
		String deck1FileNm = "deck1.txt";
		String deck2FileNm = "deck2.txt";
		
        CardParser cardParser = new CardParser(Config.FILE_PATH_CARDS_TXT);
        DeckParser deck1Parser = new DeckParser(deck1FileNm, cardParser);
        DeckParser deck2Parser = new DeckParser(deck2FileNm, cardParser);
       
        List<Card> player1Deck = deck1Parser.getDeck();
        List<Card> player2Deck = deck2Parser.getDeck();

        Player player1 = new Player(player1Deck);
        Player player2 = new IntelligentPlayer(player2Deck);
        
        assertEquals(player1.getPrizes().size(), 6);
        assertEquals(player2.getPrizes().size(), 6);
  
        
        
	}

}
