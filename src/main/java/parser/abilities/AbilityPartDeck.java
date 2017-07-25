package parser.abilities;

import game.GameBoard;
import game.Player;
import parser.abilities.Property.TargetProperty;
import parser.abilities.Property.TokenProperty;
import parser.tokenizer.Token;
import parser.tokenizer.TokenString;

/**
 * 
 */
public class AbilityPartDeck extends AbilityPart{
  
  private TargetProperty target;
  private TargetProperty destination;
  private TokenString choice;
  private Token amount;
  
  public AbilityPartDeck(TargetProperty target, TargetProperty destination,
      TokenString choice, Token amount) {
    super("Deck");
    this.target = target;
    this.destination = destination;
    this.choice = choice;
    this.amount = amount;

    properties.add(target);
    properties.add(destination);
    properties.add(new TokenProperty("Choice", choice));
    properties.add(new TokenProperty("Amount", amount));
  }

  @Override
  public boolean use(GameBoard targetBoard, Player owner) {
    //TODO implement
      return false;
  }

  @Override
  public String getDescriptionString() {
    return "Deck from "+target + " to " + destination + " with choice "+choice + " for "+amount ; //TODO add description
  }

  public String getCurrentDescription(GameBoard targetBoard, Player callingPlayer){
    return "Deck from "+target + " to " + destination + " with choice "+choice + " for "+amount.evaluateAsExpression(targetBoard, callingPlayer) + " ["+amount.getDisplayString()+"]" ;
  }
}
