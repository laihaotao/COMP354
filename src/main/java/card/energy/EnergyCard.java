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
    private enum EnergyType {COLORLESS, FIRE, WATER, LIGHTNING, PSYCHIC, GRASS, DARKNESS, METAL, FAIRY, FIGHT, DRAGON}

    private EnergyType energyType;

    public EnergyCard(String type) {
    	type.toUpperCase();
    	if (type.equals("FIGHTING")){
    		type = "FIGHT";    		
    	}
    	
    	this.energyType = EnergyType.valueOf(type);
        this.cardType = CardType.ENERGY;
    }
    
    
}
