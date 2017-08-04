package parser.abilities.properties;

import parser.abilities.filters.Filter;
import parser.abilities.filters.FilterPokemon;
import parser.commons.Formatting;
import parser.tokenizer.Token;
import parser.tokenizer.TokenStream;
import parser.tokenizer.TokenString;

/**
 * Created by frede on 2017-05-23.
 */
public class TargetProperty extends Property {

  public TokenString target, modifier;
  public Token ammountModifier;
  public Filter filter;

  public TargetProperty(TokenString target, TokenString modifier, Filter filter) {
    super("Target");
    this.target = target;
    this.modifier = modifier;
    ammountModifier = null;
    if(filter == null){
      filter = new Filter();
    }
    this.filter = filter;
  }
  public TargetProperty(TokenString target, TokenString modifier, Token ammountModifier, Filter filter) {
    super("Target");
    this.target = target;
    this.modifier = modifier;
    this.ammountModifier = ammountModifier;
    if(filter == null){
      filter = new Filter();
    }
    this.filter = filter;
  }
  

  public static TargetProperty read(TokenStream tokenStream){

    if(tokenStream.validateTokenString("target") != null || tokenStream.validateTokenString("source") != null || tokenStream.validateTokenString("destination") != null){
        return readUnsafe(tokenStream);
    }

    return null;
  }
  
  public static TargetProperty readUnsafe(TokenStream tokenStream){
    Filter filter = new Filter();
    TokenString target = null;
    TokenString targetModifier = null;
    Token amountModifier = null;
    
    if((target = tokenStream.validateTokenString("choice")) != null){

      targetModifier = tokenStream.validateTokenString();

      if(targetModifier != null){
        switch(targetModifier.value){
          case "your-pokemon":
          case "opponent-pokemon":
            targetModifier = new TokenString(targetModifier.endLocation, targetModifier.value.split("-")[0]);
            if(tokenStream.validateTokenString("cat") != null) {
              FilterPokemon pokemonFilter = new FilterPokemon();
              pokemonFilter.setCategory(tokenStream.validateTokenString().value);
              filter = pokemonFilter;
            }

        }
      }

      
    }else if ((target = tokenStream.validateTokenString("deck")) != null) {
        if(tokenStream.validateTokenString("top") != null){
            targetModifier = new TokenString(0, "top");
        }else if(tokenStream.validateTokenString("bottom")!= null){
          targetModifier = new TokenString(0, "bottom");
        }
    }else
    {
      target = tokenStream.validateTokenString();
    }
    return new TargetProperty(target, targetModifier, amountModifier, filter);
  }
  
  public String toString(){
      return "Target: "+ Formatting.toSafeString(target) + ":" + Formatting.toSafeString(modifier) + ((ammountModifier!=null)?ammountModifier.getDisplayString():"" + " and "+filter.toString());
  }
  
}
