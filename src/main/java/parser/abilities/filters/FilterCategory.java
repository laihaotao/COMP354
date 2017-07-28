package parser.abilities.filters;

public class FilterCategory extends Filter{
    private String category;
    
    public FilterCategory(String category){

        this.category = category;
    }

    public String toString(){
        return "Filters cards of type "+category;
    }
}
