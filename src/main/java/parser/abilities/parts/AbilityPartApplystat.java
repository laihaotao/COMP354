package parser.abilities.parts;

import card.Card;
import card.PokemonCard;
import game.GameBoard;
import game.Player;
import parser.abilities.properties.StatusProperty;
import parser.abilities.properties.TargetProperty;

public class AbilityPartApplystat extends AbilityPart{

    private StatusProperty statusType;
    private TargetProperty target;

    public AbilityPartApplystat(StatusProperty statusType, TargetProperty target) {
        super("ApplyStat");
        this.statusType = statusType;
        this.target = target;
        
        properties.add(statusType);
        properties.add(target);
    }

    @Override
    public boolean use(GameBoard targetBoard, Player owner) {
        Card targetCard = owner.getTarget(targetBoard, target);
        if(targetCard instanceof PokemonCard){
            PokemonCard pokemonCard = (PokemonCard)targetCard;
            pokemonCard.setStatus(statusType.type.value);
            return true;
        }
        return false;
    }

    @Override
    public String getDescriptionString() {
        return "Apply stat "+statusType+" on " + target;
    }
}
