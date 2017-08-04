package parser.abilities.parts;

import card.Card;
import card.PokemonCard;
import game.GameBoard;
import game.Player;
import parser.abilities.properties.TargetProperty;
import parser.abilities.properties.TriggerProperty;

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
    public boolean use(GameBoard targetBoard, Player owner, Card callingCard) {
        
        Card card = owner.getTarget(targetBoard, callingCard, target);
        
        if(card instanceof PokemonCard) {
            final PokemonCard pokemonCard = (PokemonCard)card;
            switch(trigger.target.modifier.value) {
                case "turn-end": {
                    Player player = null;
                    switch(trigger.target.target.value){
                        case "opponent":{
                            player = targetBoard.getOtherPlayer(owner);
                        }break;
                        case "your": {
                            player = owner;
                        }break;
                    }
                    if(player != null){
                        player.addEndTurnEvent((p)->{
                            pokemonCard.getAbilities().forEach(ability -> {
                                ability.getTemplate().parts.add(abilityToAdd);
                            });
                        });
                        return true;
                    }
                    
                }break;
            }
        }
        
        return false;
    }

    @Override
    public String getDescriptionString() {
        return "Add ability "+abilityToAdd.name + " to "+target;
    }
}
