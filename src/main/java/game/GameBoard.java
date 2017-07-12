/*
 * description:  GameBoard is the game controller
 * author(s):    frede
 * reviewer(s):  Eric
 * date:         2017-05-15
 */

package game;

import card.Card;
import card.Ability;
import card.EnergyCard;
import card.PokemonCard;
import game.ai.IntelligentPlayer;
import javafx.application.Platform;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ui.BoardView;
import ui.events.DiscardPileOnClickListener;
import ui.popup.GamePopup;

import java.util.*;

public class GameBoard {

    enum CardLocation {
        DECK, HAND, BENCH, ACTIVE, DISCARD,
    }

    private final static Logger logger = LogManager.getLogger(GameBoard.class.getName());

    private Player[] players;

    private int currentTurn = 0;

    private Card selectedCard = null;
    private CardLocation selectedCardLocation = null;
    private Random rand = new Random();

    private TurnInfo turnInfo;

    public BoardView view;
    

    public GameBoard(Player p1, Player p2) {
        players = new Player[2];
        players[0] = p1;
        players[1] = p2;
        turnInfo = new TurnInfo();
    }

    public void attachView(BoardView view){
        this.view = view;
    }
    
    private void setSelectedCard(Card card, CardLocation location) {
        if (selectedCard != null) {
            selectedCard.setSelected(false);
        }

        selectedCard = card;
        if (selectedCard != null) {
            selectedCard.setSelected(true);
        }
        selectedCardLocation = location;
    }

    public void onHandCardClicked(Player player, Card card) {
        int playerNum = (player == players[0]) ? 1 : 2;
        logger.debug("Player" + playerNum + " has clicked a card in it's hand");

        if (card != null && (playerNum - 1) == currentTurn) {
            setSelectedCard(card, CardLocation.HAND);
        }

    }

    public void onBenchCardClicked(Player player, Card card) {
        int playerNum = (player == players[0]) ? 1 : 2;
        logger.debug("Player" + playerNum + " has clicked a card in it's bench");

        // add energy card to the bench card
        if (!turnInfo.getEnergyTrigger().getStatus()
                && card != null && player == getCurrentTurnPlayer()
                && selectedCard != null && selectedCard instanceof EnergyCard) {
            if (card instanceof PokemonCard) {
                PokemonCard pokemonCard = (PokemonCard) card;
                EnergyCard energyCard = (EnergyCard) selectedCard;
                pokemonCard.addEnergy(energyCard);
                player.getHand().remove(energyCard);
                selectedCard = null;
                turnInfo.getEnergyTrigger().trigger();
                return;
            }
        } else if (turnInfo.getEnergyTrigger().getStatus()) {
            GamePopup.displayMessage("You can only add one energy per turn");
        }

        //Player is trying to place pokemon card on bench
        if (selectedCard != null && selectedCardLocation == CardLocation.HAND && selectedCard
                instanceof PokemonCard && player == getCurrentTurnPlayer()) {

            //remove selected card from player's hand and put it on the player's bench
            if (player.getHand().remove(selectedCard)) {
                player.getBench().add(selectedCard);
                setSelectedCard(null, null);
            }

            return;
        }

        if (player == getCurrentTurnPlayer() && card != null) {
            setSelectedCard(card, CardLocation.BENCH);
        }

    }

    public void onActiveCardClicked(Player player, Card card) {
        int playerNum = (player == players[0]) ? 1 : 2;
        logger.debug("Player" + playerNum + " has clicked the active pokemon");

        // add energy card to activated card
        if (!turnInfo.getEnergyTrigger().getStatus()
                && card != null && player == getCurrentTurnPlayer() && selectedCard != null &&
                selectedCard instanceof EnergyCard) {
            PokemonCard pokemonCard = (PokemonCard) card;
            EnergyCard energyCard = (EnergyCard) selectedCard;
            pokemonCard.addEnergy(energyCard);
//            player.getDiscardPile().add(energyCard);
            player.getHand().remove(energyCard);
            selectedCard = null;
            turnInfo.getEnergyTrigger().trigger();
        } else if (turnInfo.getEnergyTrigger().getStatus()) {
            GamePopup.displayMessage("You can only add one energy per turn");
        }

        if (selectedCard != null && selectedCard instanceof
                PokemonCard && player == getCurrentTurnPlayer()) {
            PokemonCard pokemonCard = (PokemonCard) selectedCard;
            if (player.getActivePokemon() == null) {
                //remove selected card from player's hand and put it as active
                if (removeSelected()) {
                    player.setActivePokemon(pokemonCard);
                    setSelectedCard(null, null);
                }
            } else if (pokemonCard.getEvolvesFrom() != null) {
                if (pokemonCard.getEvolvesFrom().equalsIgnoreCase(player.getActivePokemon()
                        .getCardName())) {
                    if (removeSelected()) {
                        player.setActivePokemon(pokemonCard);
                        setSelectedCard(null, null);
                    }
                }
            }
        }
    }

