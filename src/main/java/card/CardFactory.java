/*
 * description:  The abstract class of the Card
 * author(s):    Eric(Haotao) Lai
 * reviewer(s):
 * date:         2017-05-17
 */

package card;



import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.Line;



import card.energy.EnergyCard;
import card.pokemon.PokemonCard;
import card.trainer.TrainerCard;


public class CardFactory {


	private enum TrainerType {ITEM, SUPPORTER, STADIUM,}
	
	
    public CardFactory()
    {
    
    }
    
    
    
    public void createCard(String line) throws IOException{
    	
    	
    	
    	
    	String cleanLine = line.replaceAll("é", "e");
    	cleanLine = cleanLine.replaceAll("cat:", "");
    	
       	//It is a PokemonCard
    	if (line.contains(":pokemon:")){
    		
    		//PokemonCard parameters: String name, String pokemonStage, String pokemonType , int hp, int[] retreatEnergyCost, ArrayList<String> abilityName, ArrayList<int[]> abilityCost) {
        	
 
    		//Extract pokename name from string
    		String name = cleanLine.split(":pokemon:")[0];
    		
    		//use the rest of the line to get the other information
    		String rest = cleanLine.split(":pokemon:")[1];
    		
    		//get all the information before retreat
    		String typeAndHPLine = rest.split(":retreat:")[0];
    		
    		//Rest of the information after retreat
    		rest =  rest.split(":retreat:")[1];
    		
    		//Extract Retreat Cost
    		String retreatCostLine = rest.split(":attacks:")[0];
    		
    		//Rest of the information after attacks:
    		rest =  rest.split(":attacks:")[1];
    		String attacksLine = rest;
    		
    		
    		String [] typeAndHP_Parts = typeAndHPLine.split(":");
    		
    		String pokemonStage;
       		String evolvesFrom;
    		String pokemonType;
    		int hp;
   
    		//get PokemonStage, pokemonType, and hp information for PokemonCard parameters
    		
    		//if it is a stage-one or stage-two
    		if (typeAndHPLine.contains("stage")){
    			
        		pokemonStage = typeAndHP_Parts[0];
        		evolvesFrom = typeAndHP_Parts[1];
        		pokemonType = typeAndHP_Parts[2];
        		hp = Integer.parseInt(typeAndHP_Parts[3]);
    		}
    		//else it is a basic pokemon
    		else{
        		pokemonStage = typeAndHP_Parts[0];
        		pokemonType = typeAndHP_Parts[1];
        		hp = Integer.parseInt(typeAndHP_Parts[2]);
    		}

    		
    		

    		String [] retreatCostLineList;
    		String [] retreatCostParts;
    		ArrayList <String> retreatCostList = new ArrayList<>();;
    		
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
    		
    		
    		

    		
    		
    		
    		

    		
    		
    		
    		int[] retreatEnergyCost = PokemonCard.convertAndReturnEnergyArray(retreatCostList);
    		
    		
    		
    		
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
    		
    		
    		
    		PokemonCard pokemonCard = new PokemonCard(name, pokemonStage, pokemonType , hp, retreatEnergyCost, abilityName, abilityCost);
    		//return null;
    		//System.out.println(card.getPokemonStage());
    			
    			
    		
    		
    		
    		
    	}//end of if line contains ":pokemon:"
    	
    	
    	
    	
    	//It is a PokemonCard
    	if (line.contains(":trainer:")){
    		//TrainerCard Parameters: String name, TrainerType type
    		
    		/*
    		//Floral Crown:trainer:cat:item:67
    		String[] lineParts = cleanLine.split(":");
    		
    		String name = lineParts[0];
    		TrainerType type = TrainerType.valueOf(lineParts[2]);
    		String abilityName = Card.getAbilityNameInFileAt(lineParts[3]);
    		*/
    		
    		
    		//TrainerCard trainerCard = new TrainerCard(name, type, abilityName);
    		
    		
    		//System.out.println(lineParts);
    	}
    	
    	
    	//It is a energy
    	if (line.contains(":energy:")){
    		
    		
    	}
    	
    	
    	//System.out.println(line);
    	
    	
    	
    	//return card;
    	
    	//return null;.
     
    }
    

   
    
    

}
