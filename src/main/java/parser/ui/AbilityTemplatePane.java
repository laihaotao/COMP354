package parser.ui;

import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import parser.abilities.AbilityPart;
import parser.abilities.AbilityPartAdd;
import parser.abilities.AbilityPartCond;
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
        if(part == null){
            return;
        }
        TreeItem item = new TreeItem<>(part);
        ti.getChildren().add(item);

        if(part instanceof AbilityPartCond){
            AbilityPartCond condPart = (AbilityPartCond)part; 
            TreeItem conditionItem = new TreeItem(condPart.condition);
            TreeItem trueItem = new TreeItem<>("true");
            condPart.trueParts.forEach(tpart->{
                processAbilityPart(trueItem, tpart);
            });
            TreeItem falseItem = new TreeItem<>("false");
            condPart.falseParts.forEach(fpart->{
                processAbilityPart(falseItem, fpart);
            });
            item.getChildren().addAll(conditionItem, trueItem, falseItem);
        }else if(part instanceof AbilityPartAdd){
            AbilityPartAdd addPart = (AbilityPartAdd)part;
            processAbilityPart(item, addPart.abilityToAdd);
        }
        
        //process sub parts
      
        //process properties
        for(Property p:part.properties){
            processProperties(item, p);
        }
    }
    
    private void processProperties(TreeItem ti, Property property){
        TreeItem item = new TreeItem(property.toString());
        ti.getChildren().add(item);
        
        
    }
}
