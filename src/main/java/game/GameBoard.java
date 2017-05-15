package game;

import card.Card;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ui.BoardView;

/**
 * Created by frede on 2017-05-15.
 */
public class GameBoard {
  
  final static Logger logger = LogManager.getLogger(GameBoard.class.getName());
  
  private Player[] players;
  
  public GameBoard(Player p1, Player p2) {
      players = new Player[2];
      players[0] = p1;
      players[1] = p2;
  }
  
  public void onHandCardClicked(Player player, Card card){
    int playerNum = (player == players[0])?1:2;
    logger.debug("Player"+playerNum+" has clicked a card in it's hand");
    
      //TODO add game logic here for when a hand card is clicked
  }

  public void onBenchCardClicked(Player player, Card card){
    int playerNum = (player == players[0])?1:2;
    logger.debug("Player"+playerNum+" has clicked a card in it's bench");

    //TODO add game logic here for when a hand card is clicked
  }

  public void onActiveCardClicked(Player player, Card card){
    int playerNum = (player == players[0])?1:2;
    logger.debug("Player"+playerNum+" has clicked the active pokemon");

    //TODO add game logic here for when a hand card is clicked
  }
  
  public Player[] getPlayers(){
    return players;
  }
  
  public Player getPlayer1(){
    return players[0];
  }
  
  public Player getPlayer2(){
    return players[1];
  }
}
