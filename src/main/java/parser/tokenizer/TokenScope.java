package parser.tokenizer;

import java.util.ArrayList;
import java.util.List;

/**
 * This token symbolizes a scope made of multiple other tokens
 */
public class TokenScope extends Token {

  public List<Token> tokens;
  public String prefix;
  
  public TokenScope(int endLocation) {
    super(TokenType.SCOPE, endLocation);
    
    tokens = new ArrayList<>();
  }
  
  public String toString(){
    String s = super.toString()+"Scope -> "+prefix + "( ";
    for(Token t : tokens){
      s += t.toString() + ",  ";
    }
    s += " )";
    return s;
  }
}
