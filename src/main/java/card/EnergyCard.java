/*
 * description:  The class of energy card
 * author(s):    Eric(Haotao) Lai
 * reviewer(s):
 * date:         2017-05-17
 */

package card;


public class EnergyCard extends Card {
	 // Colorless-Fire-Water-Lightning-Psychic-Grass-Darkness-Metal-Fairy-Fight-Dragon
    public enum EnergyType {Colorless, Fire, Water, Lightning, Psychic, Grass, Darkness, Metal, Fairy, Fight, Dragon;}

    private EnergyType energyType;

    public EnergyCard(String type) {
        this(Enum.valueOf(EnergyType.class, type));
    }

    public EnergyCard(EnergyType type){
        this.energyType = type;
        this.cardType = CardType.ENERGY;
    }
    
    @Override
    public String getCardName() {
        return this.energyType.toString();
    }
    
    public EnergyCard copy(){
        return new EnergyCard(energyType);
    }
    
    public EnergyType getEnergyType(){
        return energyType;
    }
}
