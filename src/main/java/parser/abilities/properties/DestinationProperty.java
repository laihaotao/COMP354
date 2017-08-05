package parser.abilities.properties;

import parser.commons.Formatting;
import parser.tokenizer.Token;
import parser.tokenizer.TokenStream;
import parser.tokenizer.TokenString;

/**
 * Created by frede on 2017-05-23.
 */
public class DestinationProperty extends Property {
  
  public final TokenString destination;
  public final Token modifier;
  
  public DestinationProperty(TokenString destination, Token modifier){
    super("Destination");
      this.destination = destination;
      this.modifier = modifier;
  }

  public static DestinationProperty read(TokenStream tokenStream){
    TokenString destination = null;
    Token destinationModifier = null;
    if(tokenStream.validateTokenString("destination") != null){
      destination = tokenStream.validateTokenString();
      destinationModifier = tokenStream.getNextToken();
    }
    
    return new DestinationProperty(destination, destinationModifier);
  }

  public String toString(){
    return "Destination: "+ Formatting.toSafeString(destination) + " " + modifier;
  }
  

}
