package parser.abilities.parts;

import game.GameBoard;
import game.Player;
import parser.abilities.Property.TargetProperty;
import parser.abilities.Property.TokenProperty;
import parser.tokenizer.Token;

/**
 * Draw an amount of cards for a player
 */
public class AbilityPartDraw extends AbilityPart{

  private TargetProperty target;
  private Token amount;
  
  public AbilityPartDraw(TargetProperty target, Token amount) {
    super("Draw");
    this.target = target;
    this.amount = amount;
    
    properties.add(target);
    properties.add(new TokenProperty("Amount", amount));
  }

  @Override
  public boolean use(GameBoard targetBoard, Player owner) {
    //TODO implement
      return false;
  }

  @Override
  public String getDescriptionString() {
    return "Draw a card on "+target;
  }
}
