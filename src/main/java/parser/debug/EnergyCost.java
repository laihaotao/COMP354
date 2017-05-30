package parser.debug;

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
  public int water;
  public int lightning;
  public int psychic;
  public int fight;

  public EnergyCost(){
    
  }
  
  public EnergyCost(int colorless, int water, int lightning, int psychic, int fight) {
    this.colorless = colorless;
    this.water = water;
    this.lightning = lightning;
    this.psychic = psychic;
    this.fight = fight;
  }
  
  public void addEnergy(String name, int num){
      switch(name){
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
          System.out.println("ENERGY NAME ERROR "+name);
          
      }
  }
}
