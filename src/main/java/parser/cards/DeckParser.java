/*
 * description:  Loading the Deck
 * author(s):    Martin Tseng
 * reviewer(s):
 * date:         2017-05-29
 */

package parser.cards;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import card.Card;
import card.pokemon.PokemonCard;

public class DeckParser {


    public DeckParser()  {
    	
    }
    
    public ArrayList<Card> createAndReturnDeck() throws IOException{
    	
    	ArrayList<String> cardsList = new ArrayList<>();
    	ArrayList<Card> deck = new ArrayList<>();
    	
    	FileReader cardsTextFile = new FileReader("src/main/resources/cards.txt");
    	BufferedReader reader = new BufferedReader(cardsTextFile);
		String line = "";
		
		line = reader.readLine();
		while(line !=null){
			
			if(line.contains("#") || line.equals("") ){
				line = reader.readLine();
			} else{
				cardsList.add(line);
				line = reader.readLine();
			}
		}
		
		
		CardParser cardParser = new CardParser();
		
		
		System.out.println(cardsList.get(0));

		
		for (int i = 0; i<cardsList.size(); i++){
			
			
			deck.add(cardParser.createCard(cardsList.get(i)));

		}
		
		
    
		reader.close();
		
		return deck;

    }
		
		
	

    

   
    
    

}
