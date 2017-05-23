package parser.tokenizer;

/**
 * Created by frede on 2017-05-22.
 */
public class TokenString extends Token{
  
  public final String value;
  
  public TokenString(int endLocation, String value){
    super(TokenType.STRING, endLocation);
    this.value = value;
  }
  
  public String toString(){
    return super.toString() + "String -> "+value;
  }

}
