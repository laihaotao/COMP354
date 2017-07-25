package parser.abilities.Property;

import parser.commons.Formatting;
import parser.tokenizer.TokenStream;
import parser.tokenizer.TokenString;

public class StatusProperty extends Property {

    public TokenString type;

    public StatusProperty(TokenString type) {
        super("status");
        this.type = type;
    }
    
    public static StatusProperty read(TokenStream tokenStream){
        if(tokenStream.validateTokenString("status") != null){
            TokenString type = tokenStream.validateTokenString();
            return new StatusProperty(type);
        }
    
        return null;
    }
    public String toString() {
        return "Status: " + Formatting.toSafeString(type);
    }
}
