package parser.abilities.parts;

import game.GameBoard;
import game.Player;
import parser.abilities.Property.TargetProperty;

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
        return false;
    }

    @Override
    public String getDescriptionString() {
        return "Swap from " + source + " to " +destination;
    }
}
