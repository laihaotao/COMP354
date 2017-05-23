package parser.abilities;

import parser.commons.TargetProperty;
import parser.commons.TokenProperty;
import parser.tokenizer.Token;

/**
 * Created by frede on 2017-05-23.
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
}
