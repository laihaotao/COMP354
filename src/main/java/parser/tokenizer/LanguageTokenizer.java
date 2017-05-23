package parser.tokenizer;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Parses a file for the language the teacher defined in the text files
 */
public class LanguageTokenizer {

  private String fileName;
  
  public LanguageTokenizer(String fileName) {
      this.fileName = fileName;
  }

  /**
   * Parse and print output of file
   */
  public List<TokenScope> tokenize() {
    Path path1 = null;
    try {
      path1 = Paths.get(ClassLoader.getSystemResource(fileName).toURI());
    } catch (URISyntaxException e) {
      e.printStackTrace();
    }
    
    List<TokenScope> lineScopes = new ArrayList<>();
    
    try {
      Files.lines(path1).forEach((line) -> {
        lineScopes.add(parseLine(line));
      });
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    return lineScopes;
  }

  /**
   * Will tokenize a file line, each line is isolated
   * @param line
   */
  private TokenScope parseLine(String line) {
    //System.out.println("Parsing: " + line);
    TokenScope scope = processScope(line, 0, '\n');
    //printScope(scope, 0);
    
    return scope;
    
  }

  /**
   * Print out the parsed lines recursively
   * @param scope
   * @param depth
   */
  private void printScope(TokenScope scope, int depth){
    
    String tabs = "";
    for(int i=0; i < depth; i++){
      tabs += "\t";
    }
    
    System.out.println(tabs + "Prefix: " + scope.prefix);
    tabs += "\t";
    
    for(Token token : scope.tokens){
      if(token instanceof  TokenScope){
        printScope((TokenScope)token, depth + 1);
      }
      else{
        System.out.println(tabs + token);
      }
    }
  }

  private Token createTokenFromString(int location, String s){
    try {
      int value = Integer.parseInt(s);
      return new TokenInteger(location, value);
    }catch(Exception e){
      return new TokenString(location, s);
    }
  }
  
  /**
   * Parse / process a scope, will create scopes recursively
   * @param line Current line that is getting parsed
   * @param start Where this scope starts
   * @return
   */
  private TokenScope processScope(String line, int start, char endChar) {
    List<Token> tokens = new ArrayList<>();
    String currentTokenString = "";

    for (int location = start; location < line.length(); location++) {
      char c = line.charAt(location);

      switch (c) {
        //Comment
        case '#':
          TokenScope scope1 = new TokenScope(location);
          scope1.tokens = tokens;
          return scope1;
          
        //separator for ability parts
        case ',':
          tokens.add(createTokenFromString(location, currentTokenString));
          currentTokenString = "";
          tokens.add(new TokenSeparator(location));
          break;
        
        //separates ability parameters
        case ':':
          tokens.add(createTokenFromString(location, currentTokenString));
          currentTokenString = "";
          break;
          
        //start of scope
        case '(':
          TokenScope tScope = processScope(line, location+1, ')');
          tokens.add(tScope);
          location = tScope.endLocation;
          tScope.prefix = currentTokenString;
          currentTokenString = "";
          break;
          
        case '[':
          TokenScope tScope2 = processScope(line, location+1, ']');
          tokens.add(tScope2);
          location = tScope2.endLocation;
          tScope2.prefix = currentTokenString;
          currentTokenString = "";
          break;
          
        case '*':
          tokens.add(createTokenFromString(location, currentTokenString));
          TokenArithmetic tArith = new TokenArithmetic(location, ArithmeticType.MULTIPLICATION);
          tokens.add(tArith);
          currentTokenString = "";
          break;
          
        //add character to token string
        default:
          if(endChar != '\n')
          if(c == endChar){
            tokens.add(createTokenFromString(location, currentTokenString));

            TokenScope scope = new TokenScope(location);
            scope.tokens = tokens;
            return scope;
          }
          currentTokenString += c;
      }
    }
    tokens.add(createTokenFromString(line.length(), currentTokenString));
    TokenScope scope = new TokenScope(line.length());
    scope.tokens = tokens;
    return scope;
  }
}