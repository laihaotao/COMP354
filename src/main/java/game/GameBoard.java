/*
 * description:  GameBoard is the game controller
 * author(s):    frede
 * reviewer(s):  Eric
 * date:         2017-05-15
 */

package game;

import card.*;
import game.ai.IntelligentPlayer;
import javafx.application.Platform;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ui.BoardView;
import ui.events.PopupOnClickListener;
import ui.popup.GamePopup;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameBoard {

    private final static Logger logger = LogManager.getLogger(GameBoard.class.getName());

    enum CardLocation {
        DECK, HAND, BENCH, ACTIVE, DISCARD,
    }

    private Player[] players;
    private int currentTurn = 0;

    private TrainerCard inPlayTrainerCard = null;
    private Card selectedCard = null;
    private CardLocation selectedCardLocation = null;

    boolean isAIGame = false;
    
    private TurnInfo turnInfo;
    public BoardView view;

    public GameBoard(Player p1, Player p2) {
        if(p1 instanceof  IntelligentPlayer && p2 instanceof  IntelligentPlayer){
            isAIGame = true;
        }
        players = new Player[2];
        players[0] = p1;
        players[1] = p2;
        turnInfo = new TurnInfo();
    }

    public void attachView(BoardView view) {
        this.view = view;
    }

    // ===================================================================================
    // =============================== button listener ===================================
    // ===================================================================================
    public void onHandCardClicked(Player player, Card card) {
        logger.debug("Player" + getPlayerNum() + " has clicked a card in it's hand");
        if (card instanceof TrainerCard) {
            TrainerCard trainerCard = (TrainerCard) card;
            if (trainerCard.getAbility().getTemplate().use(this, player, trainerCard)) {
                fromHandToDiscard(player, card);
            }
        } else if (card != null && getCurrentTurnPlayer() == player) {
            setSelectedCard(card, CardLocation.HAND);
        }
    }

    public void onBenchCardClicked(Player player, Card card) {
        logger.debug("Player" + getPlayerNum() + " has clicked a card in it's bench");

        // add energy card to the bench card
        if (!turnInfo.getEnergyTrigger().already()
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
        } else if (turnInfo.getEnergyTrigger().already() && selectedCard instanceof EnergyCard) {
            GamePopup.displayMessage("You can only add one energy per turn");
        }

        // Player is trying to place pokemon card on bench
        if (selectedCard != null && selectedCardLocation == CardLocation.HAND
                && selectedCard instanceof PokemonCard && player == getCurrentTurnPlayer()) {
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
        logger.debug("Player" + getPlayerNum() + " has clicked the active pokemon");

        // add energy to active Pokemon
        if (selectedCard instanceof EnergyCard) {
            // add energy card to activated card
            if (!turnInfo.getEnergyTrigger().already()
                    && card != null && player == getCurrentTurnPlayer()
                    && selectedCard != null && selectedCard instanceof EnergyCard) {
                PokemonCard pokemonCard = (PokemonCard) card;
                EnergyCard energyCard = (EnergyCard) selectedCard;
                pokemonCard.addEnergy(energyCard);
                player.getHand().remove(energyCard);
                turnInfo.getEnergyTrigger().trigger();
                selectedCard = null;
            } else if (turnInfo.getEnergyTrigger().already()) {
                GamePopup.displayMessage("You can only add one energy per turn");
            }
        }

        // put a Pokemon to active place
        if (selectedCard != null && selectedCard instanceof PokemonCard
                && player == getCurrentTurnPlayer()) {
            PokemonCard pokemonCard = (PokemonCard) selectedCard;
            // remove selected card from player's hand and put it as active
            if (player.getActivePokemon() == null) {
                if (removeSelected()) {
                    player.setActivePokemon(pokemonCard);
                    setSelectedCard(null, null);
                }
            }
            // evolve the active Pokemon
            else if (pokemonCard.getEvolvesFrom() != null) {
                if (pokemonCard.getEvolvesFrom().equalsIgnoreCase(player.getActivePokemon()
                        .getCardName())) {
                    if (removeSelected()) {
                        pokemonCard.getEnergyAttached().add(((PokemonCard) player
                                .getActivePokemon()).getEnergyAttached());
                        player.setActivePokemon(pokemonCard);
                        setSelectedCard(null, null);
                    }
                }
            }
        }
    }

    public void onDiscardPileClicked() {
        logger.debug("Player" + getPlayerNum() + " has clicked the discard pile");
        Player player = getCurrentTurnPlayer();
        List<Card> pile = player.getDiscardPile();
        GamePopup.displayDiscardPile(this, player, pile, card ->
                logger.debug(player + " click the card in discardpile: " + card.getCardName()));
    }

    public void onActiveAbilityClicked(Player player, Card card, Ability ability) {
        logger.debug("Player " + getPlayerNum() + " has clicked " + ability.getTemplate().name
                + " on " + card.getCardName());

        if (player == getCurrentTurnPlayer()) {

            //If the card is a pokemon, abilities require energy and need to be checked against
            // card energy
            if (card instanceof PokemonCard
                    // --> ability.energycost.canSupport(card.attachedenergy)
                    && ability.getEnergyCost().canSupport(((PokemonCard) card).getEnergyAttached
                    ())) {

                if (((PokemonCard) card).getEffect().isCanAttack()) {
                    //If ability applies damage, it should trigger the Attack limit trigger
                    //Make sure player only attacks onc with a pokemon
                    if (!turnInfo.getAttackTrigger().already()) {
                        if (ability.getTemplate().use(this, player, card)) {
                            turnInfo.getAttackTrigger().trigger();
                        }
                    } else {
                        GamePopup.displayMessage("You can only attack once per turn");
                    }
                } else {
                    logger.debug(card.getCardName() + "cannot attack because of attached effect");
                }
            }

            // if the card is a trainer card
            else if (card instanceof TrainerCard) {
                if (!turnInfo.getTrainerTrigger().already()) {
                    TrainerCard trainerCard = (TrainerCard) card;

                    // if it is an stadium card and the in-play trainer card
                    // with the same name, this card cannot be played
                    if (trainerCard.getTrainerType() == TrainerCard.TrainerType.STADIUM
                            && inPlayTrainerCard.getCardName().equals(trainerCard.getCardName())) {
                        GamePopup.displayMessage("Stadium trainer card cannot be played when" +
                                "there is a same card in-play!");
                        return;
                    }

                    ability.getTemplate().use(this, player, card);

                    // stadium card will keep in-play after using it
                    if (trainerCard.getTrainerType() == TrainerCard.TrainerType.STADIUM) {
                        if (inPlayTrainerCard != null) {
                            removeTrainerCardEffect();
                        }
                        inPlayTrainerCard = trainerCard;
                    }
                    // not stadium card will go to discard pile after using it
                    else {
                        fromHandToDiscard(player, card);
                    }

                    // if this trainer card is support or stadium, trigger the limitation counter
                    if (trainerCard.getTrainerType() != TrainerCard.TrainerType.ITEM) {
                        turnInfo.getTrainerTrigger().trigger();
                    }
                } else {
                    GamePopup.displayMessage("Support or Stadium trainer card can only be " +
                            "played once per turn.");
                }
            }

            // update the Pokemon info
            checkPokemons();
        }
    }

    public void onRetreatButtonClicked(Player player) {
        if (player.getActivePokemon() != null) {
            PokemonCard pokemon = (PokemonCard) player.getActivePokemon();
            if ((pokemon.getRetreatEnergyCost().canSupport(pokemon.getEnergyAttached()))
                    && pokemon.getEffect().isCanRetreat()) {
                pokemon.getEnergyAttached().retreat(pokemon.getRetreatEnergyCost());
                player.setActivePokemon(null);
                player.getBench().add(pokemon);
            }
        }
    }

    public void onEndTurnButtonClicked() {
        nextTurn();
        logger.debug("====== end current turn ======");
    }

    public void chooseActivePokemon() {
        GamePopup.displayPokemonsInHand(this, getCurrentTurnPlayer(),
                getCurrentTurnPlayer().getPokemonCards(),
                card -> selectActivePokemon((PokemonCard) card, getCurrentTurnPlayer()));

    }
    // ===================================================================================
    // ============================ end of button listener ===============================
    // ===================================================================================

    public void applyDamageToCard(PokemonCard targetPokemon, int damage) {
        targetPokemon.setDamage(targetPokemon.getDamage() + damage);
        turnInfo.getAttackTrigger().trigger();
    }

    public void displayAndWaitForClick(List<Card> filterList, final int amount, final Player
            player) {

        GamePopup.displaySearchCards(this, player, filterList,
                new PopupOnClickListener() {
                    @Override
                    public void onClick(Card card) {
                        filterList.remove(card);
                        player.getHand().add(card);
                        int a = amount - 1;
                        if (a > 0) {
                            displayAndWaitForClick(filterList, a, player);
                        }
                    }
                });
    }

    // ===================================================================================
    // =============================== private methods ===================================
    // ===================================================================================
    private void removeTrainerCardEffect() {

    }

    private void selectActivePokemon(PokemonCard card, Player player) {
        if (turnInfo.turnNum == 0 && card != null && card.getPokemonStage().equals("basic")) {
            // turnNum = 0, means choose active Pokemon
            logger.debug("select active pokemon: " + card.getCardName());
            player.setActivePokemon(card);
            player.getHand().remove(card);
            turnInfo.turnNum++;
            view.refreshView();
        }
    }

    private void fromHandToDiscard(Player player, Card card) {
        player.getDiscardPile().add(card);
        player.getHand().remove(card);
    }

    private void checkPokemons() {
        for (Player player : players) {

            // check the active pokemon's hp
            if (player.getActivePokemon() != null) {
                PokemonCard pokemonCard = (PokemonCard) player.getActivePokemon();
                if (pokemonCard.getDamage() >= pokemonCard.getHp()) {
                    // if the active pokemon die, we need to move it the discard pile
                    // and the attached energy card
                    logger.debug("active pokemon: " + pokemonCard.getCardName() + " has been " +
                            "knock out");
                    removeAttachedEnergyCardToDiscardpile(player, pokemonCard);
                    player.getDiscardPile().add(pokemonCard);
                    player.setActivePokemon(null);
                    onCardDead(player);
                }
            }

            for (int i = 0; i < player.getBench().size(); i++) {
                Card card = player.getBench().get(i);
                if (card instanceof PokemonCard) {
                    PokemonCard pokemonCard = (PokemonCard) card;
                    if (pokemonCard.getDamage() >= pokemonCard.getHp()) {
                        // if the bench pokemon die, we need to move it the discard pile
                        // and the attached energy card
                        removeAttachedEnergyCardToDiscardpile(player, pokemonCard);
                        player.getBench().remove(pokemonCard);
                        player.getDiscardPile().add(pokemonCard);
                        i--;
                        onCardDead(player);
                    }
                }
            }
            for (int i = 0; i < player.getBench().size(); i++) {
                Card card = player.getBench().get(i);
                if (card instanceof PokemonCard) {
                    PokemonCard pokemonCard = (PokemonCard) card;
                    if (pokemonCard.getDamage() >= pokemonCard.getHp()) {
                        player.getHand().remove(card);
                        player.getDiscardPile().add(card);
                        onCardDead(player);
                        i--;
                    }
                }
            }

        }
    }

    private void removeAttachedEnergyCardToDiscardpile(Player player, PokemonCard pc) {
        ArrayList<EnergyCard> list = pc.getAttachedEnergyCard();
        for (EnergyCard energyCard : list) {
            logger.debug("add " + energyCard.getCardName() + " to discard pile");
            player.getDiscardPile().add(energyCard);
        }
        pc.getAttachedEnergyCard().clear();
    }

    private void onCardDead(Player owner) {
        getOtherPlayer(owner).choseRewardCard();
        if (getOtherPlayer(owner).getPrizes().size() == 0) {
            GamePopup.displayGameResult(getOtherPlayer(owner).getName(), true);
            if (!Thread.currentThread().getName().contains("FX")) {
                Thread.currentThread().stop();
            }
        }
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

    private boolean removeSelected() {
        switch (selectedCardLocation) {
            case HAND:
                return getCurrentTurnPlayer().hand.remove(selectedCard);
            case BENCH:
                return getCurrentTurnPlayer().getBench().remove(selectedCard);
        }
        return false;
    }

    private void nextTurn() {
        if (turnInfo.turnNum > 1) {
            checkWinLose();
        }
        turnInfo.turnNum++;
        turnInfo.reset();

        // update effect
        if (getCurrentTurnPlayer().getActivePokemon() != null) {
            PokemonCard pokemon = (PokemonCard) getCurrentTurnPlayer().getActivePokemon();
            pokemon.setEffect(pokemon.getEffect().remove());
        }

        //This will cycle between 0 and 1
        currentTurn = (currentTurn + 1) % 2;
        Player currentPlayer = getCurrentTurnPlayer();
        getOtherPlayer(currentPlayer).onEndTurn(turnInfo.turnNum - 1);

        if (view != null && !isAIGame) {
            view.refreshView();
        }
        //add card to players hand
        currentPlayer.drawOneCard();

        if (currentPlayer instanceof IntelligentPlayer) {
            ((IntelligentPlayer) currentPlayer).doTurn(this);
            if (view != null) {
                Platform.runLater(() -> {
                    view.refreshView();
                });
            }
            if (getPlayer1() instanceof IntelligentPlayer && getPlayer2() instanceof
                    IntelligentPlayer) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            ((PokemonCard)currentPlayer.getActivePokemon()).getEffect().apply();
            ((PokemonCard)getOtherPlayer(currentPlayer).getActivePokemon()).getEffect().apply();
            nextTurn();
        } else if (currentPlayer instanceof Ai_Player) {
            aiTurn();
        }

        ((PokemonCard)currentPlayer.getActivePokemon()).getEffect().apply();
        ((PokemonCard)getOtherPlayer(currentPlayer).getActivePokemon()).getEffect().apply();
    }
    
    private void aiTurn() {
        Random rand = new Random();
        int cardTOAddToBench = rand.nextInt(5);
        if (players[1].activePokemon == null) {
            //players[1].chooseActivePokemon();
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
            Platform.runLater(() -> GamePopup.displayGameResult(getCurrentTurnPlayer().getName(),
                    true));
        }

        if (getCurrentTurnPlayer().activePokemon == null) {
            boolean stillHavePokemon = false;
            for (Card c : getCurrentTurnPlayer().getBench()) {
                if (c instanceof PokemonCard &&
                        ((PokemonCard) c).getEvolvesFrom() == null) {
                    stillHavePokemon = true;
                    break;
                }
            }
            if (!stillHavePokemon) {
                Platform.runLater(() -> GamePopup.displayGameResult(getOtherPlayer(getCurrentTurnPlayer())
                        .getName(), true));
            }
        }
    }

    public int getPlayerNum() {
        return currentTurn + 1;
    }

    // ==================================================================================
    // =========================== setter and getter ====================================
    // ==================================================================================
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

    public TurnInfo getTurnInfo() {
        return turnInfo;
    }

    public void setCurrentTurn(int currentTurn) {
        this.currentTurn = currentTurn;
    }
}
