package game.events;

import game.Player;

public interface PlayerEndTurnEvent {
    
    void onEndTurn(Player p);

}
