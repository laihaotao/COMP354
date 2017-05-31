package parser.abilities;

import card.Card;
import card.pokemon.PokemonCard;
import game.GameBoard;
import game.Player;
import parser.commons.TargetProperty;
import parser.commons.TokenProperty;
import parser.tokenizer.Token;
import parser.tokenizer.TokenInteger;
import ui.selections.TargetSelector;

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
    Card targetToDamage;    
    switch(target.target.value){
      case "choice":{
          Card targetCard = TargetSelector.getTarget(targetBoard, owner, target);
          if(targetCard instanceof PokemonCard){
            PokemonCard pokemonCard = (PokemonCard)targetCard;
            pokemonCard.setDamage(pokemonCard.getDamage()+ammount.evaluateAsExpression());
          }
      }
      
      case "opponent-active":{
        targetToDamage = targetBoard.getOtherPlayer(owner).getActivePokemon();
        if(targetToDamage != null && targetToDamage instanceof PokemonCard){
          PokemonCard pokemonCard = (PokemonCard)targetToDamage;
          pokemonCard.setDamage(pokemonCard.getDamage()+ammount.evaluateAsExpression());
        }
      }break;
    }
    
    //damage target card
        
  }
}
