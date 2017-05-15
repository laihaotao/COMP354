package game;

/**
 * Created by frede on 2017-05-15.
 */
public class GameBoard {
  
  private Player[] players;
  
  public GameBoard(Player p1, Player p2) {
      players = new Player[2];
      players[0] = p1;
      players[1] = p2;
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
