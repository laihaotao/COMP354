/*
 * description:  The class of pokemon card
 * author(s):    Eric(Haotao) Lai
 * reviewer(s):
 * date:         2017-05-17
 */

package card;

import java.util.ArrayList;
import java.util.List;

import game.effectstatus.Effect;
import game.effectstatus.Normal;
import parser.cards.EnergyCost;

public class PokemonCard extends Card {

    //basic, Stage One, Stage Two
    private String pokemonStage;
    private int hp;
    private int attackCounter;
    private List<Ability> abilities;
    private int damage;
    private int defense;
    private EnergyCost energyAttached = new EnergyCost();
    private ArrayList<EnergyCard> attachedEnergyCard = new ArrayList<>();

    // Colorless-Fire-Water-Lightning-Psychic-Grass-Darkness-Metal-Fairy-Fightning-Dragon
    private EnergyCost retreatEnergyCost;
    private String pokemonType;
    private String status;
    private String evolvesFrom;

    private Effect effect;

    public PokemonCard() {
        effect = new Normal(this);
    }


    public PokemonCard(String name, String basic, String evolvesFrom, String pokemonType, int hp, EnergyCost
            retreatEnergyCost, List<Ability> abilities) {

        this.pokemonStage = basic;
        this.name = name;
        this.evolvesFrom = evolvesFrom;
        this.pokemonType = pokemonType;
        this.hp = hp;
        this.retreatEnergyCost = retreatEnergyCost;
        this.abilities = abilities;
        this.damage = 0;
        this.defense = 0;
        this.attackCounter = 0;
        this.status = "";
        this.cardType = CardType.POKEMON;

        effect = new Normal(this);
    }


    public String getPokemonType() {
        return pokemonType;
    }

    public void setPokemonType(String pokemonType) {
        this.pokemonType = pokemonType;
    }


    public String getPokemonStage() {
        return pokemonStage;
    }


    public void setPokemonStage(String pokemonStage) {
        this.pokemonStage = pokemonStage;
    }


    public int getHp() {
        return hp;
    }


    public void setHp(int hp) {
        this.hp = hp;
    }


    public int getAttackCounter() {
        return attackCounter;
    }


    public void setAttackCounter(int attackCounter) {
        this.attackCounter = attackCounter;
    }


    public int getDamage() {
        return damage;
    }


    public void setDamage(int damage) {
        this.damage = damage;
    }


    public int getDefense() {
        return defense;
    }


    public void setDefense(int defense) {
        this.defense = defense;
    }


    public EnergyCost getRetreatEnergyCost() {
        return retreatEnergyCost;
    }


    public void setEnergyAttached(EnergyCost energyAttached) {
        // Colorless-Fire-Water-Lightning-Psychic-Grass-Darkness-Metal-Fairy-Fightning-Dragon

        this.energyAttached = energyAttached;
    }


    public EnergyCost getEnergyAttached() {
        // Colorless-Fire-Water-Lightning-Psychic-Grass-Darkness-Metal-Fairy-Fightning-Dragon

        return this.energyAttached;

    }
	
	/* Function convertAndReturnEnergyCost
	 * 
	 * Use this function when reading the cards.txt file. Pass in an array of Strings to convert
	 * it into an array to represent the energy cost.
	 * 
	 * Example of an argument that should be passed in this form:
	 * Array:	{"colorless", "5", "water", "1"};
	 * 	This function will then return an array that represents the energy cost of 5 colorless and
	  * 	1 water
	 */

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


    public int[] returnRetreatCostArray(int colorless, int fire, int water, int lightning, int
			psychic, int grass, int darkness, int metal, int fairy, int fighting, int dragon) {
        // Colorless-Fire-Water-Lightning-Psychic-Grass-Darkness-Metal-Fairy-Fightning-Dragon
        int[] retreatEnergyCost = new int[11];
        retreatEnergyCost[0] = colorless;
        retreatEnergyCost[1] = fire;
        retreatEnergyCost[2] = water;
        retreatEnergyCost[3] = lightning;
        retreatEnergyCost[4] = psychic;
        retreatEnergyCost[5] = grass;
        retreatEnergyCost[6] = darkness;
        retreatEnergyCost[7] = metal;
        retreatEnergyCost[8] = fairy;
        retreatEnergyCost[9] = fighting;
        retreatEnergyCost[10] = dragon;
        return retreatEnergyCost;
    }


    public void setRetreatEnergyCost(EnergyCost retreatEnergyCost) {
        // Colorless-Fire-Water-Lightning-Psychic-Grass-Darkness-Metal-Fairy-Fightning-Dragon

        this.retreatEnergyCost = retreatEnergyCost;

    }


    public void printRetreatCost() {
        System.out.println(this.retreatEnergyCost);

    }


    public String getType() {
        return pokemonType;
    }


    public void setType(String pokemonType) {
        this.pokemonType = pokemonType;
    }

    public String getStatus() {
        return status;
    }


    public void setStatus(String status) {
        this.status = status;
    }

    public void setEvolvesFrom(String evolvesFrom) {
        this.evolvesFrom = evolvesFrom;
    }

    public String getEvolvesFrom() {
        return evolvesFrom;
    }


    public Ability getAbility(int index) {
        return abilities.get(index);
    }

    public List<Ability> getAbilities() {
        return abilities;
    }

    public PokemonCard copy() {
        return new PokemonCard(name,getPokemonStage(), getEvolvesFrom(), getPokemonType(), getHp(), getRetreatEnergyCost(), abilities);
    }

    public void addEnergy(EnergyCard energyCard) {
        this.getEnergyAttached().addEnergy(energyCard.getEnergyType().toString(), 1);
        attachedEnergyCard.add(energyCard);
    }

    public ArrayList<EnergyCard> getAttachedEnergyCard() {
        return this.attachedEnergyCard;
    }

    public Effect getEffect() {
        return effect;
    }

    public void setEffect(Effect effect) {
        this.effect = effect;
    }
}
