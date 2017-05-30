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

public class DeckParser {


    public DeckParser()  {
    
    	
    }
    
    public void createAndReturnDeck() throws IOException{
    	
    	ArrayList<String> cardsList = new ArrayList<>();
    	ArrayList<Card> deck = new ArrayList<>();
    	
    	FileReader cardsTextFile = new FileReader("src/main/resources/cards.txt");
    	
    	
    	
    	String fileName = "deck1";
    	//will be in the arguments
		FileReader file = new FileReader("src/main/resources/" + fileName + ".txt");
		
		//Create Scanner to read deck.txt files
		BufferedReader reader = new BufferedReader(file);
		String line = "";
		
		//Read the first line first
		line = reader.readLine();
		
		String pointerLine;
		while(line !=null){
			
		}
			
			
			
		

		

    }
    

   
    
    

}
