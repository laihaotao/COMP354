package game.effectstatus;

import card.PokemonCard;
import game.Coin;
import java.util.Random;

/**
 * Author:  Eric(Haotao) Lai
 * Date:    2017-07-22
 * E-mail:  haotao.lai@gmail.com
 * Website: http://laihaotao.me
 */


public class Poisoned extends Effect {

    public Poisoned(PokemonCard target) {
        super(target);
    }

    @Override
    public void apply() {
        this.target.setDamage(this.target.getDamage() + 10);
    }

    @Override
    public Effect remove() {
        return this;
    }
}
