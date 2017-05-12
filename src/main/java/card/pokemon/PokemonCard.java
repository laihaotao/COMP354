package card.pokemon;

import card.Card;

/**
 * Created by ERIC_LAI on 2017-05-06.
 */
public class PokemonCard extends Card {

    public PokemonCard(String name) {
        this.name = name;
        this.cardType = CardType.POKEMON;
    }
}
