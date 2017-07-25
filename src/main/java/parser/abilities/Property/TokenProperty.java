package parser.abilities.Property;

import parser.tokenizer.Token;

/**
 * Created by frede on 2017-05-23.
 */
public class TokenProperty extends Property {
  
  private Token token;
  
  public TokenProperty(String name, Token token) {
    super(name);
    this.token = token;
  }
  
  public String toString(){
    return name+": "+token;
  }
}
