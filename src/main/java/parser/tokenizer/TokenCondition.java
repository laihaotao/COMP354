package parser.tokenizer;

/**
 * Used to delimit arithmetics
 */
public class TokenCondition extends Token{

    public final ArithmeticType type;

    public Token leftValue, rightValue;

    public TokenCondition(int endLocation, ArithmeticType type, Token leftValue, Token rightValue) {
        super(TokenType.ARITHMETIC, endLocation);
        this.type = type;
        this.leftValue = leftValue;
        this.rightValue = rightValue;
    }

    public String toString(){
        return super.toString() + "Condition -> "+ leftValue + " " + type + " " + rightValue;
    }

    @Override
    public String getDisplayString() {
        String string = leftValue.getDisplayString();
        switch(type){
            case MULTIPLICATION:
                string += "*";
                break;
            default:
                return " ";
        }

        return string + rightValue.getDisplayString();
    }


}