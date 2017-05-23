package parser.ui;

import java.util.List;
import javafx.geometry.Insets;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.StackPane;
import parser.tokenizer.Token;
import parser.tokenizer.TokenArithmetic;
import parser.tokenizer.TokenScope;

/**
 * Created by frede on 2017-05-23.
 */
public class TokenPane extends TreeItem{

    Token[] tokens;
  
    public TokenPane(Token[] tokens){
        this.tokens = tokens;
      
        setExpanded(true);
        
        for(Token token : tokens){

            addToken(this, token);
          
        }
    }
    
    public void addToken(TreeItem ti, Token token){
      if(token instanceof TokenScope){
          TokenScope scope = ((TokenScope) token);
          TreeItem childItemn = new TreeItem((scope.prefix == null)? scope.tokens.get(0) : scope.prefix);
        
          ti.getChildren().add(childItemn);
          
          for(Token childToken : scope.tokens){
              
              addToken(childItemn, childToken);
          }
      }else if(token instanceof TokenArithmetic)
      {
        TokenArithmetic ta = (TokenArithmetic)token;
        TreeItem childItemn = new TreeItem(ta.type);
        addToken(childItemn, ta.leftValue);
        addToken(childItemn, ta.rightValue);
        ti.getChildren().add(childItemn);
      }
      else 
      {
       ti.getChildren().add(new TreeItem<String>(token.toString()));
      }
    }
    
    

}
