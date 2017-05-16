package ui.events;

import card.Card;
import game.Player;

/**
 * Created by frede on 2017-05-15.
 */
public interface PlayerViewListener {
  void onHandCardClicked(Player player, Card card);
  void onBenchCardClicked(Player player, Card card);
  void onActiveCardClicked(Player player, Card card);
}
