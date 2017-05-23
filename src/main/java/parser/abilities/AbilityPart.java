package parser.abilities;

import java.util.ArrayList;
import java.util.List;
import parser.commons.Property;

/**
 * Created by frede on 2017-05-23.
 */
public class AbilityPart {
    public String name;
    
    public List<AbilityPart> subParts = new ArrayList<>();
    public List<Property> properties = new ArrayList<>();
    
    public AbilityPart(String name){
      this.name = name;
    }
    public String toString(){
      return name;
    }
}
