package parser.abilities.properties;

import parser.tokenizer.TokenStream;
import parser.tokenizer.TokenString;

public class TriggerProperty extends Property {

    public TargetProperty target;
    
    public TriggerProperty(TargetProperty target) {
        super("Trigger");
        
        this.target = target;
    }

    public static TriggerProperty read(TokenStream tokenStream){

        if(tokenStream.validateTokenString("trigger") != null){
            
            TokenString target = tokenStream.validateTokenString();
            TokenString mod = tokenStream.validateTokenString();
            return new TriggerProperty(new TargetProperty(target, mod));
        }
        return null;
    }
}
