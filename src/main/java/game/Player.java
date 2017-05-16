package game;

import java.util.*;
import java.util.Random;
/**
 * Created by frede on 2017-05-15.
 * modified by kawsara 2017-05-15
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
		}//end for loop
		
		chooseActivePokemon();
	}
	
	/*
	 * this is done only at the begining 
	 */
	public void chooseActivePokemon()
	{
		
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
	
	public void putCardOnBench()
	{
		
	}
	
	
	public void pickFromPrizeCard()
	{
		// pick a card and then add it to hand
		
	}
	
	public void attackOpponent(Card opponent)
	{
		
	}
	
	

}
