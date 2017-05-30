package game;

import card.Card;
import card.pokemon.PokemonCard;
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
  Random rand = new Random();
  public GameBoard(Player p1, Player p2) {
      players = new Player[2];
      players[0] = p1;
      players[1] = p2;
  }

  public void setSelectedCard(Card card, CardLocation location){
    if(selectedCard != null) {
      selectedCard.setSelected(false);
    }

    selectedCard = card;
    if(selectedCard != null){
      selectedCard.setSelected(true);
    }
    selectedCardLocation = location;
  }

  public void onHandCardClicked(Player player, Card card){
    int playerNum = (player == players[0])?1:2;
    logger.debug("Player"+playerNum+" has clicked a card in it's hand");

    if(card != null && (playerNum-1) == currentTurn) {
      setSelectedCard(card, CardLocation.HAND);
    }

  }

  public void onBenchCardClicked(Player player, Card card){
    int playerNum = (player == players[0])?1:2;
    logger.debug("Player"+playerNum+" has clicked a card in it's bench");

    //Player is trying to place pokemon card on bench
    if(selectedCard != null && selectedCardLocation == CardLocation.HAND && selectedCard instanceof PokemonCard){

      //remove selected card from player's hand and put it on the player's bench
      if(players[0].getHand().remove(selectedCard)){
        players[0].getBench().add(selectedCard);
        setSelectedCard(null, null);
      }

      return;
    }

  }

  public void onActiveCardClicked(Player player, Card card){
    int playerNum = (player == players[0])?1:2;
    logger.debug("Player"+playerNum+" has clicked the active pokemon");

    //player is trying to place pokemon on active slot
    if(players[0].getActivePokemon() == null && selectedCard != null && selectedCardLocation== CardLocation.HAND && selectedCard instanceof PokemonCard){

      //remove selected card from player's hand and put it as active
      if(players[0].getHand().remove(selectedCard)) {
        players[0].setActivePokemon(selectedCard);
        setSelectedCard(null, null);
      }

      return;
    }
  }

  public void onEndTurnButtonClicked(){
    checkWinLoose();
    nextTurn();

    //TODO process AI turn

    //finish AI turn
  //  nextTurn();
  }

  public void nextTurn(){
    //This will cycle between 0 and 1
    currentTurn = (currentTurn + 1)%2;

    Player currentPlayer = getCurrentTurnPlayer();

    //add card to players hand
    currentPlayer.putCardInHand();

    if(currentTurn == 1)
        aiTurn();

  }
  public void aiTurn(){

    int cardTOAddToBench = rand.nextInt(5);
      if(players[1].activePokemon == null)
          players[1].chooseActivePokemon();
      for(int i = 0 ; i<(5- cardTOAddToBench); i++)
      {
          players[1].putCardOnBench();
      }
      //players[1].putCardOnBench();
      //players[1].activePokemon  this is suppose to attack
      nextTurn();
  }
  public void checkWinLoose(){

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

  public Player getCurrentTurnPlayer(){
    return players[currentTurn];
  }

  public Player getOppositeTurnPlayer(){
    return players[(currentTurn+1)%2];
  }
}
