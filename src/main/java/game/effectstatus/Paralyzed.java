package game.effectstatus;

import card.PokemonCard;

/**
 * Author:  Eric(Haotao) Lai
 * Date:    2017-07-22
 * E-mail:  haotao.lai@gmail.com
 * Website: http://laihaotao.me
 */


public class Paralyzed extends Effect {

    public Paralyzed(PokemonCard target) {
        super(target);
    }

    @Override
    public void apply() {
        this.canAttack = false;
        this.canRetreat = false;
    }
}
