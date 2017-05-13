/*
 * description:  The abstract class of energy card
 * author(s):    Eric(Haotao) Lai
 * reviewer(s):
 * date:         2017-05-17
 */

package card.energy;

import card.Card;

public class EnergyCard extends Card {

    private enum EnergyType {COLORLESS, WATER, LIGTNING,}

    private EnergyType energyType;

    public EnergyCard(EnergyType type) {
        this.energyType = type;
        this.cardType = CardType.ENERGY;
    }
}
