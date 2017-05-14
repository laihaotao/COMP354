/*
 * description:  The abstract class of pokemon card
 * author(s):    Eric(Haotao) Lai
 * reviewer(s):
 * date:         2017-05-17
 */

package card.pokemon;

import card.Card;

public class PokemonCard extends Card {
    int HP;
    String Attack;
    int Damage;
    public PokemonCard(String name) {
        this.name = name;
        this.cardType = CardType.POKEMON;
    }
}
