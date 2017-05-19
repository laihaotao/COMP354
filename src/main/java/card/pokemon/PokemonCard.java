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
    
	//basic, Stage One, Stage Two
	private String pokemonStage;
    private int hp;
    private int attackCounter;
    
    //note Abilties will contain special abilities, but also basic attacks
    private List<Ability> abilities = new ArrayList<>();
    
    private int damage;
    private int defense;
    private int [] attackEnergyCost = new int [11];
    // Colorless-Fire-Water-Lightning-Psychic-Grass-Darkness-Metal-Fairy-Fightning-Dragon
    private int [] retreatEnergyCost = new int [11];
    
    private String pokemonType;
    private String status;
    private String evolvesFrom;
    
    
    
    
    
    public PokemonCard(String pokemonStage, String name, int hp , String pokemonType, int[] retreatEnergyCost) {
    	this.pokemonStage = pokemonStage;
    	this.name = name;
        this.hp = hp;
        this.pokemonType= pokemonType; 
        this.retreatEnergyCost  = retreatEnergyCost;
        this.cardType = CardType.POKEMON;
        
        
        this.damage = 0;
        this.defense = 0;
        this.attackCounter = 0;
        this.status = "";
        
    }
    
    
    public PokemonCard(String pokemonStage, String name, int hp , String pokemonType, int colorless, int fire, int water, int lightning, int psychic, int grass, int darkness, int metal, int fairy, int fighting, int dragon) {
    	this.pokemonStage = pokemonStage;
    	this.name = name;
        this.hp = hp;
        this.pokemonType= pokemonType; 
        this.retreatEnergyCost  = retreatEnergyCost;
        this.cardType = CardType.POKEMON;
        
        
        this.damage = 0;
        this.defense = 0;
        this.attackCounter = 0;
        this.status = "";
        
        
        
        
	    // Colorless-Fire-Water-Lightning-Psychic-Grass-Darkness-Metal-Fairy-Fightning-Dragon
		this.attackEnergyCost[0] = colorless;
		this.attackEnergyCost[1] = fire;
		this.attackEnergyCost[2] = water;
		this.attackEnergyCost[3] = lightning;
		this.attackEnergyCost[4] = psychic;
		this.attackEnergyCost[5] = grass;
		this.attackEnergyCost[6] = darkness;
		this.attackEnergyCost[7] = metal;
		this.attackEnergyCost[8] = fairy;
		this.attackEnergyCost[9] = fighting;
		this.attackEnergyCost[10] = dragon;
        
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


	public int[] getRetreatEnergyInt() {
		return retreatEnergyCost;
	}


	public void setAttackEnergyCost(int colorless, int fire, int water, int lightning, int psychic, int grass, int darkness, int metal, int fairy, int fighting, int dragon) {
	    // Colorless-Fire-Water-Lightning-Psychic-Grass-Darkness-Metal-Fairy-Fightning-Dragon
		this.attackEnergyCost[0] = colorless;
		this.attackEnergyCost[1] = fire;
		this.attackEnergyCost[2] = water;
		this.attackEnergyCost[3] = lightning;
		this.attackEnergyCost[4] = psychic;
		this.attackEnergyCost[5] = grass;
		this.attackEnergyCost[6] = darkness;
		this.attackEnergyCost[7] = metal;
		this.attackEnergyCost[8] = fairy;
		this.attackEnergyCost[9] = fighting;
		this.attackEnergyCost[10] = dragon;
	}
	
	
	public int[] returnAttackCostArray(int colorless, int fire, int water, int lightning, int psychic, int grass, int darkness, int metal, int fairy, int fighting, int dragon) {
	    // Colorless-Fire-Water-Lightning-Psychic-Grass-Darkness-Metal-Fairy-Fightning-Dragon
		int [] attackEnergyCost = new int[11];
		attackEnergyCost[0] = colorless;
		attackEnergyCost[1] = fire;
		attackEnergyCost[2] = water;
		attackEnergyCost[3] = lightning;
		attackEnergyCost[4] = psychic;
		attackEnergyCost[5] = grass;
		attackEnergyCost[6] = darkness;
		attackEnergyCost[7] = metal;
		attackEnergyCost[8] = fairy;
		attackEnergyCost[9] = fighting;
		attackEnergyCost[10] = dragon;
		return attackEnergyCost;
	}
	
	
	public void setAttackEnergyCost(int []attackEnergyCost) {
	    // Colorless-Fire-Water-Lightning-Psychic-Grass-Darkness-Metal-Fairy-Fightning-Dragon
		
		for (int i = 0; i< this.attackEnergyCost.length; i++){
			this.attackEnergyCost[i] = attackEnergyCost[i];
		}

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


	public void setAbilities(List<Ability> abilities) {
		this.abilities = abilities;
	}


	public List<Ability> getAbilities(){
        return abilities;
    }
	
	
	public void setEvolvesFrom(String evolvesFrom) {
		this.evolvesFrom = evolvesFrom;
	}
	
	public String getEvolvesFrom() {
		return evolvesFrom;
	}

	
}
