package parser.cards;

/**
 * Created by frede on 2017-05-30.
 */
public class EnergyCost {
  
  

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
  public int colorless;
  public int fire;
  public int water;
  public int lightning;
  public int psychic;
  public int grass;
  public int darkness;
  public int metal;
  public int fairy;
  public int fight;
  public int dragon;

  public EnergyCost(){
    
  }
//Colorless-Fire-Water-Lightning-Psychic-Grass-Darkness-Metal-Fairy-Fightning-Dragon
  public EnergyCost(int colorless, int fire, int water, int lightning, int psychic, int grass, int darkness, int metal, int fairy, int fight, int dragon) {
    this.colorless = colorless;
    this.fire = fire;
    this.water = water;
    this.lightning = lightning;
    this.psychic = psychic;
    this.grass = grass;
    this.darkness = darkness;
    this.metal = metal;
    this.fairy = fairy;
    this.fight = fight;
    this.dragon = dragon;
  }
  
  public void addEnergy(String name, int num){
      switch(name){
        case "colorless":
          colorless += num;
          break;
        case "fire":
        	fire += num;
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
        case "grass":
        	grass += num;
            break;
        case "darkness":
        	darkness += num;
            break;
        case "metal":
        	metal += num;
            break;
        case "fairy":
        	fairy += num;
            break;
        case "fight":
        	fight += num;
            break;
        case "dragon":
        	dragon += num;
            break;
        default:
          System.out.println("ENERGY NAME ERROR "+name);  
      }
  }
  
  public void printEnergyCost(){
	// colorless-fire-water-lightning-psychic-grass-darkness-metal-fairy-fight-dragon
	  System.out.println("colorless: " + this.colorless);
	  System.out.println("fire: " + this.fire);
	  System.out.println("water: " + this.water);
	  System.out.println("lightning: " + this.lightning);
	  System.out.println("psychic: " + this.psychic);
	  System.out.println("grass: " + this.grass);
	  System.out.println("darkness: " + this.darkness);
	  System.out.println("metal: " + this.metal);
	  System.out.println("fairy: " + this.fairy);
	  System.out.println("fight: " + this.fight);
	  System.out.println("dragon: " + this.dragon);
	  
  }
  
  

  
  
  
}
