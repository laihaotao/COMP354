package parser.abilities;

import parser.commons.DestinationProperty;
import parser.commons.TargetProperty;
import parser.commons.TokenProperty;
import parser.tokenizer.Token;
import parser.tokenizer.TokenString;

/**
 * Created by frede on 2017-05-23.
 */
public class AbilityPartDeck extends AbilityPart{
  
  private TargetProperty target;
  private DestinationProperty destination;
  private TokenString choice;
  private Token amount;
  
  public AbilityPartDeck(TargetProperty target, DestinationProperty destination,
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
}
