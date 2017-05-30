package card;

import org.junit.Test;
import parser.cards.CardParser;
import util.ResourceReader;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by ERIC_LAI on 2017-05-29.
 */
public class ParseCardTest {

    @Test
    public void parseCard() throws FileNotFoundException {
        File file;
        BufferedReader br;

        file = ResourceReader.readFile("cards.txt");
        br = new BufferedReader(new FileReader(file));

        ArrayList<Card> cards = new ArrayList<>();
        CardParser cardParser = new CardParser();

        String line;
        try {
            while (null != (line = br.readLine())) {
                if (!line.isEmpty()) {
                	if(line.contains("#") || line.equals("")){
                		
                		line = br.readLine();
                	}else{
                		cards.add(cardParser.createCard(line));
                	}
                    
                }
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // print out the card's name
        for (Card card : cards) {
            System.out.println(card.getCardName());
        }
        
    }
}
