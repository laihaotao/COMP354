package card.energy;

import card.Card;

/**
 * Created by ERIC_LAI on 2017-05-06.
 */
public class EnergyCard extends Card {

    private enum EnergyType {}

    private EnergyType energyType;

    public EnergyCard(EnergyType type) {
        this.energyType = type;
        this.cardType = CardType.ENERGY;
    }
}
