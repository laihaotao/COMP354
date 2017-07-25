package parser.abilities.parts;

import card.Card;
import card.PokemonCard;
import game.GameBoard;
import game.Player;
import parser.abilities.Property.TargetProperty;
import parser.abilities.Property.TokenProperty;
import parser.tokenizer.Token;

/**
 * Used to apply damage on a target
 */
public class AbilityPartDam extends AbilityPart{
  
  private TargetProperty target;
  private Token ammount;
  
  public AbilityPartDam(TargetProperty target, Token ammount) {
    super("Dam");
    this.target = target;
    this.ammount = ammount;
    
    properties.add(target);
    properties.add(new TokenProperty("Ammount", ammount));
  }

  @Override
  public boolean use(GameBoard targetBoard, Player owner) {
    Card targetCard = owner.getTarget(targetBoard, target);
    if(targetCard != null) {
      if (targetCard instanceof PokemonCard) {
        PokemonCard pokemonCard = (PokemonCard) targetCard;
        int intAmmount = ammount.evaluateAsExpression(targetBoard, owner);
        if(intAmmount > 0) {
          targetBoard.applyDamageToCard(pokemonCard, intAmmount);
          return true;
        }
        
      }
    }
    //damage target card
        
    return false;
  }

  @Override
  public String getDescriptionString() {
    return "Damages "+ target + " for "+ammount.getDisplayString();
  }

  public String getCurrentDescription(GameBoard targetBoard, Player callingPlayer){
    return "Damages " + target + "for " + ammount.evaluateAsExpression(targetBoard, callingPlayer) + " ["+ ammount.getDisplayString() +"]";
  }
  
  public Token getAmmount(){
    return ammount;
  }
}
