package parser.abilities;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import parser.tokenizer.LanguageTokenizer;
import parser.tokenizer.Token;
import parser.tokenizer.TokenScope;
import parser.tokenizer.TokenStream;
import parser.tokenizer.TokenString;
import parser.tokenizer.TokenType;

/**
 * Created by frede on 2017-05-23.
 */
public class AbilitiesParser {

  static Logger log = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
  
  LanguageTokenizer tokenizer;
  
  public AbilitiesParser(String fileName){
    tokenizer = new LanguageTokenizer(fileName);
  }
  
  public void parse() {
    List<TokenScope> scopes = tokenizer.tokenize();
    scopes.forEach((scope)->{
        parseNullScope(scope);
    });
  }
  
  private void parseNullScope(TokenScope scope){

    TokenStream tokenStream = new TokenStream(scope.tokens);
    
    String name = tokenStream.validateTokenString().value;
    if(name == null){
      log.error("Name token needs to be a string: " + tokenStream.getNextToken());
      return;
    }
    
    System.out.println("Name: " + name);
    
    TokenString token = null;
    while((token = tokenStream.validateTokenString()) != null) {
        switch(token.value){
          case "deck":
            parsePartDeck(tokenStream);
            break;
          case  "dam":
            parseDamPart(tokenStream);
            break;
          default:
            waitUntil(tokenStream, TokenType.SEPERATOR);
        }
    }
    
  }
  
  private void waitUntil(TokenStream stream, TokenType type){
    Token token = null;  
    while((token = stream.getNextToken()) != null && token.type != type){
      
    }
  }
  
  private boolean parsePartDeck(TokenStream tokenStream){
    
      log.debug("---Deck---");

    TokenString target = null;
    TokenString targetModifier = null;
      
      if(tokenStream.validateTokenString("target") != null){
        if((target = tokenStream.validateTokenString("choice")) != null){

          if((targetModifier = tokenStream.validateTokenString()) == null){

          }

        }else if((target = tokenStream.validateTokenString()) == null){

        }
      }
    
      if(tokenStream.validateTokenString("destination") == null){
        log.error("Expected destination token not present");
        return false;
      }
      TokenString destination = tokenStream.validateTokenString();
      Token destinationModifier = tokenStream.getNextToken();

      TokenString choice = null;
      if(tokenStream.validateTokenString("choice") != null){
        choice = tokenStream.validateTokenString();
      }
      

      log.debug("Target: " + toSafeString(target) + " "+toSafeString(targetModifier));
      log.debug("Destination: " + destination.value + " " + destinationModifier);
      log.debug("Choice: "+toSafeString(choice));
      return true;
  }
  
  private boolean parseDamPart(TokenStream tokenStream){
    log.debug("---Dam---");
    
    if(tokenStream.validateTokenString("target") == null){
      log.error("Expected target token not present!");
      return false;
    }

    TokenString target = null;
    TokenString targetModifier = null;
    if((target = tokenStream.validateTokenString("choice")) != null){

      if((targetModifier = tokenStream.validateTokenString()) == null){
        log.error("Expected target modifier as string");
      }

    }else if((target = tokenStream.validateTokenString()) == null){
      log.error("Expected token identifier token as string");
      return false;
    }
    
    Token amount = tokenStream.getNextToken();
    
    log.debug("Target: " + target.value + " "+toSafeString(targetModifier));
    log.debug("Amount: " + amount);
    
    return true;
  }
  
  private String toSafeString(TokenString tokenString){
    if(tokenString == null){
      return "_";
    }
    return tokenString.value;
  }
  
}
