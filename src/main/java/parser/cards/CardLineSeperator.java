/*
 * description:  Seperate line for CardParser
 * author(s):    Martin Tseng
 * reviewer(s):  
 * date:         2017-06-08
 */

package parser.cards;

import java.util.ArrayList;
import java.util.List;

import card.Ability;
import parser.abilities.AbilitiesParser;
import parser.abilities.AbilityTemplate;

public class CardLineSeperator {

	private String line;
	private String[] lineParts;
	private String name;
	private String cardClass;
	private String cardSubClass; //basic/stage-one
    private String evolvesFrom;
	private String cardEnergyType;
	private int hp;
	private EnergyCost retreatEnergyCost;
	private ArrayList<String> energyCost = new ArrayList<>();
    
	private List<Ability> abilitiesList = new ArrayList<>();
    
    private ArrayList <EnergyCost> abilitiesEnergyCostList = new ArrayList<>();
    private ArrayList <Integer> abilitiesID = new ArrayList<>();
    private ArrayList <String> attacksLineParts = new ArrayList<>();
    
    public CardLineSeperator(){
    	
    }
    
    
	public void seperate(String line, AbilityTemplate[] abilities){
		
		int beginIndex;
        
		this.line = line.replaceAll("é", "e");
        this.line = this.line.replaceAll("cat:", "");
        this.line = this.line.replaceAll(",", ":");
        lineParts = this.line.split(":");

        this.name = lineParts[0];
        this.cardClass = lineParts[1];
        this.cardSubClass = lineParts[2];
        
        if (cardClass.contains("pokemon")){
        	
        	if(cardSubClass.contains("basic")){
        		cardEnergyType = lineParts[3];
        		hp = Integer.parseInt(lineParts[4]);
        		beginIndex = 5;
        		
        	}else{
        		evolvesFrom = lineParts[3];
        		cardEnergyType = lineParts[4];
        		hp = Integer.parseInt(lineParts[5]);
        		beginIndex = 6;
        	}
        	
        	
            if(lineParts[beginIndex].contains("retreat")){
            	retreatEnergyCost = new EnergyCost(); 
            	ArrayList <String> retreatLineParts = new ArrayList<>();
            	
            	for (int i = beginIndex+1; i < lineParts.length; i++){
            		if (lineParts[i].contains("attacks")){
            			beginIndex = i;
            			retreatEnergyCost = EnergyCost.convertAndReturnEnergyCost(retreatLineParts);
            			break;
            		}else{
            			retreatLineParts.add(lineParts[i]);
            		}
            	}

            }
            else{
            	 //does not have any retreatCost 
            	retreatEnergyCost = new EnergyCost(); 
            }
        	
            
            
        	//System.out.println(abilitiesID.get(0));
        	//System.out.println(abilitiesEnergyCostList.get(0));
            
            
            
            for (int i = beginIndex+1; i < lineParts.length; i++){
        		
        		
        		if(isNumeric(lineParts[i])){
        			//check the next element. If it is a number, then it is an abilityID.
        			
        			abilitiesID.add(Integer.parseInt(lineParts[i]));
        			EnergyCost energyCost = EnergyCost.convertAndReturnEnergyCost(attacksLineParts);
        			abilitiesEnergyCostList.add(energyCost);
        			attacksLineParts.clear();
        			
        		} else {
        			
        			attacksLineParts.add(lineParts[i]);
        			attacksLineParts.add(lineParts[i+1]);
        			i++;
        		}
        			
        	}        	
            
        	//add in the abilities
        	for (int i = 0 ; i < abilitiesID.size();i++){
        		
        		Ability ability = new Ability(abilities[abilitiesID.get(i)-1], abilitiesEnergyCostList.get(i));
        		abilitiesList.add(ability);

        		
        		
        	}	
        	
        } //end of if pokemon
       ;

        
        
        if (lineParts[1].contains("trainer")){
        	EnergyCost energyCost = new EnergyCost();
        	Ability ability = new Ability(abilities[Integer.parseInt(lineParts[3]) -1], energyCost);
        	abilitiesList.add(ability);
        }
        
		
		
	}
	
	public void resetVariables(){
		line = "";
		lineParts = null;
		name = "";
		cardClass = "";
		cardSubClass = "";
	    evolvesFrom = "";
		cardEnergyType = "";
		hp= 0;
		retreatEnergyCost = null;
		energyCost = null;
	    abilitiesList = null;
	    abilitiesEnergyCostList = null;
	    abilitiesID = null;
		
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCardClass() {
		return cardClass;
	}

	public void setCardClass(String cardClass) {
		this.cardClass = cardClass;
	}

	public String getCardSubClass() {
		return cardSubClass;
	}

	public void setCardSubClass(String cardSubClass) {
		this.cardSubClass = cardSubClass;
	}

	public String getEvolvesFrom() {
		return evolvesFrom;
	}

	public void setEvolvesFrom(String evolvesFrom) {
		this.evolvesFrom = evolvesFrom;
	}

	public String getCardEnergyType() {
		return cardEnergyType;
	}

	public void setCardEnergyType(String cardEnergyType) {
		this.cardEnergyType = cardEnergyType;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public EnergyCost getRetreatEnergyCost() {
		return retreatEnergyCost;
	}

	public void setRetreatEnergyCost(EnergyCost retreatEnergyCost) {
		this.retreatEnergyCost = retreatEnergyCost;
	}

	public ArrayList<String> getEnergyCost() {
		return energyCost;
	}

	public void setEnergyCost(ArrayList<String> energyCost) {
		this.energyCost = energyCost;
	}

	public List<Ability> getAbilitiesList() {
		return abilitiesList;
	}

	public void setAbilitiesList(List<Ability> abilitiesList) {
		this.abilitiesList = abilitiesList;
	}

	private boolean isNumeric(String str)  
	{  
	  try  
	  {  
	    double d = Double.parseDouble(str);  
	  }  
	  catch(NumberFormatException nfe)  
	  {  
	    return false;  
	  }  
	  return true;  
	}
	
}




