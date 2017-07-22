package parser.commons;

import parser.tokenizer.Token;
import parser.tokenizer.TokenStream;
import parser.tokenizer.TokenString;

/**
 * Created by frede on 2017-05-23.
 */
public class TargetProperty extends Property {

  public TokenString target, modifier;
  public Token ammountModifier;

  public TargetProperty(TokenString target, TokenString modifier) {
    super("Target");
    this.target = target;
    this.modifier = modifier;
    ammountModifier = null;
  }
  public TargetProperty(TokenString target, TokenString modifier, Token ammountModifier) {
    super("Target");
    this.target = target;
    this.modifier = modifier;
    this.ammountModifier = ammountModifier;
  }
  

  public static TargetProperty read(TokenStream tokenStream){
    TokenString target = null;
    TokenString targetModifier = null;
    Token amountModifier = null;
    if(tokenStream.validateTokenString("target") != null || tokenStream.validateTokenString("source") != null || tokenStream.validateTokenString("destination") != null){
      if((target = tokenStream.validateTokenString("choice")) != null){

        targetModifier = tokenStream.validateTokenString();

      }else if((target = tokenStream.validateTokenString("deck")) != null) {
        if((targetModifier = tokenStream.validateTokenString()) == null){
            amountModifier = tokenStream.getNextToken();
        }
      }else{
        target = tokenStream.validateTokenString();
      }
    }

    return new TargetProperty(target, targetModifier, amountModifier);
  }
  
  public static TargetProperty readUnsafe(TokenStream tokenStream){
    TokenString target = null;
    TokenString targetModifier = null;
    if((target = tokenStream.validateTokenString("choice")) != null){

      targetModifier = tokenStream.validateTokenString();

    }else{
      target = tokenStream.validateTokenString();
    }

    return new TargetProperty(target, targetModifier);
  }
  
  public String toString(){
      return "Target: "+Formatting.toSafeString(target) + ":" + Formatting.toSafeString(modifier) + ammountModifier==null?ammountModifier.getDisplayString():"";
  }
  
}
