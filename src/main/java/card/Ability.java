/*
 * description:  This class contains logic for abilities
 * author(s):    frede
 * reviewer(s):
 * date:         2017-05-17
 */

package card;

import parser.abilities.AbilityTemplate;
import parser.cards.EnergyCost;

public class Ability {

    private AbilityTemplate template;
    private EnergyCost energyCost;

    public Ability(AbilityTemplate template, EnergyCost energyCost) {
        this.template = template;
        this.energyCost = energyCost;
    }

    public AbilityTemplate getTemplate() {
        return template;
    }

    public EnergyCost getEnergyCost() {
        return this.energyCost;
    }

}
