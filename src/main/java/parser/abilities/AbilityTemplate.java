package parser.abilities;

import game.GameBoard;
import game.Player;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by frede on 2017-05-23.
 */
public class AbilityTemplate {
    public String name;
    
    public List<AbilityPart> parts = new ArrayList<>();
    
    public AbilityTemplate(String name){
        this.name = name;
    }
    
    public void use(GameBoard board, Player player){
        for(AbilityPart part : parts){
            part.use(board, player);
        }
    }
}
