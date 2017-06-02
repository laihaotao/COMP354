package game;

import card.Card;
import card.Ability;
import card.EnergyCard;
import card.PokemonCard;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

/**
 * Created by frede on 2017-05-15.
 */
public class GameBoard {

    final static Logger logger = LogManager.getLogger(GameBoard.class.getName());

    private Player[] players;

    private int currentTurn = 0;

    private Card selectedCard = null;
    private CardLocation selectedCardLocation = null;
    private Random rand = new Random();

    public GameBoard(Player p1, Player p2) {
        players = new Player[2];
        players[0] = p1;
        players[1] = p2;
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

        if (card != null && player == getCurrentTurnPlayer() && selectedCard != null &&
                selectedCard instanceof EnergyCard) {
            if (card instanceof PokemonCard) {
                PokemonCard pokemonCard = (PokemonCard) card;
                EnergyCard energyCard = (EnergyCard) selectedCard;
                pokemonCard.getEnergyAttached().addEnergy(energyCard.getEnergyType().toString(), 1);
                player.getHand().remove(energyCard);
                selectedCard = null;
            }
        }

        //Player is trying to place pokemon card on bench
        if (selectedCard != null && selectedCardLocation == CardLocation.HAND && selectedCard
                instanceof PokemonCard && player == getCurrentTurnPlayer()) {

            //remove selected card from player's hand and put it on the player's bench
            if (players[0].getHand().remove(selectedCard)) {
                players[0].getBench().add(selectedCard);
                setSelectedCard(null, null);
            }

        }

    }

    public void onActiveCardClicked(Player player, Card card) {
        int playerNum = (player == players[0]) ? 1 : 2;
        logger.debug("Player" + playerNum + " has clicked the active pokemon");


        if (card != null && player == getCurrentTurnPlayer() &&
                selectedCard != null && selectedCard instanceof EnergyCard) {
            PokemonCard pokemonCard = (PokemonCard) card;
            EnergyCard energyCard = (EnergyCard) selectedCard;
            pokemonCard.getEnergyAttached().addEnergy(energyCard.getEnergyType().toString(), 1);
            player.getHand().remove(energyCard);
            selectedCard = null;
        }

        if (players[0].getActivePokemon() == null && selectedCard != null && selectedCardLocation
                == CardLocation.HAND && selectedCard instanceof PokemonCard && player ==
                getCurrentTurnPlayer()) {

            //remove selected card from player's hand and put it as active
            if (players[0].getHand().remove(selectedCard)) {
                players[0].setActivePokemon(selectedCard);
                setSelectedCard(null, null);
            }

        }
    }

    public void onActiveAbilityClicked(Player player, Card card, Ability ability) {
        int playerNum = (player == players[0]) ? 1 : 2;
        logger.debug("Player " + playerNum + " has clicked " + ability.getTemplate().name + " on " +
                "" + card.getCardName());
        if (player == getCurrentTurnPlayer()) {
            ability.getTemplate().use(this, player);
            checkPokemons();
        }
    }

    private void checkPokemons() {
        for (Player player : players) {
            if (player.getActivePokemon() != null) {
                PokemonCard pokemonCard = (PokemonCard) player.getActivePokemon();
                if (pokemonCard.getDamage() >= pokemonCard.getHp()) {
                    player.setActivePokemon(null);
                    onCardDead(player);
                }
            }
            player.getBench().forEach((card -> {
                if (card instanceof PokemonCard) {
                    PokemonCard pokemonCard = (PokemonCard) card;
                    if (pokemonCard.getDamage() >= pokemonCard.getHp()) {
                        player.getBench().remove(card);
                        onCardDead(player);
                    }
                }
            }));
            player.getHand().forEach((card -> {
                if (card instanceof PokemonCard) {
                    PokemonCard pokemonCard = (PokemonCard) card;
                    if (pokemonCard.getDamage() >= pokemonCard.getHp()) {
                        player.getHand().remove(card);
                        onCardDead(player);
                    }
                }
            }));
        }
    }

    private void onCardDead(Player owner) {
        getOtherPlayer(owner).choseRewardCard();
    }

    public void onEndTurnButtonClicked() {
        checkWinLoose();
        nextTurn();

        //TODO process AI turn

        //finish AI turn
        nextTurn();
    }

    private void nextTurn() {
        //This will cycle between 0 and 1
        currentTurn = (currentTurn + 1) % 2;

        Player currentPlayer = getCurrentTurnPlayer();

        //add card to players hand
        currentPlayer.putCardInHand();

        if (currentTurn == 1)
            aiTurn();

    }

    private void aiTurn() {

        int cardTOAddToBench = rand.nextInt(5);
        if (players[1].activePokemon == null)
            players[1].chooseActivePokemon();
        for (int i = 0; i < (5 - cardTOAddToBench); i++) {
            players[1].putCardOnBench();
        }
        //players[1].putCardOnBench();
        //players[1].activePokemon  this is suppose to attack
        nextTurn();
    }

    private void checkWinLoose() {

        List<Card> pCards = new ArrayList<>();
        if (getCurrentTurnPlayer().prizes.size() == 0 || players[((currentTurn + 1) % 2)].deck
                .size() == 0) {
            for (Card c : players[((currentTurn + 1) % 2)].getBench())
                if (c instanceof PokemonCard)//  .getType().equals("POKEMON"))
                    pCards.add(c);
            if (pCards.size() == 0) {
                win();
            }


        }
    }

    private void win() {
        System.out.println("player " + currentTurn + "has won the game");
        System.exit(0);
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

    private Player getCurrentTurnPlayer() {
        return players[currentTurn];
    }

    public Player getOppositeTurnPlayer() {
        return players[(currentTurn + 1) % 2];
    }

    public Player getOtherPlayer(Player player) {
        if (player == players[0]) {
            return players[1];
        }

        return players[0];
    }
}
