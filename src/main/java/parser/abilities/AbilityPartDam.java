package parser.abilities;

import card.Card;
import game.GameBoard;
import game.Player;
import parser.commons.TargetProperty;
import parser.commons.TokenProperty;
import parser.tokenizer.Token;
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
      case "opponent-active":{
        targetToDamage = targetBoard.getOtherPlayer(owner).getActivePokemon();
      }break;
    }
    
    //damage target card
        
  }
}
