package parser.abilities;

import game.GameBoard;
import game.Player;
import java.util.ArrayList;
import java.util.List;
import parser.abilities.parts.AbilityPart;
import parser.abilities.parts.AbilityPartDam;

/**
 * Ability template contains all the non player-specific parts of the ability 
 */
public class AbilityTemplate {
    public String name;
    
    public List<AbilityPart> parts = new ArrayList<>();
    
    public AbilityTemplate(String name){
        this.name = name;
    }

    /**
     * Use the ability on a gameboard
     * @param board The board to be used
     * @param owner The Player owner of the ability
     */
    public boolean use(GameBoard board, Player owner){
        boolean used = false;
        for(AbilityPart part : parts){
            used |= part.use(board, owner);
        }
        return used;
    }

    public boolean appliesDamage(){
        for(AbilityPart part : parts){
            if(part instanceof AbilityPartDam){
                return true;
            }
        }

        return false;
    }
    
    public AbilityTemplate getCopy(){
        AbilityTemplate copyTemplate = new AbilityTemplate(name);
        parts.forEach(part->{
            copyTemplate.parts.add(part);
        });
        
        return copyTemplate;
    }
}
