/*
 * description:  The abstract class of trainer card
 * author(s):    Eric(Haotao) Lai
 * reviewer(s):
 * date:         2017-05-17
 */

package card.trainer;

import card.Card;

public class TrainerCard extends Card {

    private enum TrainerType {ITEM, SUPPORTER, STADIUM,}

    private TrainerType trainerType;

    public TrainerCard(String name, TrainerType type) {
        this.name = name;
        this.cardType = CardType.TRAINER;
        this.trainerType = type;
    }
}
