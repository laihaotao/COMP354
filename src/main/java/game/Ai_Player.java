/*
 * description:  The class AI player
 * author(s):    frede
 * reviewer(s):  kawsara
 * date:         2017-05-20
 */

package game;

import card.Card;
import card.EnergyCard;
import card.PokemonCard;

import java.util.List;

public class Ai_Player extends Player {


    public Ai_Player(List<Card> playerDeck) {
        super(playerDeck);

    }
    //Each player gets 7 cards drawn randomly at the beginning of the game

    //Each player draws 7 cards at the beginning of the game and keeps their own hand hidden.

    /*
     * this is done only at the begining
     */
    @Override
    public void chooseActivePokemon() {

        if (pokemonCards.size() > 0) {
            int pokNum;
            pokNum = rand.nextInt(pokemonCards.size());

            for (int i = 0; i < bench.size(); i++) {
                if (bench.get(i) instanceof PokemonCard) {
                    activePokemon = (PokemonCard) bench.get(i);
                    bench.remove(i);
                    break;

                }
            }
        } else
            System.out.println(" you dont have pokemon in hand");


    }

    @Override
    public void putCardOnBench() {
        //printCardInHand();
        if (hand.size() > 0) {
            //System.out.println("chose which card you want to add t your bench");
            int pokNum;
            pokNum = rand.nextInt(hand.size());
            if (bench.size() < 5)
                if (hand.get(pokNum) instanceof PokemonCard) {
                    bench.add(hand.remove(pokNum));
                } else {
                    boolean hasPokemon = false;
                    for (Card card : hand) {
                        if (card instanceof PokemonCard) {
                            hasPokemon = true;
                        }
                    }
                    if (hasPokemon) {
                        putCardOnBench();
                    }
                }
            // else
            // System.out.println(" bench is full");

        }

    }

    public void attachEnergyCardToActivePokemon() {
        for (Card c : hand) {
            if (c instanceof EnergyCard) {
                //activePokemon.setEnergyAttached((PokemonCard)c);
                activePokemon.getEnergyAttached().addEnergy(((EnergyCard) c).getEnergyType()
                        .toString(), 1);
                getHand().remove(c);
                break;
            }

        }
    }

    @Override
    public void attachEnergyCard() {


        for (Card card : bench) {
            if (card instanceof PokemonCard) {

                for (Card c : hand) {
                    if (c instanceof EnergyCard) {
                        //activePokemon.setEnergyAttached((PokemonCard)c);
                        ((PokemonCard) card).getEnergyAttached().addEnergy(((EnergyCard) c)
                                .getEnergyType().toString(), 1);
                        getHand().remove(c);
                        break;
                    }

                }
                break;
            }
        }


    }

    /*
     * done when we want to change our active pokemon to another
     */
    @Override
    public void swapAcTivePokemon() {

        if (pokemonCards.size() > 0) {
            int pokNum;
            pokNum = rand.nextInt(pokemonCards.size());
            bench.add(activePokemon);
            for (int i = 0; i < bench.size(); i++) {
                if (bench.get(i) instanceof PokemonCard) {
                    activePokemon = (PokemonCard) bench.get(i);
                    bench.remove(i);
                    break;

                }
            }
        } else {
            System.out.println(" you dont have pokemon in hand");
        }
    }

    public void attackOpponent(Card opponent) {

    }

}