/*
 * description:  The abstract class of energy card
 * author(s):    Eric(Haotao) Lai
 * reviewer(s):
 * date:         2017-05-17
 */

package card.energy;

import card.Card;


public class EnergyCard extends Card {
	 // Colorless-Fire-Water-Lightning-Psychic-Grass-Darkness-Metal-Fairy-Fightning-Dragon
    private enum EnergyType {COLORLESS, FIRE, WATER, LIGTNING, PSYCHIC, GRASS, DARKNESS, METAL, FAIRY, FIGHTING, DRAGON}

    private EnergyType energyType;

    public EnergyCard(String type) {
        this.energyType = EnergyType.valueOf(type);
        this.cardType = CardType.ENERGY;
    }
    
    
}
