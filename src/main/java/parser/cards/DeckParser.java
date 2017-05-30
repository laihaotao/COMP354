/*
 * description:  Loading the Deck
 * author(s):    Martin Tseng
 * reviewer(s):
 * date:         2017-05-29
 */

package parser.cards;

import java.io.*;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import card.Card;
import card.pokemon.PokemonCard;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import util.ResourceReader;

public class DeckParser {

    private List<Card> deck;
    private BufferedReader br;
    private HashMap<Integer, Card> cardMap;

    public DeckParser(String deckFilePath, CardParser cardParser) throws IOException, ClassNotFoundException {
        deck = new ArrayList<>();
        this.cardMap = cardParser.getCardMap();
        File file = ResourceReader.readFile(deckFilePath);
        br = new BufferedReader(new FileReader(file));
        buildDeck();
    }

    public List<Card> getDeck() {
        if (!deck.isEmpty()) {
            return deck;
        }
        return null;
    }

    private void buildDeck() throws IOException, ClassNotFoundException {
        String line;
        while ((line = br.readLine()) != null) {
            if (!line.isEmpty()) {
                int lineNum = Integer.parseInt(line);

                Card originalCard = cardMap.get(lineNum);
                Card copiedCard = (Card) originalCard.deepClone();

                deck.add(copiedCard);
            }
        }
    }

}
