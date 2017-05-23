package parser.abilities;

import java.lang.annotation.Target;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import parser.commons.DestinationPart;
import parser.commons.Formatting;
import parser.commons.TargetPart;
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

      TargetPart targetPart = TargetPart.read(tokenStream);
    
      DestinationPart destinationPart = DestinationPart.read(tokenStream);
      
      TokenString choice = null;
      if(tokenStream.validateTokenString("choice") != null){
        choice = tokenStream.validateTokenString();
      }
      

      log.debug(targetPart);
      log.debug(destinationPart);
      log.debug("Choice: "+Formatting.toSafeString(choice));
      return true;
  }
  
  private boolean parseDamPart(TokenStream tokenStream){
    log.debug("---Dam---");

    TargetPart targetPart = TargetPart.read(tokenStream);
    
    Token amount = tokenStream.getNextToken();
    
    log.debug(targetPart);
    log.debug("Amount: " + amount);
    
    return true;
  }
  

  
}
