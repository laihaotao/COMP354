package game;


import card.Card;
import card.abilities.Ability;
import card.pokemon.PokemonCard;

import java.util.*;

//import javax.sound.midi.SysexMessage;

//import Card.CardType;
/**
 * Created by frede on 2017-05-15.
 * modified by kawsara 2017-05-16
 *in progress
 */


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.Normalizer;

import card.Card;
import card.pokemon.PokemonCard;

/**
 * Created by frede on 2017-05-15.
 */
public class Player {

	//function

	private Card [] playerDeck = new Card[60];

	//Will take the txt.file, read through it, and create the decks
	public void createDeck() throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException{

		//IMPORTANT: The deck text files must be a folder called "decks". If you don't have one, please create one
		//Card [] playerDeck = new Card [60];

		FileReader playerDeckFile = new FileReader("src/main/java/decks/deck1.ptcgo.txt");
		FileReader opponentDeckFile = new FileReader("src/main/java/decks/deck1.ptcgo.txt");

		playerDeck = loadDeck(playerDeckFile);






	}

	public Card[] loadDeck(FileReader file) throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException{



		//Create Scanner to read deck.txt files
		BufferedReader reader = new BufferedReader(file);
		Card [] deck = new Card[60];

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
				System.out.println("You may not have more than 60 Cards");
				break;
			}


			if (!line.isEmpty()){//if the line is not empty, we proceed

				lineParts = line.split(" ");

				//Checking which category
				if (lineParts[0].equals("##Pok�mon")){
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
					//Line contains pokemon Card information

					//Number of Cards of the same type
					int numberOfCards = Integer.parseInt(lineParts[1]);
					if (!isEnergyCard){
						if (numberOfCards>4){//Game rules: can't have more than 4 Cards of the same type
							System.out.println("It is against the rules to have more than 4 Cards of the same type");
							System.out.println(numberOfCards + line);
						}
					}

					//WILL CREATE 60 PIKACHUS FOR DEMO
					String className = "PikachuDemo";  //must use fully qualified name

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

					//add Cards

					for (int i=0; i < numberOfCards;i++){//Create the number of Cards indicated in the line read

						//will need pokemonCardFactory
						
						//(String pokemonStage, String name, int hp , String pokemonType, ArrayList<Ability> abilities, int[] retreatEnergyCost)
						//will delete this soon..
						
						int[] retreatEnergyCost = PokemonCard.setAndReturnEnergyArray(1,2,3,4,5,6,7,8,9,10,11);
						
						PokemonCard pokemonCard = new PokemonCard("Basic", "PikachuDemo", 90, "lightning",null, retreatEnergyCost);
						//deck[deckIndex] = pokemonCard;
						//deckIndex +=1;


					}// end of for loop
				}
			}

