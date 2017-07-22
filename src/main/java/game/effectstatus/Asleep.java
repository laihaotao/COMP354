package game.effectstatus;

import card.PokemonCard;
import game.Coin;

/**
 * Author:  Eric(Haotao) Lai
 * Date:    2017-07-22
 * E-mail:  haotao.lai@gmail.com
 * Website: http://laihaotao.me
 */


public class Asleep extends Effect {

    public Asleep(PokemonCard target) {
        super(target);
    }

    @Override
    void apply() {
        this.canAttack = false;
        this.canRetreat = false;
    }

    @Override
    public void remove() {
        Coin.CoinStatus status = Coin.flip();
        if (status == Coin.CoinStatus.Head) {
            super.remove();
        }
    }
}
