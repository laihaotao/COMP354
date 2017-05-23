package parser.tokenizer;

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
}
