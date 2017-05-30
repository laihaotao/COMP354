package parser.tokenizer;

import java.util.List;

/**
 * Created by frede on 2017-05-23.
 */
public class TokenStream {
  
  private Token[] tokens;

  int currentToken = -1;
  
  public TokenStream(List<Token> tokens){
    this.tokens = tokens.toArray(new Token[0]);
  }
  
  public Token getNextToken(){
    currentToken++;
    if(currentToken < tokens.length){
      return tokens[currentToken];
    }
    
    return null;
  }

  /**
   * Validate that next token is a string with a certain value
   * @param value value to validate
   * @return
   */
  public TokenString validateTokenString(String value){
    Token token = getNextToken();
    
    if(token instanceof TokenString){
      TokenString tokenString = (TokenString)token;
      
      if(tokenString.value.equalsIgnoreCase(value)) {
        return tokenString;
      }
    }
    
    backtrack();
    return null;
  }

  /**
   * Validate that next token is a string
   * @return
   */
  public TokenString validateTokenString(){
      Token token = getNextToken();
      if(token instanceof TokenString){
          return (TokenString)token;
      }
      backtrack();
      return null;
  }
  
  public TokenInteger validateTokenInteger(){
      Token token = getNextToken();
      if(token instanceof TokenInteger){
          return (TokenInteger)token;
      }
      backtrack();
      return null;
  }
  
  public TokenSeparator validateTokenSeparator(){
    Token token = getNextToken();
    if(token instanceof TokenSeparator){
      return (TokenSeparator)token;
    }
    backtrack();
    return null;
  }
  
  public void backtrack(){
      currentToken--;
  }
}
