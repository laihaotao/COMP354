package regression;
import card.Ability;
import card.Card;
import card.PokemonCard;
import card.Card.CardType;
import parser.abilities.AbilitiesParser;
import parser.abilities.AbilityTemplate;
import parser.cards.CardLineSeperator;

import static org.junit.Assert.*;

import org.junit.Test;

public class copyPokemonCard {

	@Test
	public void copyPokemonCard() {
		/*2017-07-08 It was reported that the getEvolvesFrom of a copy Pokemon simply returns "stage-one" and not the basic Pokemon. We will write a static
		 * test for this particular Pokemon:
		 * Raichu:pokemon:cat:stage-one:Pikachu:cat:lightning:90:attacks:cat:colorless:2:7,cat:colorless:1,cat:lightning:2:8
		 */
        AbilitiesParser abilitiesParser = new AbilitiesParser("abilities.txt");
        AbilityTemplate[] abilities = abilitiesParser.parse();
        
        
		CardLineSeperator cardLineSeperator = new CardLineSeperator();
		cardLineSeperator.seperate("Raichu:pokemon:cat:stage-one:Pikachu:cat:lightning:90:attacks:cat:colorless:2:7,cat:colorless:1,cat:lightning:2:8"
				, abilities);
		
		Card card = null;
		card = new PokemonCard(cardLineSeperator.getName(), cardLineSeperator.getCardSubClass(),
    			cardLineSeperator.getEvolvesFrom(), cardLineSeperator.getCardEnergyType(),
    			cardLineSeperator.getHp(), cardLineSeperator.getRetreatEnergyCost(),
    			cardLineSeperator.getAbilitiesList());
		
		PokemonCard pokemonCard = (PokemonCard) card;

		PokemonCard copyPokemonCard = pokemonCard.copy();
		

		assertEquals("stage-one", copyPokemonCard.getPokemonStage());
		assertEquals("Raichu", copyPokemonCard.getCardName());
		assertEquals("Pikachu", copyPokemonCard.getEvolvesFrom());
		assertEquals("lightning", copyPokemonCard.getPokemonType());
		assertEquals(90, copyPokemonCard.getHp());
		assertEquals("EnergyCost{colorless=0, water=0, lightning=0, psychic=0, fight=0}", copyPokemonCard.getRetreatEnergyCost().toString());

		
		
	}

}
