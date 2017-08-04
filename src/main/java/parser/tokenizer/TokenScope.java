package parser.tokenizer;

import card.Card;
import card.PokemonCard;
import game.GameBoard;
import game.Player;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import parser.cards.EnergyCost;

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

  @Override
  public String getDisplayString() {
    StringBuilder displayBuilder = new StringBuilder();
    displayBuilder.append(prefix+"(");
    tokens.forEach(token->{
      displayBuilder.append(token.getDisplayString()+":");
    });
    displayBuilder.append(")");
    return displayBuilder.toString();
  }

  @Override
  public int evaluateAsExpression(GameBoard targetBoard, Player callingPlayer) {
    if(prefix.equalsIgnoreCase("count")){
      TokenStream tokenStream = new TokenStream(tokens);

      if (tokenStream.validateTokenString("target") != null) {
        Card targetCard = null;
        switch (tokenStream.validateTokenString().value) {
          case "opponent-active":
            targetCard = targetBoard.getOtherPlayer(callingPlayer).getActivePokemon();
            break;
          case "your-active":
            targetCard = callingPlayer.getActivePokemon();
            break;
            
          case "your-bench":
            return callingPlayer.getBench().size();
          case "opponent-bench":
            return targetBoard.getOtherPlayer(callingPlayer).getBench().size();

          case "your-hand":
            return callingPlayer.getHand().size();
          case "opponent-hand":
            return targetBoard.getOtherPlayer(callingPlayer).getHand().size();
          case "last":
            targetCard = callingPlayer.getLastTarget(0);
        }
        
        if(targetCard != null){
          switch(tokenStream.validateTokenString().value){
            case "source": {
              switch(tokenStream.validateTokenString().value){
                case "damage":{
                  return ((PokemonCard)targetCard).getDamage();
                }
              }
            }break;
            case "damage":
              return ((PokemonCard)targetCard).getDamage();
            case "energy":
              PokemonCard pokemonCard = ((PokemonCard)targetCard);
              EnergyCost energyAttached = pokemonCard.getEnergyAttached();
              TokenString specificEnergy;
              if((specificEnergy = tokenStream.validateTokenString())!=null && specificEnergy.value.length()>0){
                switch(specificEnergy.value){
                  case "psychic":
                    return energyAttached.psychic;
                  case "lighting":
                    return energyAttached.lightning;
                  case "fight":
                    return energyAttached.fight;
                  case "water":
                    return energyAttached.water;
                  case "colorless":
                    return energyAttached.colorless;
                }
              }
              return Arrays.stream(((PokemonCard)targetCard).getEnergyAttached().getAsArray()).sum();
          }
        }
      }
    }
    else if(tokens.size() > 0){
      for (Token token : tokens) {
        int value = token.evaluateAsExpression(targetBoard, callingPlayer);
        if (value > 0) {
          return value;
        }
      }
    }
    
    return 0;
  }
}
