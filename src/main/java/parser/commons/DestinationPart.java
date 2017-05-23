package parser.commons;

import parser.tokenizer.Token;
import parser.tokenizer.TokenStream;
import parser.tokenizer.TokenString;

/**
 * Created by frede on 2017-05-23.
 */
public class DestinationPart extends Part{
  
  public final TokenString destination;
  public final Token modifier;
  
  public DestinationPart(TokenString destination, Token modifier){
      this.destination = destination;
      this.modifier = modifier;
  }

  public static DestinationPart read(TokenStream tokenStream){
    TokenString destination = null;
    Token destinationModifier = null;
    if(tokenStream.validateTokenString("destination") != null){
      destination = tokenStream.validateTokenString();
      destinationModifier = tokenStream.getNextToken();
    }
    
    return new DestinationPart(destination, destinationModifier);
  }

  public String toString(){
    return "Destination: "+Formatting.toSafeString(destination) + " " + modifier;
  }
  

}
