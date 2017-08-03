package regression;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.*;

import org.junit.Test;

import card.Card;
import card.PokemonCard;
import parser.abilities.AbilitiesParser;
import parser.abilities.AbilityTemplate;
import parser.cards.CardLineSeperator;

public class parseCardRegressionTest {

	@Test
	public void test() {

		
		
		/*
		 * 2017-08-03 The parseCard sundenly is not working anymore. We will write a regression test to detect this error faster
		 * 
		 * 
		 * Dugtrio:pokemon:cat:stage-one:Diglett:cat:fight:90:retreat:cat:colorless:1:attacks:cat:fight:1:54,cat:colorless:2,
		 * cat:fight:1:55
		 * 
		 */
		
		AbilitiesParser abilitiesParser = new AbilitiesParser("abilities.txt");
        AbilityTemplate[] abilities = abilitiesParser.parse();
        
        
		CardLineSeperator cardLineSeperator = new CardLineSeperator();
		cardLineSeperator.seperate("Dugtrio:pokemon:cat:stage-one:Diglett:cat:fight:90:retreat:cat:colorless:1:attacks:cat:fight:1:54,cat:colorless:2,cat:fight:1:55"
				, abilities);
		
		PokemonCard card = null;
		card = new PokemonCard(cardLineSeperator.getName(), cardLineSeperator.getCardSubClass(),
    			cardLineSeperator.getEvolvesFrom(), cardLineSeperator.getCardEnergyType(),
    			cardLineSeperator.getHp(), cardLineSeperator.getRetreatEnergyCost(),
    			cardLineSeperator.getAbilitiesList());
		
        assertEquals(card.getCardName(), "Dugtrio");
        assertEquals(card.getRetreatEnergyCost().toString(), "EnergyCost{colorless=1, water=0, lightning=0, psychic=0, fight=0}");
        assertEquals(card.getAbility(0).getEnergyCost().toString(), "EnergyCost{colorless=0, water=0, lightning=0, psychic=0, fight=1}");
        assertEquals(card.getAbility(1).getEnergyCost().toString(), "EnergyCost{colorless=2, water=0, lightning=0, psychic=0, fight=1}");
        assertEquals(card.getEvolvesFrom(), "Diglett");
        assertEquals(card.getHp(), 90);
		
        
	
		
		
	}

}
