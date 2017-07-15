package regression;

import org.junit.Test;
import parser.abilities.AbilitiesParser;
import parser.abilities.AbilityTemplate;
import parser.cards.CardLineSeperator;

import static junit.framework.TestCase.assertEquals;

/**
 * Author:  Eric(Haotao) Lai
 * Date:    2017-07-14
 * E-mail:  haotao.lai@gmail.com
 * Website: http://laihaotao.me
 */


public class EnergyCostTest {


    @Test
    public void test() {
        /*
        2017-06-14
        We found a bug in the parsing where the line Parser was taking only one type of energy.
        Example:
        Pikachu Libre:pokemon:cat:basic:cat:lightning:80:
        retreat:cat:colorless:1:
        attacks:cat:colorless:2:3,cat:colorless:2,cat:lightning:1:4

        The last ability attack takes 2 colorless and 1 lightning, however the bug was
        taking only one type, which is colorless, and completely ignoring the lightning tpye
         */

        String line = "Pikachu Libre:pokemon:cat:basic:cat:lightning:80:retreat:cat:colorless:1" +
                ":attacks:cat:colorless:2:3,cat:colorless:2,cat:lightning:1:4";
        AbilitiesParser abilitiesParser = new AbilitiesParser("abilities.txt");
        AbilityTemplate[] abilities = abilitiesParser.parse();

        CardLineSeperator cardLineSeperator = new CardLineSeperator();
        cardLineSeperator.seperate(line, abilities);


        assertEquals(cardLineSeperator.getAbilitiesList().get(1).getEnergyCost().toString(),
                "EnergyCost{colorless=2, water=0, lightning=1, psychic=0, fight=0}");


    }


}
