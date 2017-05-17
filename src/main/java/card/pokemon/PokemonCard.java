/*
 * description:  The abstract class of pokemon card
 * author(s):    Eric(Haotao) Lai
 * reviewer(s):
 * date:         2017-05-17
 */

package card.pokemon;

import card.Card;
import card.abilities.Ability;
import java.util.ArrayList;
import java.util.List;

public class PokemonCard extends Card {
    
    private int HP;
    private String Attack;
    private int Damage;
    private String type;
    
    private List<Ability> abilities = new ArrayList<>();
    
    public PokemonCard(String name, int hp, int Damage, String type) {
        this.name = name;
        this.HP = hp;
        this.Damage= Damage;
        this.type= type;
        this.cardType = CardType.POKEMON;
    }
    
    public List<Ability> getAbilities(){
        return abilities;
    }
}
