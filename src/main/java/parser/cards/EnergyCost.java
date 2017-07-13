/*
 * description:  EnergyCost, make the output more clear
 * author(s):    frede
 * reviewer(s):
 * date:         2017-05-30
 */

package parser.cards;

import java.util.ArrayList;

/**
 * Created by frede on 2017-05-30.
 */
public class EnergyCost {

    public int colorless;
    public int water;
    public int lightning;
    public int psychic;
    public int fight;

    private int sum;

    public EnergyCost() {

    }

    //Colorless-Fire-Water-Lightning-Psychic-Grass-Darkness-Metal-Fairy-Fightning-Dragon
    public EnergyCost(int colorless, int water, int lightning, int psychic, int fight) {
        this.colorless = colorless;
        this.water = water;
        this.lightning = lightning;
        this.psychic = psychic;
        this.fight = fight;
        this.sum = colorless + water + lightning + psychic + fight;
    }

    public void addEnergy(String name, int num) {
        switch (name.toLowerCase()) {
            case "colorless":
                colorless += num;
                break;
            case "water":
                water += num;
                break;
            case "lightning":
                lightning += num;
                break;
            case "psychic":
                psychic += num;
                break;
            case "fight":
                fight += num;
                break;
            default:
                System.out.println("ENERGY NAME ERROR " + name);
        }
    }


    @Override
    public String toString() {
        return "EnergyCost{" +
                "colorless=" + colorless +
                ", water=" + water +
                ", lightning=" + lightning +
                ", psychic=" + psychic +
                ", fight=" + fight +
                '}';
    }

    public String toCondensedString() {
        String string = "[ ";
        if (colorless > 0) {
            string += "C: " + colorless + " ";
        }
        if (water > 0) {
            string += "W: " + water + " ";
        }
        if (lightning > 0) {
            string += "L: " + lightning + " ";
        }
        if (psychic > 0) {
            string += "P: " + psychic + " ";
        }
        if (fight > 0) {
            string += "F: " + fight;
        }
        string += "]";
        return string;
    }
    public static EnergyCost convertAndReturnEnergyCost(ArrayList<String> energyTypeAndAmount) {
        // Colorless-Fire-Water-Lightning-Psychic-Grass-Darkness-Metal-Fairy-Fight-Dragon

        EnergyCost energyCost = new EnergyCost();

        for (int i = 0; i < energyTypeAndAmount.size(); i += 2) {

            String energyType = energyTypeAndAmount.get(i);
            //energyTypeAndAmount[i] should be the the energyType, energyTypeAndAmount[i+1]
            // should be the amount
            int energyAmount = 0;
            energyAmount = Integer.parseInt(energyTypeAndAmount.get(i + 1));
            energyCost.addEnergy(energyType, energyAmount);

        }

        return energyCost;
    }

    public boolean canSupport(EnergyCost attachedEnergy) {
        int sumRemain = 0;
        int[] remain = new int[4];

        remain[0] = attachedEnergy.water - this.water;
        remain[1] = attachedEnergy.lightning - this.lightning;
        remain[2] = attachedEnergy.psychic - this.psychic;
        remain[3] = attachedEnergy.fight - this.fight;

        for (int res : remain) {
            if (res < 0) return false;
            sumRemain += res;
        }

        return sumRemain >= this.colorless;
    }

    public void retreat(EnergyCost retreatCost) {
//        water -= retreatCost.water;
//        lightning -= retreatCost.lightning;
//        psychic -= retreatCost.psychic;
//        fight -= retreatCost.fight;

        if (retreatCost.colorless != 0) {
            while (retreatCost.colorless !=0) {
                if (water != 0) {
                    water -= 1;
                }
                if (lightning != 0) {
                    lightning -= 1;
                }
                if (psychic != 0) {
                    psychic -= 1;
                }
                if (fight != 0) {
                    fight -= 1;
                }
                retreatCost.colorless--;
            }
        } else {
            if (retreatCost.water != 0) {
                water -= retreatCost.water;
            }
            if (retreatCost.lightning != 0) {
                lightning -= retreatCost.lightning;
            }
            if (retreatCost.psychic != 0) {
                psychic -= retreatCost.psychic;
            }
            if (retreatCost.fight != 0) {
                fight -= retreatCost.fight;
            }
        }
    }

    public void add(EnergyCost energyCost){
        this.colorless += energyCost.colorless;
        this.water += energyCost.water;
        this.lightning += energyCost.lightning;
        this.psychic += energyCost.psychic;
        this.fight += energyCost.fight;
    }

    public int[] getAsArray(){
        return new int[]{colorless, water, lightning, psychic, fight};
    }
}