    public void onDiscardPileClicked() {
        Player player = getCurrentTurnPlayer();
        int playerNum = (player == players[0]) ? 1 : 2;
        logger.debug("Player" + playerNum + " has clicked the discard pile");

        List<Card> pile = player.getDiscardPile();
        GamePopup.displayDiscardPile(player, pile, card ->
                logger.debug(player + " click the card in discardpile: " + card.getCardName()));
    }

    private boolean removeSelected() {
        switch (selectedCardLocation) {
            case HAND:
                return getCurrentTurnPlayer().hand.remove(selectedCard);
            case BENCH:
                return getCurrentTurnPlayer().getBench().remove(selectedCard);
        }
        return false;
    }

    public void onActiveAbilityClicked(Player player, Card card, Ability ability) {
        int playerNum = (player == players[0]) ? 1 : 2;
        logger.debug("Player " + playerNum + " has clicked " + ability.getTemplate().name
                + " on "+ card.getCardName());

        if (player == getCurrentTurnPlayer()) {

            //If the card is a pokemon, abilities require energy and need to be checked against card energy
            if(card instanceof PokemonCard
                    // attention here, want to compare the energy cost must use the
                    // required energy equal to the attached energy
                    // --> ability.energycost.equals(card.attachedenergy)
                    && ability.getEnergyCost().equals(((PokemonCard)card).getEnergyAttached())) {

                //If ability applies damage, it should trigger the Attack limit trigger
                if(ability.getTemplate().appliesDamage()){
                    //Make sure player only attacks onc with a pokemon
                    if(!turnInfo.getAttackTrigger().getStatus()) {
                        turnInfo.getAttackTrigger().trigger();
                        ability.getTemplate().use(this, player);
                    } else {
                        GamePopup.displayMessage("You can only attack once per turn");
                    }
                }else{
                    //regular ability, does not apply damage and thus does not trigger attack limit
                    ability.getTemplate().use(this, player);
                }
            }
            
            checkPokemons();
        }
    }

    public void applyDamageToCard(Player callingPlayer, PokemonCard targetPokemon, int damage) {
            targetPokemon.setDamage(targetPokemon.getDamage() + damage);
            turnInfo.getAttackTrigger().trigger();
    }

    private void checkPokemons() {
        for (Player player : players) {

            // check the active pokemon's hp
            if (player.getActivePokemon() != null) {
                PokemonCard pokemonCard = (PokemonCard) player.getActivePokemon();
                if (pokemonCard.getDamage() >= pokemonCard.getHp()) {
                    // if the active pokemon die, we need to move it the discard pile
                    // and the attached energy card
                    logger.debug("active pokemon: " + pokemonCard.getCardName() + " has been knock out");
                    removeAttachedEnergyCardToDiscardpile(pokemonCard);
                    player.getDiscardPile().add(pokemonCard);
                    player.setActivePokemon(null);
                    onCardDead(player);
                }
            }

            // check the bench pokemon's hp
            player.getBench().forEach((card -> {
                if (card instanceof PokemonCard) {
                    PokemonCard pokemonCard = (PokemonCard) card;
                    if (pokemonCard.getDamage() >= pokemonCard.getHp()) {
                        // if the bench pokemon die, we need to move it the discard pile
                        // and the attached energy card
                        removeAttachedEnergyCardToDiscardpile(pokemonCard);
                        player.getBench().remove(pokemonCard);
                        player.getDiscardPile().add(pokemonCard);
                        onCardDead(player);
                    }
                }
            }));

            // check the hand pokemon's hp
            player.getHand().forEach((card -> {
                if (card instanceof PokemonCard) {
                    PokemonCard pokemonCard = (PokemonCard) card;
                    if (pokemonCard.getDamage() >= pokemonCard.getHp()) {
                        player.getHand().remove(card);
                        player.getDiscardPile().add(card);
                        onCardDead(player);
                    }
                }
            }));
        }
    }

