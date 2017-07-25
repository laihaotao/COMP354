package parser.abilities.parts;

import game.GameBoard;
import game.Player;
import java.util.ArrayList;
import java.util.List;
import parser.abilities.properties.Property;

/**
 * AbilityPart defines behavior for an an ability
 */
public abstract class AbilityPart {
    public String name;
    
    public List<AbilityPart> subParts = new ArrayList<>();
    public List<Property> properties = new ArrayList<>();
    
    public AbilityPart(String name){
      this.name = name;
    }
    public String toString(){
      return name;
    }

  /**
   * Use the ability on a board
   * @param targetBoard GameBoard to use ability on
   * @param owner The player owner of the ability
   */
  public abstract boolean use(GameBoard targetBoard, Player owner);
  
  public abstract String getDescriptionString();

    public String getCurrentDescription(GameBoard targetBoard, Player callingPlayer){
        return getDescriptionString();
    }
}
