/*
 * description:  Parse Cards from a String line
 * author(s):    Martin Tseng
 * reviewer(s):
 * date:         2017-05-29
 */

package parser.cards;
import java.io.IOException;
import java.util.ArrayList;
import card.Card;
import card.energy.EnergyCard;
import card.pokemon.PokemonCard;
import card.trainer.TrainerCard;


public class CardParser {


    public CardParser()
    {
    }
    
    
    
    public Card createCard(String line) throws IOException{
    	
    	
    	Card card = null;
    	
    	String cleanLine = line.replaceAll("é", "e");
    	cleanLine = cleanLine.replaceAll("cat:", "");
    	
    	String name;
    	String rest;
    	String typeAndHPLine;
    	String retreatCostLine = "";
    	String attacksLine;
		String pokemonStage;
		String pokemonType;
		int hp;
		int[] retreatEnergyCost = {0,0,0,0,0,0,0,0,0,0,0};
    	
       	//It is a PokemonCard
    	if (line.contains(":pokemon:")){
    		
    		//PokemonCard parameters: String name, String pokemonStage, String pokemonType , int hp, int[] retreatEnergyCost, ArrayList<String> abilityName, ArrayList<int[]> abilityCost) {
        	
 

   
    		
    		String evolvesFrom = "";
    		//get PokemonStage, pokemonType, and hp information for PokemonCard parameters
   
    //Shellder:pokemon:cat:basic:cat:water:60:retreat:cat:colorless:1:attacks:cat:colorless:1,cat:water:1:9
   //Seaking:pokemon:cat:stage-one:Goldeen:cat:water:90:attacks:cat:water:1:10,cat:colorless:1:11
    		
    		//if it is a stage-one or stage-two
    		if (line.contains(":stage")){
    			
        		//Extract pokename name from string
        		name = cleanLine.split(":pokemon:")[0];
        		//use the rest of the line to get the other information
        		rest = cleanLine.split(":pokemon:")[1];
        		//get all the information before retreat
        		typeAndHPLine = rest.split(":attacks:")[0];
        		//Rest of the information after retreat
        		rest =  rest.split(":attacks:")[1];
        		attacksLine = rest;
        		
        		
        		String [] typeAndHP_Parts = typeAndHPLine.split(":");
        		

    			
        		pokemonStage = typeAndHP_Parts[0];
        		evolvesFrom = typeAndHP_Parts[1];
        		pokemonType = typeAndHP_Parts[2];
        		hp = Integer.parseInt(typeAndHP_Parts[3]);
    		}
    		//else it is a basic pokemon
    		else{
    			
        		//Extract pokename name from string
        		name = cleanLine.split(":pokemon:")[0];
        		//use the rest of the line to get the other information
        		rest = cleanLine.split(":pokemon:")[1];
        		//get all the information before retreat
        		typeAndHPLine = rest.split(":retreat:")[0];
        		//Rest of the information after retreat
        		rest =  rest.split(":retreat:")[1];
        		//Extract Retreat Cost
        		retreatCostLine = rest.split(":attacks:")[0];
        		//Rest of the information after attacks:
        		rest =  rest.split(":attacks:")[1];
        		attacksLine = rest;
        		
        		
        		String [] typeAndHP_Parts = typeAndHPLine.split(":");
        		
    			
        		pokemonStage = typeAndHP_Parts[0];
        		pokemonType = typeAndHP_Parts[1];
        		hp = Integer.parseInt(typeAndHP_Parts[2]);
    		}

    		
    		

    		String [] retreatCostLineList;
    		String [] retreatCostParts;
    		ArrayList <String> retreatCostList = new ArrayList<>();;
    		
    		
    		if (line.contains(":basic:")){
	    		//If there is multiple color to retreat
	    		if (retreatCostLine.contains(",")){
	    			retreatCostLineList = retreatCostLine.split(",");
	    			
	    			
	        		for (int i = 0; i< retreatCostLineList.length; i++){
	        			
	        			retreatCostParts = retreatCostLineList[i].split(":");
	        			
	        			for (int j = 0; j< retreatCostParts.length; j++){
	        				retreatCostList.add(retreatCostParts[j]);
	        				
	        			}
	        			
	        		}
	    			
	    			
	    		} else {
	    			
	    			retreatCostParts = retreatCostLine.split(":");
	    			
	    			for (int j = 0; j< retreatCostParts.length; j++){
	    				retreatCostList.add(retreatCostParts[j]);
	    				
	    			}
	    			
	    			
	    		}
	    		
	    		retreatEnergyCost = PokemonCard.convertAndReturnEnergyArray(retreatCostList);
	    		
    		}
    		
    		
    		
    		
    		String [] attacksLineList = attacksLine.split(",");
    		
    		//split the ability cost
    		String [] energyCostParts;
    		ArrayList <String> energyCost = new ArrayList<>();;
    		ArrayList<int[]> abilityCost = new ArrayList<>();;
    		ArrayList<String> abilityName = new ArrayList<>();;
    		
    		
    		
    		for (int i = 0; i< attacksLineList.length; i++){

    			energyCostParts = attacksLineList[i].split(":");
    			
    			
    			//This array has no ability ID in it
    			if(energyCostParts.length < 3){
    				
    				for (int j = 0; j< energyCostParts.length; j++){
    					energyCost.add(energyCostParts[j]);
    				}
    				
    				
    			}
    			//Does contain ability ID in array
    			else{
    				for (int j = 0; j< energyCostParts.length-1; j++){
    					energyCost.add(energyCostParts[j]);
    				}
    				
    				int position= Integer.parseInt(energyCostParts[2]);
    				abilityName.add(Card.getAbilityNameInFileAt(position));
    				abilityCost.add(PokemonCard.convertAndReturnEnergyArray(energyCost));
    			}

    		}
    		
    		
    		if (line.contains(":basic")){
    			card = new PokemonCard(name, pokemonStage, pokemonType , hp, retreatEnergyCost, abilityName, abilityCost);
    		}
    		else {
    			card = new PokemonCard(name, pokemonStage, pokemonType , hp, abilityName, abilityCost);  	
    		}
    			
    		
    		
    		
    	}//end of if line contains ":pokemon:"
    	
    	
    	
    	
    	//It is a PokemonCard
    	else if (line.contains(":trainer:")){
    		
    		
    		//TrainerCard Parameters: String name, TrainerType type
    		//example: Misty's Determination:trainer:cat:supporter:33
    		String[] lineParts = cleanLine.split(":");
    		
    		name = lineParts[0];
    		
    		
    		//TrainerType type = TrainerType.valueOf(lineParts[2]);
    		
    		String type = lineParts[2].toUpperCase();
    		
    		String abilityName = Card.getAbilityNameInFileAt(lineParts[3]);
    		card = new TrainerCard("hi", "SUPPORTER", "anything");
    		
    		card = new TrainerCard(name, type, abilityName);
    		

    		return card;

    		
    	}
    	
    	
    	//It is a energy
    	else if (line.contains(":energy:")){
    		//parameters: enum EnergyType, EnergyType energyType;
    		
    		String[] lineParts = cleanLine.split(":");

    		card = new EnergyCard(lineParts[0]);

    		
    	}
    	
    	
    	return card;
     
    }
    

   
    
    

}
