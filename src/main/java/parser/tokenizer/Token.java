package parser.tokenizer;

/**
 * This symbolizes a token in the language
 */
public abstract class Token {
  
  public final int endLocation;
  public final TokenType type;
  
  public Token(TokenType type, int endLocation){
    this.type = type;
    this.endLocation = endLocation;
  }
  
  public String toString(){
    return "@"+endLocation + " token:";
  }

  public int evaluateAsExpression(){
    return 0;
  }
}
