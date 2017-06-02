package game;


import card.Card;
import card.PokemonCard;

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

import ui.selections.RewardSelector;

/**
 * Created by frede on 2017-05-15.
 */
public class Player {

    //function
    //private  ArrayList<Card> listOfAllCards;
    protected List<Card> deck;// = new Card[60];
    protected List<Card> prizes = new ArrayList<>();//= new Card[6];
    protected List<Card> hand = new ArrayList<>();
    protected List<Card> bench = new ArrayList<>();//= new Card[5];;
    protected List<Card> discardPile = new ArrayList<>();
    protected PokemonCard activePokemon;
    protected List<PokemonCard> pokemonCards = new ArrayList<>();
    protected List<Card> energyCards = new ArrayList<>();
    private List<Card> trainerCards = new ArrayList<>();
    private Scanner kb = new Scanner(System.in);
    Random rand = new Random();


    public Player(List<Card> playerDeck) {
        //Each player gets 7 cards drawn randomly at the beginning of the game
        deck = playerDeck;

        putPrizes();  //lack of better name
        //Each player draws 7 cards at the beginning of the game and keeps their own hand hidden.

        put7CardInHand();

        //chooseActivePokemon();
    }

    public void put7CardInHand() {

        for (int i = 0; i < 7; i++) {
            putCardInHand();
        }
        checkForPokemon();
    }

    public void checkForPokemon() {

        if (pokemonCards.size() == 0) {
            int handSize = hand.size();
            while (handSize > 0) {
                deck.add(hand.remove(0));
                handSize--;
            }

            put7CardInHand(); // this takes care of Mulligans // change when deck is updated
        }
    }

    //Each player gets 7 cards drawn randomly at the beginning of the game
    public void putPrizes() {
        for (int i = 0; i < 6; i++) {
            int n = rand.nextInt(deck.size());
            prizes.add(deck.remove(n));
        }


    }

    //Each player draws 7 cards at the beginning of the game and keeps their own hand hidden.
    public void putCardInHand() {
        hand.add(deck.remove(0));
        if (hand.get(hand.size() - 1) instanceof PokemonCard)//  .getType().equals("POKEMON"))
            pokemonCards.add((PokemonCard) hand.get(hand.size() - 1));

    }

    /*
     * this is done only at the begining
     */
    public void chooseActivePokemon() {
        printPokemonCards();
        if (pokemonCards.size() > 0) {
            //System.out.println("chose which card you want to use ");
            int pokNum;
            do {
                System.out.println("chose which card you want to use as your active pokemon ");
                System.out.println("Enter the number");
                pokNum = kb.nextInt();
            } while (pokNum < 1 || pokNum > pokemonCards.size());
            activePokemon = pokemonCards.get(pokNum - 1);
            System.out.println(" your active pokemon is: " + activePokemon.getCardName());
        }

    }


    public void putCardOnBench() {
        printCardInHand();
        if (hand.size() > 0) {
            //System.out.println("chose which card you want to add t your bench");
            int pokNum;
            do {
                System.out.println("chose which card you want to add t your bench ");
                System.out.println("Enter the number between 1 and " + hand.size());
                pokNum = kb.nextInt();
            } while (pokNum < 1 || pokNum > hand.size());
            if (bench.size() < 5)
                bench.add(hand.remove(pokNum - 1));
            else
                System.out.println(" bench is full");

        }

    }


    public void pickFromPrizeCard() {
        printPrizeCards();
        // pick a card and then add it to hand
        if (prizes.size() > 0) {

            int pokNum;

            do {
                System.out.println("chose which card you want to add t your bench ");
                System.out.println("Enter a number between 1 and " + prizes.size());
                pokNum = kb.nextInt();
            } while (pokNum < 1 || pokNum > prizes.size());
            hand.add(prizes.remove(pokNum - 1));

        }

    }

    public void drawTopSixCard() {
        for (int i = 0; i < 6; i++) {
            putCardInHand();
        }

    }

    public void printPrizeCards() {
        if (prizes.size() > 0) {
            System.out.println("these are the Cards you have as prizes");
        } else
            System.out.println(" you have no cards as prizes");

    }

    public void printCardInBench() {
        if (bench.size() > 0) {
            System.out.println("these are the Cards you have in bench");

            for (int i = 0; i < bench.size(); i++) {
                System.out.println(i + 1 + ": " + bench.get(i).getCardName());
            }//end for loop

        } else
            System.out.println(" there are no card on your bench");


    }

    public void printCardInHand() {
        if (hand.size() > 0) {
            System.out.println("these are the Cards you have in hand");

            for (int i = 0; i < hand.size(); i++) {
                System.out.println(i + 1 + ": " + hand.get(i).getCardName());
            }//end for loop

        } else
            System.out.println(" there are no card in your hand");

    }

    public void printCardOnDeck() {

        if (deck.size() > 0) {
            System.out.println("these are the Cards you have in your deck");

            for (int i = 0; i < deck.size(); i++) {
                System.out.println(i + 1 + ": " + deck.get(i).getCardName());
            }//end for loop

        } else
            System.out.println(" there are no card in your deck");

    }

    public void printPokemonCards() {
        if (pokemonCards.size() > 0) {
            System.out.println("these are the pokemon you have in hand");

            for (int i = 0; i < pokemonCards.size(); i++) {
                System.out.println(i + 1 + ": " + pokemonCards.get(i).getCardName());
            }//end for loop
        } else
            System.out.println(" there are no pokemon in your hand");
    }

    /*
     * done when we want to change our active pokemon to another
     */
    public void swapAcTivePokemon() {
        printPokemonCards();
        if (pokemonCards.size() > 0) {

            //System.out.println("chose which card you want to use ");
            int pokNum;
            do {
                System.out.println("chose which card you want to use as your active pokemon ");
                System.out.println("Enter the number");
                pokNum = kb.nextInt();
            } while (pokNum < 1 || pokNum > pokemonCards.size());
            activePokemon = pokemonCards.get(pokNum - 1);
            System.out.println(" your active pokemon is: " + activePokemon.getCardName());
        }


    }

    public void attackOpponent(Card opponent) { }

    public void attachEnergyCard() {}

    public void attachEnergyCardToActivePokemon() {}

    public List<Card> getDeck() {
        return deck;
    }

    public List<Card> getHand() {
        return hand;
    }

    public List<Card> getBench() {
        return bench;
    }

    public Card getActivePokemon() {
        return activePokemon;
    }

    public void setActivePokemon(PokemonCard card) {
        activePokemon = card;
    }

    public List<Card> getPrizes() {
        return prizes;
    }

    public void choseRewardCard() {
        Card selectedPrize = RewardSelector.getReward(this);
        if (selectedPrize != null) {
            prizes.remove(selectedPrize);
            hand.add(selectedPrize);
        }
    }
}