			line = reader.readLine(); //move to the next line
		} // end of the While loop

		if (isPokemonCard == false){
			System.out.println("you deck needs at least a pokemon Card");

		}
		return deck;



	}

	//This method is used to clean the line to make it easier to read the string and to convert it to a className
	public String convertLineToClassName (String string){
		string = string.replaceAll("\\*", "");
		string = Normalizer.normalize(string, Normalizer.Form.NFD);
		string = string.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
		string = string.replaceAll("'", "");
		string = string.replaceAll(" ", "");
		string = string.replaceAll("-", "");
		string = string.replaceAll("\\d", "");
		return string;

	}

	public void printDeck(){
		//Good for testing

		//Print out the whole deck for testing
		for (int i=0; i<playerDeck.length;i++){
			System.out.println("Deck - Index: " + i + " " + playerDeck[i]);
		}


	}




	//private  ArrayList<Card> listOfAllCards;
	private  ArrayList<Card> deck;// = new Card[60];
	private  ArrayList<Card> prizes = new ArrayList()  ;//= new Card[6];
	private  ArrayList<Card> hand = new ArrayList() ;
	private   ArrayList<Card>bench  = new ArrayList()  ;//= new Card[5];;
	private  ArrayList<Card> discardPile  = new ArrayList() ;
	private ArrayList<Card> onTable;
	private Card activePokemon;
	private  ArrayList<Card>  pokemonCards  = new ArrayList() ;
	private ArrayList<Card>  energyCards  = new ArrayList() ;
	private  ArrayList<Card> trainerCards  = new ArrayList() ;
	Scanner kb = new Scanner(System.in);
	Random rand = new Random();


	public Player(ArrayList<Card> playerDeck)
	{
		//Each player gets 7 cards drawn randomly at the beginning of the game
		deck = playerDeck;

		putprizes();  //lack of better name
		//Each player draws 7 cards at the beginning of the game and keeps their own hand hidden.
		for(int i = 0 ; i<7; i++)
		{
			putCardInHand();
		}

		//chooseActivePokemon();
	}
	//Each player gets 7 cards drawn randomly at the beginning of the game
	public void putprizes()
	{
		for(int i = 0 ; i<7; i++)
		{
			int  n = rand.nextInt(deck.size());
			prizes.add(deck.remove(n));
		}


	}
	//Each player draws 7 cards at the beginning of the game and keeps their own hand hidden.
	public void putCardInHand()
	{
		hand.add(deck.remove(0));
		if(hand.get(hand.size()-1).getCardType() == "POKEMON")
			pokemonCards.add(hand.get(hand.size()-1));

	}

	/*
	 * this is done only at the begining
	 */
	public void chooseActivePokemon()
	{
		printPokemonCards();
		if (pokemonCards.size() >0)
		{
			//System.out.println("chose which card you want to use ");
			int pokNum ;
			do
			{
				System.out.println("chose which card you want to use as your active pokemon ");
				System.out.println("Enter the number");
				pokNum  = kb.nextInt();
			}while(pokNum <1 || pokNum > pokemonCards.size());
			activePokemon = pokemonCards.get(pokNum-1);
			System.out.println(" your active pokemon is: " + activePokemon.getCardName());
		}

	}


	public void putCardOnBench()
	{
		printCardInHand();
		if (hand.size() >0)
		{
			//System.out.println("chose which card you want to add t your bench");
			int pokNum ;
			do
			{
				System.out.println("chose which card you want to add t your bench ");
				System.out.println("Enter the number between 1 and " + hand.size());
				pokNum  = kb.nextInt();
			}while(pokNum <1 || pokNum > hand.size());

			bench.add(hand.remove(pokNum-1));

		}

	}


	public void pickFromPrizeCard()
	{
		printPrizeCards();
		// pick a card and then add it to hand
		if (prizes.size() >0)
		{

			int pokNum ;

			do
			{
				System.out.println("chose which card you want to add t your bench ");
				System.out.println("Enter a number between 1 and " + prizes.size());
				pokNum  = kb.nextInt();
			}while(pokNum <1 || pokNum > prizes.size());
			hand.add(prizes.remove(pokNum-1));

		}

	}
	public void printPrizeCards()
	{
		if (prizes.size() >0)
		{
			System.out.println("these are the Cards you have as prizes");
		}
		else
			System.out.println(" you have no cards as prizes");

	}
	public void printCardInBench()
	{
		if (bench.size() >0)
		{
			System.out.println("these are the Cards you have in bench");

			for(int i = 0 ; i<bench.size(); i++)
			{
				System.out.println(i+1 +": "+bench.get(i).getCardName());
			}//end for loop

		}
		else
			System.out.println(" there are no card on your bench");


	}
	public void printCardInHand()
	{
		if (hand.size() >0)
		{
			System.out.println("these are the Cards you have in hand");

			for(int i = 0 ; i<hand.size(); i++)
			{
				System.out.println(i+1 +": "+hand.get(i).getCardName());
			}//end for loop

		}
		else
			System.out.println(" there are no card in your hand");

	}
	public void printCardOnDeck()
	{

		if (deck.size() >0)
		{
			System.out.println("these are the Cards you have in your deck");

			for(int i = 0 ; i<deck.size(); i++)
			{
				System.out.println(i+1 +": "+deck.get(i).getCardName());
			}//end for loop

		}
		else
			System.out.println(" there are no card in your deck");

	}
	public void printPokemonCards()
	{
		if (pokemonCards.size() >0)
		{
			System.out.println("these are the pokemon you have in hand");

			for(int i = 0 ; i<pokemonCards.size(); i++)
			{
				System.out.println(i+1 +": "+pokemonCards.get(i).getCardName());
			}//end for loop
		}
		else
			System.out.println(" there are no pokemon in your hand");
	}
	public void drawTopSixCard()
	{
		for(int i = 0 ; i<6; i++)
		{
			putCardInHand();
		}

	}
	/*
	 * done when we want to change our active pokemon to another
	 */
	public void swapAcTivePokemon()
	{
		printPokemonCards();
		if (pokemonCards.size() >0)
		{

			//System.out.println("chose which card you want to use ");
			int pokNum ;
			do
			{
				System.out.println("chose which card you want to use as your active pokemon ");
				System.out.println("Enter the number");
				pokNum  = kb.nextInt();
			}while(pokNum <1 || pokNum > pokemonCards.size());
			activePokemon = pokemonCards.get(pokNum-1);
			System.out.println(" your active pokemon is: " + activePokemon.getCardName());
		}


	}

	public void attackOpponent(Card opponent)
	{

	}

	public List<Card> getDeck(){
		return deck;
	}

	public List<Card> getHand(){
		return hand;
	}

	public List<Card> getBench(){
		return bench;
	}

	public Card getActivePokemon(){
		return activePokemon;
	}

	public void setActivePokemon(Card card){
		activePokemon = card;
	}

}