/*
 * description:  Parse Cards from a String line
 * author(s):    Martin Tseng
 * reviewer(s):  Eric(Haotao) Lai
 * date:         2017-05-29
 */

package parser.cards;

import card.Ability;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

import card.Card;
import card.EnergyCard;
import card.PokemonCard;
import card.TrainerCard;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import parser.abilities.AbilitiesParser;
import parser.abilities.AbilityTemplate;
import util.ResourceReader;


public class CardParser {

    final static Logger logger = LogManager.getLogger(CardParser.class.getName());

    private BufferedReader br;
    private HashMap<Integer, Card> cardMap;

    public HashMap<Integer, Card> getCardMap() {
        return cardMap;
    }

    public CardParser(String path) throws IOException {
        InputStream is = ResourceReader.readFile(path);
        br = new BufferedReader(new InputStreamReader(is));
        cardMap = new HashMap<>();
        buildCardMap();
    }

    private void buildCardMap() throws IOException {
        int lineNum = 1;
        String line;

      
        while ((line = br.readLine()) != null) {


            Card card = createCard(line);


            if (null != card) {
                cardMap.put(lineNum, card);
                //commenting this out to free the console for now
                logger.debug(lineNum + ": " + card.getCardName());
            }


            lineNum++;


        }


    }

    private Card createCard(String line) throws IOException {
    	
    	
    	Card card = null;
       
    	
        if ("#".equals(line) || line.isEmpty()) return null;
        
        
        CardLineSeperator cardLineSeperator = new CardLineSeperator();
        cardLineSeperator.seperate(line);
        
        
        if(line.contains(":pokemon:")){
        	
        	card = new PokemonCard(cardLineSeperator.getName(), cardLineSeperator.getCardSubClass(),
        			cardLineSeperator.getEvolvesFrom(), cardLineSeperator.getCardEnergyType(),
        			cardLineSeperator.getHp(), cardLineSeperator.getRetreatEnergyCost(),
        			cardLineSeperator.getAbilitiesList());
        	
        	
        }
        else if(line.contains(":trainer:")){
        	card = new TrainerCard(cardLineSeperator.getName(), cardLineSeperator.getCardSubClass(),
        			cardLineSeperator.getAbilitiesList().get(0));
        	
        }
        else if(line.contains(":energy:")){
        	card = new EnergyCard(cardLineSeperator.getName());
        }
        
        cardLineSeperator.resetVariables();
        
        return card;
    }


}
