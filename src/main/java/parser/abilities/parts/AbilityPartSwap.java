package parser.abilities.parts;

import card.Card;
import card.PokemonCard;
import game.GameBoard;
import game.Player;
import game.SpecialAbility;
import parser.abilities.properties.TargetProperty;

public class AbilityPartSwap extends AbilityPart{

    private final TargetProperty source;
    private final TargetProperty destination;

    public AbilityPartSwap(TargetProperty source, TargetProperty destination) {
        super("swap");
        this.source = source;
        this.destination = destination;
        
        properties.add(source);
        properties.add(destination);
    }

    @Override
    public boolean use(GameBoard targetBoard, Player owner) {
        PokemonCard p1 = (PokemonCard) owner.getTarget(targetBoard, source);
        PokemonCard p2 = (PokemonCard) owner.getTarget(targetBoard, destination);
        // remove all status effects
        p1.setEffect(p1.getEffect().remove());
        SpecialAbility.swapCardPos(p1, p2);
        return true;
    }

    @Override
    public String getDescriptionString() {
        return "Swap from " + source + " to " +destination;
    }
}
