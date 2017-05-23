package parser.abilities.inter;

/**
 * Created by frede on 2017-05-22.
 */
public class TokenSeparator extends Token{

  public TokenSeparator(int endLocation) {
    super(endLocation);
  }
  
  public String toString(){
      return super.toString() + "Separator -> --------------";
  }
}
