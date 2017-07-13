package game;

import card.PokemonCard;
import org.junit.Test;
import parser.cards.EnergyCost;

import static org.junit.Assert.assertEquals;
/**
 * Author: ERIC_LAI
 * Date:   2017-07-10
 * E-mail: haotao.lai@gmail.com
 */
public class CheckEnergyCost {

    @Test
    public void test1() {

        EnergyCost attachedEnergy = new EnergyCost(0, 10, 0, 0, 0);
        PokemonCard p1 = new PokemonCard("test1", "test1", "test1", "test1", 100, null
                , null);
        p1.setEnergyAttached(attachedEnergy);
        EnergyCost requiredEnergy = new EnergyCost(0, 3, 3, 3, 3);
        assertEquals(false, requiredEnergy.canSupport(p1.getEnergyAttached()));
    }

    @Test
    public void test2() {
        EnergyCost attachedEnergy = new EnergyCost(0, 1, 0, 0, 0);
        PokemonCard p1 = new PokemonCard("test1", "test1", "test1", "test1", 100, null
                , null);
        p1.setEnergyAttached(attachedEnergy);

        EnergyCost requiredEnergy = new EnergyCost(1, 0, 0, 0, 0);
        assertEquals(true, requiredEnergy.canSupport(p1.getEnergyAttached()));
    }

    @Test
    public void test3() {
        EnergyCost attachedEnergy = new EnergyCost(0, 1, 1, 0, 0);
        PokemonCard p1 = new PokemonCard("test1", "test1", "test1", "test1", 100, null
                , null);
        p1.setEnergyAttached(attachedEnergy);

        EnergyCost requiredEnergy = new EnergyCost(1, 0, 1, 0, 0);
        assertEquals(true, requiredEnergy.canSupport(p1.getEnergyAttached()));
    }

    @Test
    public void test4() {
        EnergyCost attachedEnergy = new EnergyCost(0, 1, 1, 1, 1);
        PokemonCard p1 = new PokemonCard("test1", "test1", "test1", "test1", 100, null
                , null);
        p1.setEnergyAttached(attachedEnergy);

        EnergyCost requiredEnergy = new EnergyCost(1, 0, 1, 1, 0);
        assertEquals(true, requiredEnergy.canSupport(p1.getEnergyAttached()));
    }
}
