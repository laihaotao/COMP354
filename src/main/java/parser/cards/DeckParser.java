/*
 * description:  Loading the Deck
 * author(s):    Martin Tseng
 * reviewer(s):
 * date:         2017-05-29
 */

package parser.cards;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import card.Card;
import util.ResourceReader;

public class DeckParser {

    private List<Card> deck;
    private BufferedReader br;
    private CardParser cardParser;

    public DeckParser(String deckFilePath, CardParser cardParser) throws IOException, ClassNotFoundException {
        deck = new ArrayList<>();
        this.cardParser = cardParser;
        InputStream is = ResourceReader.readFile(deckFilePath);
        br = new BufferedReader(new InputStreamReader(is));
        buildDeck();
        br.close();
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

                Card originalCard = cardParser.getCardMap().get(lineNum);
                Card copiedCard = originalCard.copy();

                deck.add(copiedCard);
            }
        }
    }

}
