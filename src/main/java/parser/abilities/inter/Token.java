package parser.abilities.inter;

/**
 * This symbolizes a token in the language
 */
public abstract class Token {
  
  public final int endLocation;
  
  public Token(int endLocation){
    this.endLocation = endLocation;
  }
  
  public String toString(){
    return "@"+endLocation + " token:";
  }
}
