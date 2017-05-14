import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.Normalizer;


//import card;

public class loadingDeck {

	private static Object clsInstance;

	
	public static void main(String[] args) throws Exception {
		
		
		//add this in the main.java or in the menu.java
		
		//Create Scanner to read deck.txt files
		//IMPORTANT: The deck text files must be a folder called "decks". If you don't have one, please create one
		

		//card [] playerDeck = new card [60];
		card [] playerDeck = new card[60];
		card [] opponentDeck = new card[60];
		
		FileReader playerDeckFile = new FileReader("decks/deck1.ptcgo.txt");
		FileReader opponentDeckFile = new FileReader("decks/deck1.ptcgo.txt");
		
		playerDeck = loadDeck(playerDeckFile);
		opponentDeck = loadDeck(playerDeckFile);

		//Print out the whole deck for testing
		for (int i=0; i<opponentDeck.length;i++){
			System.out.println(playerDeck[i]);
		}
		
		

		
	}
	
	
	public static card[] loadDeck(FileReader file) throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		BufferedReader reader = new BufferedReader(file);
		card [] deck = new card[60];
		
		//initiate variables to read the file
		String line = "";
		String [] lineParts = new String [10];
		
		//Read the first line first
		line = reader.readLine();

		//variables for the while loop
		boolean isPokemonCard = false;
		boolean isTrainerCard = false;
		boolean isEnergyCard = false;
		int deckIndex = 0;
		
		//While loop to read all the lines.
		while (line != null){
			
			//Game Rules: A player cannot have more than 60;
			if (deckIndex>60){ 
				System.out.println("You may not have more than 60 cards");
				break;
			}
			
			
			if (!line.isEmpty()){//if the line is not empty, we proceed
				
				lineParts = line.split(" ");
				
				//Checking which category
				if (lineParts[0].equals("##Pokémon")){
					isPokemonCard = true;
					//isTrainerCard = false;
					//isEnergyCard = false;
				}
				else if (lineParts[0].equals("##Trainer")){
					//isPokemonCard = false;
					isTrainerCard = true;
					//isEnergyCard = false;
					
				}
				else if (lineParts[0].equals("##Energy")){
					//isPokemonCard = false;
					//isTrainerCard = false;
					isEnergyCard = true;
				}
				
				else if (lineParts[0].equals("*")){
					//Line contains pokemon card information
					
					//Number of cards of the same type
					int numberOfCards = Integer.parseInt(lineParts[1]); 
					if (isPokemonCard|isTrainerCard){
						if (numberOfCards>4){//Game rules: can't have more than 4 cards of the same type
							System.out.println("It is against the rules to have more than 4 cards of the same type");
						}
					}
					
					//WILL CREATE 60 PIKACHUS FOR DEMO
					String className = "pikachuDemo";  //must use fully qualified name
					
					/* NOTE:PLEASE DO NOT DELETE THIS OR MODIFY THIS YET. THIS ADDS THE CORRESPONDING POKEMON BASED ON THE STRING THAT IT READS
					 * EXAMPLE: If it reads "Glameow BKP 93" It will create a class object of GlameowbBKP. If you uncomment this
					 * You will get an error as we have not created the pokemons yet.
					 * 
					 * Martin Tseng
					//Clean any accents or apostrophe in the name
					String className = convertLineToClassName(line);;
					if (isEnergyCard){
						
						if(className.contains("Grass")){
							className = "Grass";
						}
						else if(className.contains("Fire")){
							className = "Fire";
						}
						else if(className.contains("Water")){
							className = "Water";
						}
						else if(className.contains("Lightning")){
							className = "Lightning";
						}
						else if(className.contains("Psychic")){
							className = "Psychic";
						}
						else if(className.contains("Fighting")){
							className = "Fighting";
						}
						else if(className.contains("Darkness")){
							className = "Darkness";
						}
						else if(className.contains("Metal")){
							className = "Metal";
						}
						else if(className.contains("Colorless")){
							className = "Colorless";
						}
						else if(className.contains("Fairy")){
							className = "Fairy";
						}
						else if(className.contains("Dragon")){
							className = "Dragon";
						}
						
					}
					*/
					
					//add cards
					
					for (int i=0; i < numberOfCards;i++){//Create the number of cards indicated in the line read
						Class<?> cls = Class.forName(className);//must use fully qualified name
						clsInstance = cls.newInstance();
						deck[deckIndex] = (card) clsInstance;
						deckIndex +=1;
						

					}// end of for loop
				}
			}

			line = reader.readLine(); //move to the next line
		}
		
		if (isPokemonCard == false){
			System.out.println("you deck needs at least a pokemon card");
			
		}
		return deck;
		
		
		
	}
	
	
	
	
	public static String convertLineToClassName (String string){
		string = string.replaceAll("\\*", "");
		string = Normalizer.normalize(string, Normalizer.Form.NFD);
		string = string.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
		string = string.replaceAll("'", "");
		string = string.replaceAll(" ", "");
		string = string.replaceAll("-", "");
		string = string.replaceAll("\\d", "");
		return string;
		
	}

}
