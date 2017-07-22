package parser.tokenizer;

import game.GameBoard;
import game.Player;

/**
 * Token that holds an integer 
 */
public class TokenInteger extends Token{
  public final int value;
  
  public TokenInteger(int endLocation, int value) {
    super(TokenType.INTEGER, endLocation);
    this.value = value;
  }
  
  public String toString(){
    return super.toString()+"Integer -> "+value;
  }
  
  public int evaluateAsExpression(GameBoard targetBoard, Player callingPlayer){
      return value;
  }

  @Override
  public String getDisplayString() {
    return String.valueOf(value);
  }
}
