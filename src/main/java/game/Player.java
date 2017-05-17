
import java.util.*;
import java.util.Random;

import javax.sound.midi.SysexMessage;

import Card.CardType;
/**
 * Created by frede on 2017-05-15.
 * modified by kawsara 2017-05-16
 *in progress
 */
public class Player {
	
	private  ArrayList<Card> deck;// = new Card[60];
	private  ArrayList<Card> prizes ;//= new Card[6];
	private  ArrayList<Card> hand;
	private   ArrayList<Card>bench ;//= new Card[5];;
	private  ArrayList<Card> discardPile;
	private ArrayList<Card> onTable;
	private Card activePokemon;
	private  ArrayList<Card>  pokemonCards;
	private ArrayList<Card>  energyCards;
	private  ArrayList<Card> trainerCards;
	Scanner kb = new Scanner(System.in);
	Random rand = new Random();

	
	public Player(ArrayList<Card> playerDeck)
	{
		//Each player gets 7 cards drawn randomly at the beginning of the game
		
		for(int i = 0 ; i<6; i++)
		{
			int  n = rand.nextInt(playerDeck.size());
			prizes.add(playerDeck.remove(n));
		}//end for loop
		
		//Each player draws 7 cards at the beginning of the game and keeps their own hand hidden.
		for(int i = 0 ; i<7; i++)
		{
			
			hand.add(playerDeck.remove(i));
			if(hand.get(i).cardType == "Pokemon")
				pokemonCards.add(hand.get(i));
				
		}//end for loop
		
		chooseActivePokemon();
	}
	
	/*
	 * this is done only at the begining 
	 */
	public void chooseActivePokemon()
	{
		if (pokemonCards.size() >0)
		{
			System.out.println("these are the pokemon you have in hand");

			for(int i = 0 ; i<pokemonCards.size(); i++)
			{
				System.out.println(i+1 +": "+pokemonCards.get(i).name);
			}//end for loop
			System.out.println("chose which card you want to use ");
			int pokNum ;
			do
			{
				System.out.println("chose which card you want to use as your active pokemon ");
				System.out.println("Enter the number");
				pokNum  = kb.nextInt();
			}while(pokNum <1 || pokNum > pokemonCards.size());
			activePokemon = pokemonCards.get(pokNum-1);
			System.out.println(" your active pokemon is: " + activePokemon.name);
		}
		else 
			System.out.println(" there are no pokemon in your hand");
			
	}

	
	public void putCardOnBench()
	{
		if (hand.size() >0)
		{
			System.out.println("these are the Cards you have in hand");

			for(int i = 0 ; i<hand.size(); i++)
			{
				System.out.println(i+1 +": "+hand.get(i).name);
			}//end for loop
			//System.out.println("chose which card you want to add t your bench");
			int pokNum ;
			do
			{
				System.out.println("chose which card you want to add t your bench ");
				System.out.println("Enter the number");
				pokNum  = kb.nextInt();
			}while(pokNum <1 || pokNum > hand.size());
			bench.add(hand.remove(pokNum-1));
			
		}
		else 
			System.out.println(" there are no card in your hand");
			
		
	}
	
	
	public void pickFromPrizeCard()
	{
		// pick a card and then add it to hand
		if (prizes.size() >0)
		{
			System.out.println("these are the Cards you have as prizes");

			
			//System.out.println("chose which card you want to add t your bench");
			int pokNum ;
			
			do
			{
				System.out.println("chose which card you want to add t your bench ");
				System.out.println("Enter a number between 1 and " + prizes.size());
				pokNum  = kb.nextInt();
			}while(pokNum <1 || pokNum > prizes.size());
			hand.add(prizes.remove(pokNum-1));
			
		}
		else 
			System.out.println(" you have no cards as prizes");
			
		
	}
	public void drawTopSixCard()
	{
		
	}
	/*
	 * done when we want to change our active pokemon to another
	 */
	public void swapAcTivePokemon()
	{
		
	}
	
	public void evolveAcTivePokemon()
	{
		
	}
	public void attackOpponent(Card opponent)
	{
		
	}
	
	

}
