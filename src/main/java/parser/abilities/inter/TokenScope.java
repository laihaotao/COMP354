package parser.abilities.inter;

import java.util.ArrayList;
import java.util.List;

/**
 * This token symbolizes a scope made of multiple other tokens
 */
public class TokenScope extends Token {

  public List<Token> tokens;
  public String prefix;
  public TokenScope(int endLocation) {
    super(endLocation);
    
    tokens = new ArrayList<>();
  }
}
