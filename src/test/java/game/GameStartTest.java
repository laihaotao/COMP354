package game;

import card.Card;
import org.junit.Test;
import parser.cards.CardParser;
import parser.cards.DeckParser;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by ERIC_LAI on 2017-05-31.
 */
public class GameStartTest {

    @Test
    public void startGame() throws IOException, ClassNotFoundException {
        CardParser cardParser = new CardParser("cards.txt");
        DeckParser deck1Parser = new DeckParser("deck1.txt", cardParser);
        DeckParser deck2Parser = new DeckParser("deck2.txt", cardParser);

        List<Card> player1Deck = deck1Parser.getDeck();
        List<Card> player2Deck = deck2Parser.getDeck();

        GameBoard gameBoard = new GameBoard(new Player(player1Deck), new Ai_Player(player2Deck));

        Player player = gameBoard.getPlayer1();
        int handCardNum = player.getHand().size();
        int prizeCardNum = player.getPrizes().size();

        assertEquals(7, handCardNum);
        assertEquals(6, prizeCardNum);

    }
}
