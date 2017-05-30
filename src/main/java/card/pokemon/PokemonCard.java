/*
 * description:  The abstract class of pokemon card
 * author(s):    Eric(Haotao) Lai
 * reviewer(s):
 * date:         2017-05-17
 */

package card.pokemon;

import card.Card;
import card.abilities.Ability;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import parser.abilities.AbilityTemplate;

public class PokemonCard extends Card {
    

	//basic, Stage One, Stage Two
	private String pokemonStage;
    private int hp;
    private int attackCounter;
    
    //note: Abilities will contain special abilities, but also basic attacks
    //Each ability has a cost, therefore abilityName[i] will refer to abilityCost[i];
    /**private ArrayList<AbilityTemplate> abilityTemplates = new ArrayList<>();
    private ArrayList<int[]> abilityCost = new ArrayList<>();
    **/
    private List<Ability> abilities;
    
    private int damage;
    private int defense;
   
    
    
    
    private int [] energyAttached = new int [11];
    // Colorless-Fire-Water-Lightning-Psychic-Grass-Darkness-Metal-Fairy-Fightning-Dragon
    private int [] retreatEnergyCost = new int [11];
    private String pokemonType;
    private String status;
    private String evolvesFrom;
    
    
    
    
    public PokemonCard(){
	
    }
    
    
    public PokemonCard(String name, String basic, String pokemonType , int hp, int[] retreatEnergyCost, List<Ability> abilities) {
    	
    	this.pokemonStage = basic;
    	this.name = name;
    	this.pokemonType = pokemonType;
        this.hp = hp;
        this.retreatEnergyCost  = retreatEnergyCost;
        
        
        this.abilities = abilities;

        
        this.damage = 0;
        this.defense = 0;
        this.attackCounter = 0;
        this.status = "";
        this.cardType = CardType.POKEMON;
        
    }
    
    
    
    
    public PokemonCard(String name, String pokemonStage, String pokemonType , int hp, List<Ability> abilities) {
    	
    	this.pokemonStage = pokemonStage;
    	this.name = name;
    	this.pokemonType = pokemonType;
        this.hp = hp;
        
        
        this.abilities = abilities;

        
        this.damage = 0;
        this.defense = 0;
        this.attackCounter = 0;
        this.status = "";
        this.cardType = CardType.POKEMON;
        
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


	public int[] getRetreatEnergyCost() {
		return retreatEnergyCost;
	}


	public void setEnergyAttached(int colorless, int fire, int water, int lightning, int psychic, int grass, int darkness, int metal, int fairy, int fighting, int dragon) {
	    // Colorless-Fire-Water-Lightning-Psychic-Grass-Darkness-Metal-Fairy-Fightning-Dragon
		this.energyAttached[0] = colorless;
		this.energyAttached[1] = fire;
		this.energyAttached[2] = water;
		this.energyAttached[3] = lightning;
		this.energyAttached[4] = psychic;
		this.energyAttached[5] = grass;
		this.energyAttached[6] = darkness;
		this.energyAttached[7] = metal;
		this.energyAttached[8] = fairy;
		this.energyAttached[9] = fighting;
		this.energyAttached[10] = dragon;
	}
	
	
	public void setEnergyAttached(int []energyAttached) {
	    // Colorless-Fire-Water-Lightning-Psychic-Grass-Darkness-Metal-Fairy-Fightning-Dragon
		
		for (int i = 0; i< this.energyAttached.length; i++){
			this.energyAttached[i] = energyAttached[i];
		}

	}
	
	
	public int[] getEnergyAttached() {
	    // Colorless-Fire-Water-Lightning-Psychic-Grass-Darkness-Metal-Fairy-Fightning-Dragon
		
		return this.energyAttached;

	}
	
	
	public void setRetreatEnergyCost(int colorless, int fire, int water, int lightning, int psychic, int grass, int darkness, int metal, int fairy, int fighting, int dragon) {
	    // Colorless-Fire-Water-Lightning-Psychic-Grass-Darkness-Metal-Fairy-Fightning-Dragon
		this.retreatEnergyCost[0] = colorless;
		this.retreatEnergyCost[1] = fire;
		this.retreatEnergyCost[2] = water;
		this.retreatEnergyCost[3] = lightning;
		this.retreatEnergyCost[4] = psychic;
		this.retreatEnergyCost[5] = grass;
		this.retreatEnergyCost[6] = darkness;
		this.retreatEnergyCost[7] = metal;
		this.retreatEnergyCost[8] = fairy;
		this.retreatEnergyCost[9] = fighting;
		this.retreatEnergyCost[10] = dragon;
	}
	
	
	//keep it static so we can use this Class function without instantiating
	public int[] convertAndReturnEnergyArray(int colorless, int fire, int water, int lightning, int psychic, int grass, int darkness, int metal, int fairy, int fighting, int dragon) {
	    // Colorless-Fire-Water-Lightning-Psychic-Grass-Darkness-Metal-Fairy-Fightning-Dragon
		
		int[] energyArray = new int [11];
		
		
		energyArray[0] = colorless;
		energyArray[1] = fire;
		energyArray[2] = water;
		energyArray[3] = lightning;
		energyArray[4] = psychic;
		energyArray[5] = grass;
		energyArray[6] = darkness;
		energyArray[7] = metal;
		energyArray[8] = fairy;
		energyArray[9] = fighting;
		energyArray[10] = dragon;
		
		return energyArray;
	}
	
	
	/* Function convertAndReturnEnergyArray
	 * 
	 * Use this function when reading the cards.txt file. Pass in an array of Strings to convert it into an array to represent the energy cost.
	 * 
	 * Example of an argument that should be passed in this form:
	 * Array:	{"colorless", "5", "water", "1"};
	 * 	This function will then return an array that represents the energy cost of 5 colorless and 1 water
	 */

	public static int[] convertAndReturnEnergyArray(ArrayList <String> energyTypeAndAmount) {
	    // Colorless-Fire-Water-Lightning-Psychic-Grass-Darkness-Metal-Fairy-Fightning-Dragon
		int[] energyArray = new int [11];
		for (int i=0; i< energyTypeAndAmount.size(); i+=2){
			
			String energyType = energyTypeAndAmount.get(i);
			energyType.toLowerCase();
			//energyTypeAndAmount[i] should be the the energyType, energyTypeAndAmount[i+1] should be the amount
			
			int energyAmount =0;
			
			energyAmount = Integer.parseInt(energyTypeAndAmount.get(i+1));
			
			switch (energyType){
			case "colorless": 
				energyArray[0] = energyAmount;
				break;
			case "fire":
				energyArray[1] = energyAmount;
				break;
			case "water": 
				energyArray[2] = energyAmount;
				break;
			case "lightning": 
				energyArray[3] = energyAmount;
				break;
			case "psychic": 
				energyArray[4] = energyAmount;
				break;
			case "grass": 
				energyArray[5] = energyAmount;
				break;
			case "darkness": 
				energyArray[6] = energyAmount;
				break;
			case "metal": 
				energyArray[7] = energyAmount;
				break;
			case "fairy": 
				energyArray[8] = energyAmount;
				break;
			case "fighting": 
				energyArray[9] = energyAmount;
				break;
			case "dragon": 
				energyArray[10] = energyAmount;
				break;
			}
			
			
		}

	return energyArray;
	}
	
	
	
	
	
	
	
	
	public int[] returnRetreatCostArray(int colorless, int fire, int water, int lightning, int psychic, int grass, int darkness, int metal, int fairy, int fighting, int dragon) {
	    // Colorless-Fire-Water-Lightning-Psychic-Grass-Darkness-Metal-Fairy-Fightning-Dragon
		int [] retreatEnergyCost = new int[11];
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
	
	
	public void setRetreatEnergyCost(int []retreatEnergyCost) {
	    // Colorless-Fire-Water-Lightning-Psychic-Grass-Darkness-Metal-Fairy-Fightning-Dragon
		
		for (int i = 0; i< this.retreatEnergyCost.length; i++){
			this.retreatEnergyCost[i] = retreatEnergyCost[i];
		}

	}
	
	
	public void printRetreatCost(){
		
		
		
		System.out.println("colorless: " + this.retreatEnergyCost[0]);
		System.out.println("Fire: " + this.retreatEnergyCost[1]);
		System.out.println("Water: " + this.retreatEnergyCost[2]);
		System.out.println("Lightning: " + this.retreatEnergyCost[3]);
		System.out.println("Psychic: " + this.retreatEnergyCost[4]);
		System.out.println("Grass: " + this.retreatEnergyCost[5]);
		System.out.println("Darkness: " + this.retreatEnergyCost[6]);
		System.out.println("Metal: " + this.retreatEnergyCost[7]);
		System.out.println("Fairy: " + this.retreatEnergyCost[8]);
		System.out.println("Fightning: " + this.retreatEnergyCost[9]);
		System.out.println("Dragon: " + this.retreatEnergyCost[10]);
		
		

		
	}


	public String getType() {
		return pokemonType;
	}


	public void setType(String type) {
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
	

	
}
