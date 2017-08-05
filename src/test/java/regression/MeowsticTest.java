package regression;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;

import card.Card;
import card.PokemonCard;
import entry.Config;
import game.Player;
import game.ai.IntelligentPlayer;
import parser.abilities.AbilitiesParser;
import parser.abilities.AbilityTemplate;
import parser.cards.CardLineSeperator;
import parser.cards.CardParser;
import parser.cards.DeckParser;
import util.ResourceReader;

public class MeowsticTest {

	@Test
	public void test() throws IOException, ClassNotFoundException {
		/*
		 * 
		 * 2017-08-03 Parsing Meowstic is not working. It is crashing the game at launch
		 * 
		 */
		
		AbilitiesParser abilitiesParser = new AbilitiesParser("abilities.txt");
        AbilityTemplate[] abilities = abilitiesParser.parse();
        
        
		CardLineSeperator cardLineSeperator = new CardLineSeperator();
		cardLineSeperator.seperate("Meowstic:pokemon:cat:stage-one:Espurr:cat:psychic:90:retreat:cat:colorless:1:attacks:cat:psychic:1:36,cat:psychic:3:37"
				, abilities);
		
		PokemonCard card = null;
		card = new PokemonCard(cardLineSeperator.getName(), cardLineSeperator.getCardSubClass(),
    			cardLineSeperator.getEvolvesFrom(), cardLineSeperator.getCardEnergyType(),
    			cardLineSeperator.getHp(), cardLineSeperator.getRetreatEnergyCost(),
    			cardLineSeperator.getAbilitiesList());
		
        assertEquals(card.getCardName(), "Meowstic");
        assertEquals(card.getRetreatEnergyCost().toString(), "EnergyCost{colorless=1, water=0, lightning=0, psychic=0, fight=0}");
        assertEquals(card.getAbility(0).getEnergyCost().toString(), "EnergyCost{colorless=0, water=0, lightning=0, psychic=1, fight=0}");
        assertEquals(card.getAbility(1).getEnergyCost().toString(), "EnergyCost{colorless=0, water=0, lightning=0, psychic=3, fight=0}");
        assertEquals(card.getEvolvesFrom(), "Espurr");
        assertEquals(card.getHp(), 90);
        
        //Now testing the hashmap
        CardParser cardParser = new CardParser(Config.FILE_PATH_CARDS_TXT);
        assertNull(cardParser.getCardMap().get(27));
        assertEquals(cardParser.getCardMap().get(28).getCardName(), "Meowstic");
        /*
         * Conclusion: The card parser counts the # as a line. Therefore, in this case, the line 27 will be considered null, and
         * Meowstic will be considered as line 28.
         */
        
        

	}

}
