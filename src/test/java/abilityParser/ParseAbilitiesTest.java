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
		 * Spike Cannon:dam:target:opponent-active:30,
		 * cond:flip:dam:target:opponent-active:30,
		 * cond:flip:dam:target:opponent-active:30,
		 * cond:flip:dam:target:opponent-active:30,
		 * cond:flip:dam:target:opponent-active:30,
		 * cond:flip:dam:target:opponent-active:30
		 */
		
        AbilitiesParser abilitiesParser = new AbilitiesParser("abilities.txt");
        AbilityTemplate[] abilities = abilitiesParser.parse();

        //System.out.print(abilities[0].parts.get(0));
        
        assertEquals("Damages Target: opponent-active:_ for 30", abilities[0].parts.get(0).getDescriptionString().toString());
        assertEquals("Condition: Damages Target: opponent-active:_ for 30", abilities[0].parts.get(1).getDescriptionString().toString());
        assertEquals("Condition: Damages Target: opponent-active:_ for 30", abilities[0].parts.get(2).getDescriptionString().toString());
        assertEquals("Condition: Damages Target: opponent-active:_ for 30", abilities[0].parts.get(3).getDescriptionString().toString());
        assertEquals("Condition: Damages Target: opponent-active:_ for 30", abilities[0].parts.get(4).getDescriptionString().toString());
        assertEquals("Condition: Damages Target: opponent-active:_ for 30", abilities[0].parts.get(5).getDescriptionString().toString());
        
  
        
	}

}
