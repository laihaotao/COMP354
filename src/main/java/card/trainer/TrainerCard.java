package card.trainer;

import card.Card;

/**
 * Created by ERIC_LAI on 2017-05-06.
 */
public class TrainerCard extends Card {

    private enum TrainerType {}

    private TrainerType trainerType;

    public TrainerCard(String name, TrainerType type) {
        this.name = name;
        this.cardType = CardType.TRAINER;
        this.trainerType = type;
    }
}
