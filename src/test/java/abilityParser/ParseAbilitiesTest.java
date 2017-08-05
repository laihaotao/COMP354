package abilityParser;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import card.Card;
import card.PokemonCard;
import entry.Config;
import parser.abilities.AbilitiesParser;
import parser.abilities.AbilityTemplate;
import parser.cards.CardParser;
import parser.cards.DeckParser;

public class ParseAbilitiesTest {

	@Test
	public void ParseAbilityTest() throws IOException, ClassNotFoundException {
		
		/*
		 * 
			first ability is:
			"Act Cute:deck:target:opponent:destination:deck:bottom:choice:them:1"
			
		 */
		
        AbilitiesParser abilitiesParser = new AbilitiesParser("abilities.txt");
        AbilityTemplate[] abilities = abilitiesParser.parse();

        assertEquals("Act Cute", abilities[0].name);
        assertEquals("Target: opponent:_ and filters nothing", abilities[0].parts.get(0).properties.get(0).toString());
        assertEquals("Target: deck:bottom and filters nothing", abilities[0].parts.get(0).properties.get(1).toString());
        assertEquals("Choice: @65 token:String -> them", abilities[0].parts.get(0).properties.get(2).toString());
        assertEquals("Amount: @67 token:Integer -> 1", abilities[0].parts.get(0).properties.get(3).toString());
        
  
        
	}

}
