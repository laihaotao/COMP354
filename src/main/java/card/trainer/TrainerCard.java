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
    private String abilityName;
    private TrainerType trainerType;

    public TrainerCard(String name, TrainerType type, String abilityName) {
        this.name = name;
        this.cardType = CardType.TRAINER;
        this.trainerType = type;
        this.abilityName = abilityName;
    }
    
    public static TrainerType returnTrainerType (String type){
    	TrainerType trainerType = TrainerType.valueOf(type);
    	return trainerType;
    }
}
