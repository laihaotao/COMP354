package card.abilities;

import parser.abilities.AbilityTemplate;

/**
 * This class contains logic for abilities
 */
public class Ability {
  
  private AbilityTemplate template;
  private int[] cost;
  
  public Ability(AbilityTemplate template, int[] cost) {
        this.template = template;
        this.cost = cost;
  }
  
  public AbilityTemplate getTemplate(){
    return template;
  }
  
  public int[] getCost(){
    return cost;
  }
  
}
