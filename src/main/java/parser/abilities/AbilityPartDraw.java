package parser.abilities;

import game.GameBoard;
import game.Player;
import parser.commons.TargetProperty;
import parser.commons.TokenProperty;
import parser.tokenizer.Token;

/**
 * Created by frede on 2017-05-23.
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
  public void onUse(GameBoard targetBoard, Player owner) {
    
  }
}
