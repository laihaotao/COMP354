/*
 * description:
 * author(s):    frede
 * reviewer(s):
 * date:         2017-05-15
 */

package ui.events;

import card.Card;
import card.Ability;
import game.Player;

public interface PlayerViewListener {

    void onHandCardClicked(Player player, Card card);

    void onBenchCardClicked(Player player, Card card);

    void onActiveCardClicked(Player player, Card card);

    void onActiveAbilityClicked(Player player, Card card, Ability ability);

    void onRetreatButtonClicked(Player player);

    void onDiscardPileClicked(Player player);
}
