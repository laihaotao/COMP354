/*
 * description:  The abstract class of energy card
 * author(s):    Eric(Haotao) Lai
 * reviewer(s):
 * date:         2017-05-17
 */

package card.energy;

import card.Card;


public class EnergyCard extends Card {
	 // Colorless-Fire-Water-Lightning-Psychic-Grass-Darkness-Metal-Fairy-Fight-Dragon
    private enum EnergyType {Colorless, Fire, Water, Lightning, Psychic, Grass, Darkness, Metal, Fairy, Fight, Dragon;}

    private EnergyType energyType;

    public EnergyCard(String type) {
		this.energyType = Enum.valueOf(EnergyType.class, type);
        this.cardType = CardType.ENERGY;
    }

    @Override
    public String getCardName() {
        return this.energyType.toString();
    }
}
