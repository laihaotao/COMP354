package Player;

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

public class HandTest {

	@Test
	public void HandTest() throws ClassNotFoundException, IOException {
		
		
		//The starting hands were not the same as there was a bit of randomness in the drawing phase. This was made it difficult for testing.
		//For example: Autoclicking testing a pokemon attacking another pokemon was very difficult as it would sometime click on an energy card
		//or a trainer card as the starting hand was different everytime.
		
		/* will test the content of hands and prize cards of each of the players
		 * 	Deck1:					Deck2:
			Shauna				51	Lightning	25
			Pokémon Fan Club	52	Pikachu		3
			Switch				53	Electrike	16
			Psychic				58	Glameow		1
			Hitmonlee			44	Lightning	25
			Slowpoke			43	Raichu		4
			Machop				33	Shellder	5
			
			Meowth				32	Seaking		6
			Fight				57	Seaking		6
			Fight				57	Purugly		14
			Doduo				34	Lightning	25
			Dodrio				35	Water		26
			Machop				33	Lightning	25

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
        
        player1.setName("human player");
        player2.setName("AI player");
        
        assertEquals(player1.getHand().get(0).getCardName(), "Shauna");
        assertEquals(player1.getHand().get(1).getCardName(), "Pokemon Fan Club");
        assertEquals(player1.getHand().get(2).getCardName(), "Switch");
        assertEquals(player1.getHand().get(3).getCardName(), "PSYCHIC");
        assertEquals(player1.getHand().get(4).getCardName(), "Hitmonlee");
        assertEquals(player1.getHand().get(5).getCardName(), "Slowpoke");
        assertEquals(player1.getHand().get(6).getCardName(), "Machop");
        
        assertEquals(player2.getHand().get(0).getCardName(), "LIGHTNING");
        assertEquals(player2.getHand().get(1).getCardName(), "Pikachu");
        assertEquals(player2.getHand().get(2).getCardName(), "Electrike");
        assertEquals(player2.getHand().get(3).getCardName(), "Glameow");
        assertEquals(player2.getHand().get(4).getCardName(), "LIGHTNING");
        assertEquals(player2.getHand().get(5).getCardName(), "Raichu");
        assertEquals(player2.getHand().get(6).getCardName(), "Shellder");
        
        
        

        
		
	}

}
