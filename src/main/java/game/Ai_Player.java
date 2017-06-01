package game;

/*
  Created by kawsa on 5/20/2017.
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
public class Ai_Player  extends  Player{


    public Ai_Player(List<Card> playerDeck)
    {
        super(playerDeck);

    }
    //Each player gets 7 cards drawn randomly at the beginning of the game

    //Each player draws 7 cards at the beginning of the game and keeps their own hand hidden.

    /*
     * this is done only at the begining
     */
    @Override
    public void chooseActivePokemon()
    {

        if (pokemonCards.size() >0)
        {
            int pokNum ;
            pokNum  = rand.nextInt(pokemonCards.size());

            for (int i=0; i<bench.size();i++) {
                if (bench.get(i) instanceof PokemonCard) {
                    activePokemon = bench.get(i);
                    bench.remove(i);
                    break;

                }
            }
        }
        else
            System.out.println(" you dont have pokemon in hand");


    }
    @Override
    public void putCardOnBench()
    {
        //printCardInHand();
        if (hand.size() >0)
        {
            //System.out.println("chose which card you want to add t your bench");
            int pokNum ;
            pokNum  = rand.nextInt(4);
            if(bench.size()<5)
                bench.add(hand.remove(pokNum));
           // else
               // System.out.println(" bench is full");

        }

    }

    /*
     * done when we want to change our active pokemon to another
     */
    @Override
    public void swapAcTivePokemon()
    {

        if (pokemonCards.size() >0)
        {
            int pokNum ;
            pokNum  = rand.nextInt(pokemonCards.size());
            bench.add(activePokemon);
            for (int i=0; i<bench.size();i++) {
                if (bench.get(i) instanceof PokemonCard) {
                    activePokemon = bench.get(i);
                    bench.remove(i);
                    break;

                }
            }
        }
        else
            System.out.println(" you dont have pokemon in hand");
    }

    public void attackOpponent(Card opponent)
    {

    }

}