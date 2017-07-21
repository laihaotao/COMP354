package parser.commons;

import parser.tokenizer.TokenStream;
import parser.tokenizer.TokenString;

/**
 * Created by frede on 2017-05-23.
 */
public class TargetProperty extends Property {

  public final TokenString target, modifier;

  public TargetProperty(TokenString target, TokenString modifier) {
    super("Target");
    this.target = target;
    this.modifier = modifier;
  }

  public static TargetProperty read(TokenStream tokenStream){
    TokenString target = null;
    TokenString targetModifier = null;

    if(tokenStream.validateTokenString("target") != null || tokenStream.validateTokenString("source") != null || tokenStream.validateTokenString("destination") != null){
      if((target = tokenStream.validateTokenString("choice")) != null){

        targetModifier = tokenStream.validateTokenString();

      }else{
        target = tokenStream.validateTokenString();
      }
    }

    return new TargetProperty(target, targetModifier);
  }
  
  public String toString(){
      return "Target: "+Formatting.toSafeString(target) + ":" + Formatting.toSafeString(modifier);
  }
  
}
