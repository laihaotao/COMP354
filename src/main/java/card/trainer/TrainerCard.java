/*
 * description:  The abstract class of trainer card
 * author(s):    Eric(Haotao) Lai
 * reviewer(s):
 * date:         2017-05-17
 */

package card.trainer;

import card.Card;
import card.abilities.Ability;
import parser.abilities.AbilityPart;

public class TrainerCard extends Card {

    private enum TrainerType {ITEM, SUPPORTER, STADIUM,}
    private Ability ability;
    private TrainerType trainerType;

    public TrainerCard(String name, String type, Ability ability) {
        this.name = name;
        this.cardType = CardType.TRAINER;
        this.trainerType = Enum.valueOf(TrainerType.class, type);
        this.ability = ability;
    }

}
