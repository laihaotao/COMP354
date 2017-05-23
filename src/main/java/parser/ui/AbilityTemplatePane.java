package parser.ui;

import javafx.scene.control.TreeItem;
import parser.abilities.AbilityPart;
import parser.abilities.AbilityTemplate;
import parser.commons.Property;

/**
 * Created by frede on 2017-05-23.
 */
public class AbilityTemplatePane extends TreeItem {
    private AbilityTemplate[] abilityTemplates;
  
    public AbilityTemplatePane(AbilityTemplate[] abilityTemplates){
      super("Abilities");
      this.abilityTemplates = abilityTemplates;
      setExpanded(true);
      
      for(AbilityTemplate abilityTemplate : abilityTemplates){
        TreeItem rootItem = new TreeItem<>(abilityTemplate.name);
        this.getChildren().add(rootItem);
        
        for(AbilityPart part : abilityTemplate.parts){
            processAbilityPart(rootItem, part);
        }
      }
    }
    
    private void processAbilityPart(TreeItem ti, AbilityPart part){
        TreeItem item = new TreeItem<>(part);
        ti.getChildren().add(item);
        
        //process sub parts
      
        //process properties
        for(Property p:part.properties){
            processProperties(item, p);
        }
    }
    
    private void processProperties(TreeItem ti, Property property){
        TreeItem item = new TreeItem(property);
        ti.getChildren().add(item);
        
        
    }
}
