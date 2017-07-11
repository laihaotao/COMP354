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
import game.ai.InteliigentPlayer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import parser.cards.EnergyCost;
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


    public GameBoard(Player p1, Player p2) {
        players = new Player[2];
        players[0] = p1;
        players[1] = p2;
        turnInfo = new TurnInfo();
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
                pokemonCard.getEnergyAttached().addEnergy(energyCard.getEnergyType().toString(), 1);
                player.getHand().remove(energyCard);
                selectedCard = null;
                turnInfo.getEnergyTrigger().trigger();
                return;
            }
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
            pokemonCard.getEnergyAttached().addEnergy(energyCard.getEnergyType().toString(), 1);
            player.getDiscardPile().add(energyCard);
            player.getHand().remove(energyCard);
            selectedCard = null;
            turnInfo.getEnergyTrigger().trigger();

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
        GamePopup.displayDiscardPile(player, pile, new DiscardPileOnClickListener() {
            @Override
            public void onClickDiscardCard(Card card) {
                logger.debug(player + " click the card in discardpile: " + card.getCardName());
            }
        });
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
        logger.debug("Player " + playerNum + " has clicked " + ability.getTemplate().name + " on " +
                "" + card.getCardName());

        if (player == getCurrentTurnPlayer()) {

            //If the card is a pokemon, abilities require energy and need to be checked against card energy
            if(card instanceof PokemonCard && enoughEnergy(ability.getEnergyCost(), ((PokemonCard)card).getEnergyAttached())) {

                //If ability applies damage, it should trigger the Attack limit trigger
                if(ability.getTemplate().appliesDamage()){
                    //Make sure player only attacks onc with a pokemon
                    if(!turnInfo.getAttackTrigger().getStatus()) {
                        turnInfo.getAttackTrigger().trigger();
                        ability.getTemplate().use(this, player);
                    }
                }else{
                    //regular ability, does not apply damage and thus does not trigger attack limit
                    ability.getTemplate().use(this, player);
                }
            }else {

            }
            checkPokemons();
        }
    }

    public void applyDamageToCard(Player callingPlayer, PokemonCard targetPokemon, int damage) {
            targetPokemon.setDamage(targetPokemon.getDamage() + damage);
            turnInfo.getAttackTrigger().trigger();
    }

    private boolean enoughEnergy(EnergyCost energyCost, EnergyCost energyAttached) {
        return energyAttached.colorless >= energyCost.colorless
                && energyAttached.water >= energyCost.water
                && energyAttached.lightning >= energyCost.lightning
                && energyAttached.psychic >= energyCost.psychic
                && energyAttached.fight >= energyCost.fight;
    }

    private void checkPokemons() {
        for (Player player : players) {
            if (player.getActivePokemon() != null) {
                PokemonCard pokemonCard = (PokemonCard) player.getActivePokemon();
                if (pokemonCard.getDamage() >= pokemonCard.getHp()) {
                    player.getDiscardPile().add(player.getActivePokemon());
                    player.setActivePokemon(null);
                    onCardDead(player);
                }
            }
            player.getBench().forEach((card -> {
                if (card instanceof PokemonCard) {
                    PokemonCard pokemonCard = (PokemonCard) card;
                    if (pokemonCard.getDamage() >= pokemonCard.getHp()) {
                        player.getBench().remove(card);
                        player.getDiscardPile().add(card);
                        onCardDead(player);
                    }
                }
            }));
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


    private void onCardDead(Player owner) {
        getOtherPlayer(owner).choseRewardCard();
        if (currentTurn == 0) {
            players[1].chooseActivePokemon();
        }
        if (getOtherPlayer(owner).getPrizes().size() == 0) {
            GamePopup.displayGameResult(getOtherPlayer(owner).getName(), true);
        }
    }

    public void onEndTurnButtonClicked() {
        //checkWinLose(); //NOTE commented out until method is fixed
        nextTurn();

        //TODO process AI turn

        //finish AI turn
        // nextTurn();
    }

    private void nextTurn() {
        turnInfo.reset();
        //This will cycle between 0 and 1
        currentTurn = (currentTurn + 1) % 2;

        Player currentPlayer = getCurrentTurnPlayer();

        //add card to players hand
        currentPlayer.putCardInHand();

        if (currentTurn == 1) {
            if(currentPlayer instanceof InteliigentPlayer){
                ((InteliigentPlayer) currentPlayer).doTurn(this);
                nextTurn();
            }else {
                aiTurn();
            }
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

    //TODO this method is broken, exits the game on first turn
    private void checkWinLose() {
        boolean stillHavePokemon = false;

        if (getCurrentTurnPlayer().prizes.size() == 0 || getWaitingTurnPlayer().deck.size() == 0) {
            //printWinMsg();
            // NOTE: the win message should always use
            // GamePopup.displayGameResult(player.getName(), true);
            // Since it uses the javafx gui instead of the console
        }

        for (Card c : getWaitingTurnPlayer().getBench()) {
            if (c instanceof PokemonCard &&
                    ((PokemonCard) c).getEvolvesFrom() == null) {
                stillHavePokemon = true;
                break;
            }
        }
        if (!stillHavePokemon) {
            //printWinMsg();
            // See above note
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
            Card card = player.getActivePokemon();
            player.setActivePokemon(null);
            player.getBench().add(card);
        }
    }
}
