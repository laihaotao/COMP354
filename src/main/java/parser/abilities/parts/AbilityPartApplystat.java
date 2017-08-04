package parser.abilities.parts;

import card.Card;
import card.PokemonCard;
import game.GameBoard;
import game.Player;
import game.effectstatus.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import parser.abilities.properties.StatusProperty;
import parser.abilities.properties.TargetProperty;

public class AbilityPartApplystat extends AbilityPart {

    private final static Logger logger = LogManager.getLogger(AbilityPartApplystat.class.getName());


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
    public boolean use(GameBoard targetBoard, Player owner, Card callingCard) {
        Card targetCard = owner.getTarget(targetBoard,callingCard,  target);
        if (targetCard instanceof PokemonCard) {
            PokemonCard pokemonCard = (PokemonCard) targetCard;
            String effectName = statusType.type.value;
            Effect effect = null;
            switch (effectName) {
                case "asleep":
                    effect = new Asleep(pokemonCard);
                    break;
                case "paralyzed":
                    effect = new Paralyzed(pokemonCard);
                    break;
                case "poisoned":
                    effect = new Poisoned(pokemonCard);
                    break;
                case "stuck":
                    effect = new Stuck(pokemonCard);
                    break;
            }
            if (effect != null) {
                logger.debug(pokemonCard.getCardName() + " get " + effectName + " effect");
                effect.apply();
                pokemonCard.setEffect(effect);
                pokemonCard.setStatus(effectName);
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    @Override
    public String getDescriptionString() {
        return "Apply stat " + statusType + " on " + target;
    }
}
