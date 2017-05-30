package card;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by ERIC_LAI on 2017-05-29.
 */
public class ParseCard {

    @Test
    public void parseCard() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        BufferedReader br = null;
        File file = new File(classLoader.getResource("cards.txt").getFile());
        br = new BufferedReader(new FileReader(file));

        CardFactory cardFactory = new CardFactory();
        ArrayList<Card> cards = new ArrayList<>();
        String line;
        while ((line = br.readLine()) != null) {
            if (!line.isEmpty()) {
                cards.add(cardFactory.createCard(line));
            }
        }

        br.close();

        // print out the card's name
        for (Card card : cards) {
            System.out.println(card.getCardName());
        }
    }
}
