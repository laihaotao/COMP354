package parser.commons;

public class FilterPokemon extends Filter{
    public String category = null;

    public void setCategory(String category){
        this.category = category;
    }

    public String toString(){
        return "filter pokemons " + ((category!=null)?" of cat "+category:"");
    }

}
