package parser.commons;

import parser.tokenizer.TokenString;

/**
 * Created by frede on 2017-05-23.
 */
public class Formatting {
  public static String toSafeString(TokenString tokenString){
    if(tokenString == null){
      return "_";
    }
    return tokenString.value;
  }
}
