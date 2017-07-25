package parser.tokenizer;

import game.GameBoard;
import game.Player;

/**
 * Used to delimit arithmetics
 */
public class TokenCondition extends Token{

    public final OperatorType type;

    public Token leftValue, rightValue;

    public TokenCondition(int endLocation, OperatorType type, Token leftValue, Token rightValue) {
        super(TokenType.OPERATOR, endLocation);
        this.type = type;
        this.leftValue = leftValue;
        this.rightValue = rightValue;
    }

    public String toString(){
        return super.toString() + "Condition -> "+ leftValue + " " + type + " " + rightValue;
    }
    
    public int evaluateAsExpression(GameBoard targetBoard, Player callingPlayer) {
        switch(type){
            case GREATER:
                return (leftValue.evaluateAsExpression(targetBoard, callingPlayer) > rightValue.evaluateAsExpression(targetBoard, callingPlayer)?1:0);
            default:
                return 0;
        }
    }
    
    @Override
    public String getDisplayString() {
        String string = leftValue.getDisplayString();
        switch(type){
            case GREATER:
                string += ">";
                break;
            default:
                return " ";
        }

        return string + rightValue.getDisplayString();
    }


}