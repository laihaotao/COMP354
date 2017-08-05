package game.effectstatus;

import card.PokemonCard;

/**
 * Author:  Eric(Haotao) Lai
 * Date:    2017-07-22
 * E-mail:  haotao.lai@gmail.com
 * Website: http://laihaotao.me
 */


public class Stuck extends Effect {

    public Stuck(PokemonCard target) {
        super(target);
    }

    @Override
    public void apply() {
        this.canRetreat = false;
        this.canAttack = true;
    }
}
