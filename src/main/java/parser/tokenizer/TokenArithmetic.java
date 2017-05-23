package parser.tokenizer;

/**
 * Used to delimit arithmetics
 */
public class TokenArithmetic extends Token{
  
  public final ArithmeticType type;
  
  public Token leftValue, rightValue;
  
  public TokenArithmetic(int endLocation, ArithmeticType type, Token leftValue, Token rightValue) {
    super(TokenType.ARITHMETIC, endLocation);
    this.type = type;
    this.leftValue = leftValue;
    this.rightValue = rightValue;
  }
  
  public String toString(){
    return super.toString() + "Arithmetic -> "+ leftValue + " " + type + " " + rightValue;
  }
  
  
}
