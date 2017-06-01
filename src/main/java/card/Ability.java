package card;

import parser.abilities.AbilityTemplate;
import parser.cards.EnergyCost;

/**
 * This class contains logic for abilities
 */
public class Ability {
  
  private AbilityTemplate template;
  private EnergyCost energyCost;
  
  public Ability(AbilityTemplate template, EnergyCost energyCost) {
        this.template = template;
        this.energyCost = energyCost;
  }
  
  public AbilityTemplate getTemplate(){
    return template;
  }
  
  public EnergyCost getEnergyCost(){
    return this.energyCost;
  }
  
}
