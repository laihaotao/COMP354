package game;

/**
 * Created by kawsa on 5/20/2017.
 */

import card.Card;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


import card.Card;
import card.pokemon.PokemonCard;

import java.util.*;

//import javax.sound.midi.SysexMessage;

//import Card.CardType;
/**
 * Created by frede on 2017-05-15.
 * modified by kawsara 2017-05-16
 *in progress
 */
public class Ai_Player {
    //private  ArrayList<Card> listOfAllCards;
    private ArrayList<Card> deck;// = new Card[60];
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


    public Ai_Player(ArrayList<Card> playerDeck)
    {
        //Each player gets 7 cards drawn randomly at the beginning of the game
        deck = playerDeck;

        putprizes();  //lack of better name
        //Each player draws 7 cards at the beginning of the game and keeps their own hand hidden.
        for(int i = 0 ; i<7; i++)
        {
            putCardInHAnd();
        }
        chooseActivePokemon();
        putCardOnBench();
        putCardOnBench();

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
    public void putCardInHAnd()
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
        //printPokemonCards();
        if (pokemonCards.size() >0)
        {
            //System.out.println("chose which card you want to use ");
            int pokNum ;
           // do
            //{
              //  System.out.println("chose which card you want to use as your active pokemon ");
              //  System.out.println("Enter the number");
                pokNum  = rand.nextInt(pokemonCards.size());
            //}while(pokNum <1 || pokNum > pokemonCards.size());
            activePokemon = pokemonCards.get(pokNum);
            System.out.println(" your active pokemon is: " + activePokemon.getCardName());
        }
        else
            System.out.println(" you dont have pokemon in hand");


    }


    public void putCardOnBench()
    {
     //   printCardInHand();
        if (hand.size() >0)
        {
            //System.out.println("chose which card you want to add t your bench");
            int cardNum ;
            //do
            //{
              //  System.out.println("chose which card you want to add t your bench ");
                //System.out.println("Enter the number between 1 and " + hand.size());
                cardNum  = rand.nextInt(hand.size());
          //  }while(pokNum <1 || pokNum > hand.size());

            bench.add(hand.remove(cardNum));

        }
        else
            System.out.println(" you dont have card in hand");

    }


    public void pickFromPrizeCard()
    {
       // printPrizeCards();
        // pick a card and then add it to hand
        if (prizes.size() >0)
        {

            int cardNum ;

          //  do
          //  {
          //      System.out.println("chose which card you want to add t your bench ");
            //    System.out.println("Enter a number between 1 and " + prizes.size());
                cardNum  = rand.nextInt(prizes.size());;
           // }while(pokNum <1 || pokNum > prizes.size());
            hand.add(prizes.remove(cardNum));

        }
        else
            System.out.println(" you dont have pokemon in hand");

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
            putCardInHAnd();
        }

    }
    /*
     * done when we want to change our active pokemon to another
     */
    public void swapAcTivePokemon()
    {
       // printPokemonCards();
        if (pokemonCards.size() >0)
        {

            //System.out.println("chose which card you want to use ");
            int pokNum ;
           // do
            //{
              //  System.out.println("chose which card you want to use as your active pokemon ");
                //System.out.println("Enter the number");
                pokNum  = rand.nextInt(pokemonCards.size());
            //}while(pokNum <1 || pokNum > pokemonCards.size());
            activePokemon = pokemonCards.get(pokNum-1);
            //System.out.println(" your active pokemon is: " + activePokemon.getCardName());
        }
        else
            System.out.println(" you dont have pokemon in hand");


    }

    public void attackOpponent(Card opponent)
    {

    }

}