package parser.abilities.filters;

import parser.abilities.properties.TargetProperty;

public class FilterEvolveFrom extends Filter {

    private TargetProperty target;

    public FilterEvolveFrom(TargetProperty target){

        this.target = target;
    }
    
    public String toString(){
        return "filters evolve-from "+target;
    }
}
