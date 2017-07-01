package parser.abilities;

import card.Card;
import card.PokemonCard;
import game.GameBoard;
import game.Player;
import parser.commons.TargetProperty;
import parser.commons.TokenProperty;
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
  public void use(GameBoard targetBoard, Player owner) {
    Card targetCard = owner.getTarget(targetBoard, target);
    if(targetCard != null) {
      if (targetCard instanceof PokemonCard) {
        PokemonCard pokemonCard = (PokemonCard) targetCard;
        pokemonCard.setDamage(pokemonCard.getDamage() + ammount.evaluateAsExpression());
      }
    }
    //damage target card
        
  }

  @Override
  public String getDescriptionString() {
    return "Damages "+ target + " for "+ammount.getDisplayString();
  }
}
