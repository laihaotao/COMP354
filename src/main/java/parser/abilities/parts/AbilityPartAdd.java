package parser.abilities.parts;

import game.GameBoard;
import game.Player;
import parser.abilities.Property.TargetProperty;
import parser.abilities.Property.TriggerProperty;

public class AbilityPartAdd extends AbilityPart{


    private final TargetProperty target;
    private TriggerProperty trigger;
    public final AbilityPart abilityToAdd;

    public AbilityPartAdd(TargetProperty target,TriggerProperty trigger, AbilityPart abilityToAdd) {
        super("add");
        this.target = target;
        this.trigger = trigger;
        this.abilityToAdd = abilityToAdd;
        
        properties.add(target);
        properties.add(trigger);
    }

    @Override
    public boolean use(GameBoard targetBoard, Player owner) {
        return false;
    }

    @Override
    public String getDescriptionString() {
        return "Add ability "+abilityToAdd.name + " to "+target;
    }
}
