package parser.abilities.inter;

/**
 * Created by frede on 2017-05-22.
 */
public class TokenString extends Token{
  
  private String str;
  
  public TokenString(int endLocation, String str){
    super(endLocation);
    this.str = str;
  }
  
  public String toString(){
    return super.toString() + "String -> "+str;
  }

}
