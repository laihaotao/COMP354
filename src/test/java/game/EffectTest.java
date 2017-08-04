package game;

import card.Ability;
import card.Card;
import card.PokemonCard;
import entry.Config;
import game.ai.IntelligentPlayer;
import game.effectstatus.Effect;
import game.effectstatus.Paralyzed;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import parser.cards.CardParser;
import parser.cards.DeckParser;
import parser.cards.EnergyCost;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static entry.Config.FILE_PATH_CARDS_TXT;
import static entry.Config.FILE_PATH_DECK1_TXT;
import static entry.Config.FILE_PATH_DECK2_TXT;
import static org.junit.Assert.assertEquals;

/**
 * Author:  Eric(Haotao) Lai
 * Date:    2017-08-03
 * E-mail:  haotao.lai@gmail.com
 * Website: http://laihaotao.me
 */


public class EffectTest {

    private final static Logger logger = LogManager.getLogger(EffectTest.class.getName());


    private List<Card> deck1;
    private List<Card> deck2;
    private Player player1;
    private Player player2;
    private GameBoard gameBoard;

    private void buildGame() throws IOException, ClassNotFoundException {
        CardParser cardParser = new CardParser(Config.FILE_PATH_CARDS_TXT);
        DeckParser deck1Parser = new DeckParser(Config.FILE_PATH_DECK1_TXT, cardParser);
        DeckParser deck2Parser = new DeckParser(Config.FILE_PATH_DECK2_TXT, cardParser);

        deck1 = deck1Parser.getDeck();
        deck2 = deck2Parser.getDeck();

        player1 = new Player(deck1);
        player2 = new IntelligentPlayer(deck2);

        player1.setName("player1");
        player2.setName("player2");

        gameBoard = new GameBoard(player1, player2);
    }

    @Before
    public void init() throws IOException, ClassNotFoundException {
        buildGame();
    }

    @Test
    public void paralyzedTest() throws IOException, ClassNotFoundException {
        PokemonCard hitmonlee = (PokemonCard) getSpecificCard("Hitmonlee", player1.getHand());
        PokemonCard pikachu = (PokemonCard) getSpecificCard("Pikachu", player2.getHand());

        // set pikachu hp, just for test purpose
        pikachu.setHp(10000);

        // set them as the active pokemon
        player2.setActivePokemon(pikachu);
        player1.setActivePokemon(hitmonlee);
        EnergyCost energyCost = new EnergyCost(3, 3, 3, 3, 3);

        pikachu.setEnergyAttached(energyCost);
        hitmonlee.setEnergyAttached(energyCost);

        // it is player1's turn
        gameBoard.setCurrentTurn(0);
        logger.info("now is player: " + gameBoard.getCurrentTurnPlayer().getName() + "'s turn");
        // make Hitmonlee to attack Pikachu
        // before attack, the demage of pikachu should be 0
        assertEquals(0, pikachu.getDamage());
        gameBoard.onActiveAbilityClicked(player1, hitmonlee, hitmonlee.getAbility(1));
        // after attack, the demage of pikachu should be 30
        assertEquals(30, pikachu.getDamage());

        paralyzed(3, hitmonlee);
        assertEquals(30, pikachu.getDamage());
    }

    private void paralyzed(int time, PokemonCard target) {
        while ((time--) > 0) {
            gameBoard.onEndTurnButtonClicked();
            // end turn -> now it is player2's turn
            gameBoard.setCurrentTurn(1);
            logger.info("now is player: " + gameBoard.getCurrentTurnPlayer().getName() + "'s turn");
            // set Hitmonlee to be paralyzed
            Effect paralyzed = new Paralyzed(target);
            paralyzed.apply();
            target.setEffect(paralyzed);

            gameBoard.onEndTurnButtonClicked();
            // set turn to player1's turn
            gameBoard.setCurrentTurn(0);
            gameBoard.onActiveAbilityClicked(player1, target, target.getAbility(1));
        }
    }

    private void listAllAbilities(List<Ability> abilities) {
        for (Ability a : abilities) {
            logger.info(a.getTemplate().name);
        }
    }

    private Card getSpecificCard(String name, List<Card> deck) {
        for (Card card : deck) {
            if (card.getCardName().equals(name)) {
                return card;
            }
        }
        return null;
    }
}
