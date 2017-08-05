package parser.tokenizer;

import game.GameBoard;
import game.Player;

/**
 * Used to delimit arithmetics
 */
public class TokenArithmetic extends Token{
  
  public final OperatorType type;
  
  public Token leftValue, rightValue;
  
  public TokenArithmetic(int endLocation, OperatorType type, Token leftValue, Token rightValue) {
    super(TokenType.OPERATOR, endLocation);
    this.type = type;
    this.leftValue = leftValue;
    this.rightValue = rightValue;
  }
  
  public String toString(){
    return super.toString() + "Arithmetic -> "+ leftValue + " " + type + " " + rightValue;
  }

  public int evaluateAsExpression(GameBoard targetBoard, Player callingPlayer){
    switch(type){
      case MULTIPLICATION:
        return leftValue.evaluateAsExpression(targetBoard, callingPlayer) * rightValue.evaluateAsExpression(targetBoard, callingPlayer);
      case DIVISION:
        return leftValue.evaluateAsExpression(targetBoard, callingPlayer) / rightValue.evaluateAsExpression(targetBoard, callingPlayer);
      case ADDITION:
        return leftValue.evaluateAsExpression(targetBoard, callingPlayer) + rightValue.evaluateAsExpression(targetBoard, callingPlayer);
      case SUBTRATION:
        return leftValue.evaluateAsExpression(targetBoard, callingPlayer) - rightValue.evaluateAsExpression(targetBoard, callingPlayer);
      default:
        return 0;
    }
  }
  
  @Override
  public String getDisplayString() {
    String string = leftValue.getDisplayString();
    switch(type){
      case MULTIPLICATION:
        string += "*";
        break;
      default:
        return " ";
    }
    
    return string + rightValue.getDisplayString();
  }


}
