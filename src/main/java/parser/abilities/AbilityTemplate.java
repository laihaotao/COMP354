package parser.abilities;

import game.GameBoard;
import game.Player;
import java.util.ArrayList;
import java.util.List;

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
    public void use(GameBoard board, Player owner){
        for(AbilityPart part : parts){
            part.use(board, owner);
        }
    }
}