    private void removeAttachedEnergyCardToDiscardpile(PokemonCard pc) {
        ArrayList<EnergyCard> list = pc.getAttachedEnergyCard();
        for (EnergyCard energyCard : list) {
            logger.debug("add " + energyCard.getCardName() + " to discard pile");
            getCurrentTurnPlayer().getDiscardPile().add(energyCard);
        }
        pc.getAttachedEnergyCard().clear();
    }

    private void onCardDead(Player owner) {
        getOtherPlayer(owner).choseRewardCard();
        if (getOtherPlayer(owner).getPrizes().size() == 0) {
            GamePopup.displayGameResult(getOtherPlayer(owner).getName(), true);
            if(!Thread.currentThread().getName().contains("FX")){
                Thread.currentThread().stop();
            }
        }
    }

    public void onEndTurnButtonClicked() {
        if (turnInfo.turnNum != 1) {
            checkWinLose();
        }
        nextTurn();
        turnInfo.turnNum++;
    }

    private void nextTurn() {
        turnInfo.reset();
        //This will cycle between 0 and 1
        currentTurn = (currentTurn + 1) % 2;

        Player currentPlayer = getCurrentTurnPlayer();

        //add card to players hand
        currentPlayer.putCardInHand();

            if(currentPlayer instanceof IntelligentPlayer){
                ((IntelligentPlayer) currentPlayer).doTurn(this);
                if(view != null) {
                    Platform.runLater(()->{
                        view.refreshView();
                    });
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                nextTurn();
            }else if(currentPlayer instanceof Ai_Player){
                aiTurn();
            }
        

    }

    private void aiTurn() {

        int cardTOAddToBench = rand.nextInt(5);
        if (players[1].activePokemon == null) {
            players[1].chooseActivePokemon();
        }
        for (int i = 0; i < (5 - cardTOAddToBench); i++) {
            players[1].putCardOnBench();
        }
        int pokNum = rand.nextInt(2);
        if (pokNum == 0 & players[1].activePokemon != null) {
            players[1].attachEnergyCardToActivePokemon();
        } else
            players[1].attachEnergyCard();

        if (players[1].activePokemon != null) {
            pokNum = rand.nextInt(players[1].activePokemon.getAbilities().size());
            onActiveAbilityClicked(players[1],
                    players[1].activePokemon, players[1].activePokemon.getAbility(pokNum));
        }
        //players[1].putCardOnBench();
        //players[1].activePokemon  this is suppose to attack
        nextTurn();
    }

    /*
      win conditions:
        1. Take all of your Prize cards.
        2. Knock Out all of your opponent’s in-play Pokemon.
        3. If your opponent has no cards in their deck at the beginning of their turn.
    */
    private void checkWinLose() {
        if (getCurrentTurnPlayer().prizes.size() == 0 || getWaitingTurnPlayer().deck.size() == 0) {
            logger.debug("prize card size or enemy deck size equal zero");
            GamePopup.displayGameResult(getCurrentTurnPlayer().getName(), true);
        }

        if (getCurrentTurnPlayer().activePokemon == null) {
            boolean stillHavePokemon = false;
            for (Card c : getWaitingTurnPlayer().getBench()) {
                if (c instanceof PokemonCard &&
                        ((PokemonCard) c).getEvolvesFrom() == null) {
                    stillHavePokemon = true;
                    break;
                }
            }
            if (!stillHavePokemon) {
                GamePopup.displayGameResult(getCurrentTurnPlayer().getName(), true);
            }
        }
    }

    public Player[] getPlayers() {
        return players;
    }

    public Player getPlayer1() {
        return players[0];
    }

    public Player getPlayer2() {
        return players[1];
    }

    public Player getCurrentTurnPlayer() {
        return players[currentTurn];
    }

    public Player getWaitingTurnPlayer() {
        return players[(currentTurn + 1) % 2];
    }

    public Player getOtherPlayer(Player player) {
        if (player == players[0]) {
            return players[1];
        }

        return players[0];
    }

    public void onRetreatButtonClicked(Player player) {
        if (player.getActivePokemon() != null) {
            PokemonCard card = (PokemonCard) player.getActivePokemon();
            if ((card.getRetreatEnergyCost().equals(card.getEnergyAttached()))) {
                card.getEnergyAttached().retreat(card.getRetreatEnergyCost());
                player.setActivePokemon(null);
                player.getBench().add(card);
            }
        }
    }
}
