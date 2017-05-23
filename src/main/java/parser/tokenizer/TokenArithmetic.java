package parser.tokenizer;

/**
 * Used to delimit arithmetics
 */
public class TokenArithmetic extends Token{
  
  public final ArithmeticType type;
  
  public TokenArithmetic(int endLocation, ArithmeticType type) {
    super(TokenType.ARITHMETIC, endLocation);
    this.type = type;
  }
  
  public String toString(){
    return super.toString() + "Arithmetic -> "+ArithmeticType.MULTIPLICATION;
  }
  
  
}
