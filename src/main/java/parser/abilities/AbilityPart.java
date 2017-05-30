package parser.abilities;

import game.GameBoard;
import game.Player;
import java.util.ArrayList;
import java.util.List;
import parser.commons.Property;

/**
 * Created by frede on 2017-05-23.
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
    
    public abstract void use(GameBoard targetBoard, Player owner);
}
