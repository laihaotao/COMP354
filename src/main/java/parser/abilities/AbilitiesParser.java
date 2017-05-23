package parser.abilities;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import parser.commons.DestinationProperty;
import parser.commons.TargetProperty;
import parser.tokenizer.LanguageTokenizer;
import parser.tokenizer.Token;
import parser.tokenizer.TokenInteger;
import parser.tokenizer.TokenScope;
import parser.tokenizer.TokenStream;
import parser.tokenizer.TokenString;
import parser.tokenizer.TokenType;
import parser.ui.AbilityTemplatePane;
import parser.ui.TokenPane;

/**
 * Created by frede on 2017-05-23.
 */
public class AbilitiesParser {

  static Logger log = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
  
  LanguageTokenizer tokenizer;
  
  public AbilitiesParser(String fileName){
    tokenizer = new LanguageTokenizer(fileName);
  }
  
  public List<AbilityTemplate> parse() {
    List<TokenScope> scopes = tokenizer.tokenize();
    
    List<AbilityTemplate> templates = new ArrayList<>();
    
    scopes.forEach((scope)->{
        templates.add(parseNullScope(scope));
    });

    Stage stage = new Stage();
    stage.setTitle("Parser");
    
    BorderPane mainView = new BorderPane();
    
    TreeItem tokenItemPane = new TokenPane(scopes.toArray(new Token[0]));
    
    TreeView tokenView = new TreeView<>(tokenItemPane);
    tokenView.setMinWidth(600);
    mainView.setLeft(tokenView);
    
    TreeItem abilityItemPane = new AbilityTemplatePane((templates.toArray(new AbilityTemplate[0])));
    TreeView abilityView = new TreeView(abilityItemPane);
    abilityView.setMinWidth(600);
    mainView.setRight(abilityView);
    
    
    stage.setScene(new Scene(mainView, 1200,800));
    stage.show();
    
    
    
    return templates;
  }
  
  private AbilityTemplate parseNullScope(TokenScope scope){

    TokenStream tokenStream = new TokenStream(scope.tokens);
    
    String name = tokenStream.validateTokenString().value;
    if(name == null){
      log.error("Name token needs to be a string: " + tokenStream.getNextToken());
      return null;
    }
    
    AbilityTemplate template = new AbilityTemplate(name);
    
    //log.debug(" " + name+ " ");
    
    Token token = null;
    while((token = tokenStream.getNextToken()) != null) {
        if(token instanceof TokenString){
          TokenString tokenString = (TokenString)token;
          switch(tokenString.value){
            case "deck":
              template.parts.add(parsePartDeck(tokenStream));
              break;
            case  "dam":
              template.parts.add(parseDamPart(tokenStream));
              break;
            case "draw":
              template.parts.add(parseDrawPart(tokenStream));
              break;
            default:
              waitUntil(tokenStream, TokenType.SEPERATOR);
        }
        
        }
    }
    
    return template;
  }
  
  private void waitUntil(TokenStream stream, TokenType type){
    Token token = null;  
    while((token = stream.getNextToken()) != null){
        if(token.type == type){
          break;
        }
    }
  }
  
  private AbilityPart parsePartDeck(TokenStream tokenStream){
    
      //log.debug("---Deck---");

      TargetProperty targetPart = TargetProperty.read(tokenStream);
    
      DestinationProperty destinationPart = DestinationProperty.read(tokenStream);
      
      TokenString choice = null;
      if(tokenStream.validateTokenString("choice") != null){
        choice = tokenStream.validateTokenString();
      }
      
      Token amount = tokenStream.getNextToken();
      if(!(amount instanceof TokenScope) && !(amount instanceof TokenInteger))
      {
        tokenStream.backtrack();
      }
      //log.debug(targetPart);
      //log.debug(destinationPart);
      //log.debug("Choice: "+Formatting.toSafeString(choice));
      return new AbilityPartDeck(targetPart, destinationPart, choice, amount);
  }
  
  private AbilityPart parseDamPart(TokenStream tokenStream){
    //log.debug("---Dam---");

    TargetProperty targetPart = TargetProperty.read(tokenStream);
    
    Token amount = tokenStream.getNextToken();
    
    //log.debug(targetPart);
    //log.debug("Amount: " + amount);
    
    return new AbilityPartDam(targetPart, amount);
  }
  
  private AbilityPart parseDrawPart(TokenStream tokenStream){
    TargetProperty target = new TargetProperty(tokenStream.validateTokenString(), null);
    
    Token amount = tokenStream.getNextToken();
    if(!(amount instanceof TokenScope) && !(amount instanceof TokenInteger))
    {
      tokenStream.backtrack();
    }
    
    return new AbilityPartDraw(target, amount);
  }

  
}
