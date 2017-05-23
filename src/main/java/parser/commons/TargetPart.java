package parser.commons;

import parser.tokenizer.TokenStream;
import parser.tokenizer.TokenString;

/**
 * Created by frede on 2017-05-23.
 */
public class TargetPart extends Part{

  public final TokenString target, modifier;

  public TargetPart(TokenString target, TokenString modifier) {
    this.target = target;
    this.modifier = modifier;
  }

  public static TargetPart read(TokenStream tokenStream){
    TokenString target = null;
    TokenString targetModifier = null;

    if(tokenStream.validateTokenString("target") != null){
      if((target = tokenStream.validateTokenString("choice")) != null){

        targetModifier = tokenStream.validateTokenString();

      }else{
        target = tokenStream.validateTokenString();
      }
    }

    return new TargetPart(target, targetModifier);
  }
  
  public String toString(){
      return "Target: "+Formatting.toSafeString(target) + " " + Formatting.toSafeString(modifier);
  }
  
}
