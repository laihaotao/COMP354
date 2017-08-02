package Player;

import card.Card;
import entry.Config;
import game.GameBoard;
import game.Player;
import game.ai.IntelligentPlayer;
import org.junit.Test;
import parser.cards.CardParser;
import parser.cards.DeckParser;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by kawsa on 7/13/2017.
 */

public class TestButtonClicked {
    String deck1FileNm = "deck1.txt";
    String deck2FileNm = "deck2.txt";

    CardParser cardParser;
    DeckParser deck1Parser ;
    DeckParser deck2Parser;
    List<Card> player1Deck ;
    List<Card> player2Deck;
    Player player1 ;//= new Player(player1Deck);
    Player player2;
    @Test
    public void testRetreatButtonClicked() throws ClassNotFoundException,IOException {

        String deck1FileNm = "deck1.txt";
        String deck2FileNm = "deck2.txt";

        cardParser = new CardParser(Config.FILE_PATH_CARDS_TXT);
        deck1Parser = new DeckParser(deck1FileNm, cardParser);
        deck2Parser = new DeckParser(deck2FileNm, cardParser);

        player1Deck = deck1Parser.getDeck();
        player2Deck = deck2Parser.getDeck();

        Player player1 = new Player(player1Deck);
        Player player2 = new IntelligentPlayer(player2Deck);

        player1.setName("human player");
        player2.setName("AI player");


        GameBoard gm = new GameBoard(player1,player2);
        player1.chooseActivePokemon(gm);
        player2.checkMulligans();
        gm.onRetreatButtonClicked(player1);
        gm.onRetreatButtonClicked(player2);

        assertEquals(null, player1.getActivePokemon() );

        assertEquals(null, player2.getActivePokemon());

    }


}
