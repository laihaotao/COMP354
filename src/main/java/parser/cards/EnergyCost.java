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

    public EnergyCost() {

    }

    //Colorless-Fire-Water-Lightning-Psychic-Grass-Darkness-Metal-Fairy-Fightning-Dragon
    public EnergyCost(int colorless, int water, int lightning, int psychic, int fight) {
        this.colorless = colorless;
        this.water = water;
        this.lightning = lightning;
        this.psychic = psychic;
        this.fight = fight;
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

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof EnergyCost) {
            EnergyCost another = (EnergyCost) obj;

            return this.colorless == another.colorless
                    && this.water == another.water
                    && this.lightning == another.lightning
                    && this.psychic == another.psychic
                    && this.fight == another.fight;
        }
        return false;
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
    


}
