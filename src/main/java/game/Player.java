/*
 * description:  The class of player
 * author(s):    frede
 * reviewer(s):  kawsara
 * date:         2017-05-15
 */

package game;


import card.Card;
import card.PokemonCard;
import entry.GameApp;
import game.events.PlayerEndTurnEvent;
import java.util.Optional;
import java.util.Stack;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import parser.abilities.parts.AbilityPart;
import parser.abilities.parts.AbilityPartDam;
import parser.abilities.parts.AbilityPartDeck;
import parser.abilities.properties.TargetProperty;
import ui.popup.GamePopup;
import ui.selections.RewardSelector;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import ui.selections.TargetSelectorUI;

public class Player {

    protected List<Card> deck;// = new Card[60];
    protected List<Card> prizes = new ArrayList<>();//= new Card[6];
    protected List<Card> hand = new ArrayList<>();
    protected List<Card> bench = new ArrayList<>();//= new Card[5];;
    protected List<Card> discardPile = new ArrayList<>();
    protected PokemonCard activePokemon = null;
    protected List<PokemonCard> pokemonCards = new ArrayList<>();
    protected List<Card> energyCards = new ArrayList<>();
    private List<Card> trainerCards = new ArrayList<>();
    private Scanner kb = new Scanner(System.in);
    Random rand = new Random();
    private String name;

    private Stack<PlayerEndTurnEvent> onEndTurnEvents;
    
    private game.TargetSelector targetSelector;

    public Player(List<Card> playerDeck) {
        //Each player gets 7 cards drawn randomly at the beginning of the game
        deck = playerDeck;
        putPrizes();
        draw7Cards();
        targetSelector = createTargetSelector();

        onEndTurnEvents = new Stack<>();
    }

    public void addEndTurnEvent(PlayerEndTurnEvent event) {
        onEndTurnEvents.add(event);
    }
    
    public void onEndTurn(int turnNum) {
        while(!onEndTurnEvents.empty()) {
            onEndTurnEvents.pop().onEndTurn(this);
        }
    }
    
    public TargetSelector createTargetSelector() {
        return new TargetSelectorUI();
    }

    public void draw7Cards() {
        for (int i = 0; i < 7; i++) {
            drawOneCard();
        }
        //checkMulligans();
    }

    public void checkMulligans() {
        // pokemonCards is used to trace the pokemon cards in the hand
        if (pokemonCards.size() == 0) {
            int handSize = hand.size();
            while (handSize > 0) {
                // put the current hand card at the end of the deck
                deck.add(hand.remove(0));
                handSize--;
            }

            draw7Cards();
        }
    }

    public void drawNewCards(){
        int handSize = hand.size();
        while (handSize > 0) {
            // put the current hand card at the end of the deck
            deck.add(hand.remove(0));
            handSize--;
        }

        draw7Cards();
    }

    //Each player gets 7 cards drawn randomly at the beginning of the game
    public void putPrizes() {
        for (int i = 0; i < 6; i++) {
            prizes.add(deck.remove(deck.size() - i - 1));
        }
    }

    //Each player draws 7 cards at the beginning of the game and keeps their own hand hidden.
    public void drawOneCard() {
        hand.add(deck.remove(0));
        if (hand.get(hand.size() - 1) instanceof PokemonCard) {//  .getType().canSupport("POKEMON"))
            pokemonCards.add((PokemonCard) hand.get(hand.size() - 1));
        }
    }

    /*
     * this is done only at the begining
     */
    public void chooseActivePokemon(GameBoard gameBoard) {
        GamePopup.displayPokemonsInHand(gameBoard, this,
            this.getPokemonCards(), (card)-> {
                this.activePokemon = (PokemonCard)card;
                this.hand.remove(activePokemon);
                gameBoard.view.refreshView();
        });
            
    }
    
    public Card getLastTarget(int rollback){
        return targetSelector.getLastCardTarget(rollback);
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
            drawOneCard();
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


    public void attackOpponent(Card opponent) {
    }

    public void attachEnergyCard() {
    }

    public void attachEnergyCardToActivePokemon() {
    }

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

    public List<Card> getDiscardPile() {
        return discardPile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Card getTarget(GameBoard gameBoard, Card callingCard, TargetProperty target) {
        return targetSelector.getCard(gameBoard, this,callingCard, target);
    }

    public Player getTargetPlayer(GameBoard gameBoard, TargetProperty target) {
        return targetSelector.getPlayer(gameBoard, this, target);
    }

    public List<PokemonCard> getPokemonCards() {
        return pokemonCards;
    }
    
    public boolean ShouldDoAbility(GameBoard board, AbilityPart ability){
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Ability choice");
        alert.setHeaderText("Do you want to use: "+ability.getCurrentDescription(board, this));
        
        ButtonType buttonTypeOne = new ButtonType("Yes");
        ButtonType buttonTypeTwo = new ButtonType("No");

        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne){
            return true;
        } else if (result.get() == buttonTypeTwo) {
            return false;
        }
        
        return false;
    }
    
    public Card selectCardToDiscardFromHand(){
        return TargetSelectorUI.selectCard(hand);
    }
    
    public boolean shouldDoDeckAbility(GameBoard board, AbilityPartDeck abilityPartDeck){
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Deck choice");
        alert.setHeaderText("Do you want to use: "+abilityPartDeck.getCurrentDescription(board, this));

        ButtonType buttonTypeOne = new ButtonType("Yes");
        ButtonType buttonTypeTwo = new ButtonType("No");

        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne){
            return true;
        } else if (result.get() == buttonTypeTwo) {
            return false;
        }

        return false;
    }

    public boolean shouldDrawMulCard(){
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Draw card choice");
        alert.setHeaderText("The other player had a mulligan, do you want to draw a card");

        ButtonType buttonTypeOne = new ButtonType("Yes");
        ButtonType buttonTypeTwo = new ButtonType("No");

        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne){
            return true;
        } else if (result.get() == buttonTypeTwo) {
            return false;
        }

        return false;
    }
}