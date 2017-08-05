package game;

import card.Card;

import java.util.List;
import java.util.Random;

/**
 * Author:  Eric(Haotao) Lai
 * Date:    2017-07-25
 * E-mail:  haotao.lai@gmail.com
 * Website: http://laihaotao.me
 */


public class SpecialAbility {

    public static void shuffleDeck(List<Card> deck) {
        int deckSize = deck.size();
        for (int x = 0; x < deckSize; x++) {
            int y = getRandCard(x, deckSize);
            swapCardPos(deck.get(x), deck.get(y));
        }
    }

    private static int getRandCard(int except, int size) {
        Random generator = new Random();
        int res = generator.nextInt(size);
        while (res == except) {
            res = generator.nextInt(size);
        }
        return res;
    }

    public static void swapCardPos(Card x, Card y) {
        Card tmp = x;
        x = y;
        y = tmp;
    }
}
