package parser.tokenizer;

/**
 * Created by frede on 2017-05-22.
 */
public class TokenSeparator extends Token{

  public TokenSeparator(int endLocation) {
    super(TokenType.SEPERATOR, endLocation);
  }
  
  public String toString(){
      return super.toString() + "Separator -> --------------";
  }
}
