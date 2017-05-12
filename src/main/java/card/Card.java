package card;

/**
 * Created by ERIC_LAI on 2017-05-06.
 */
public abstract class Card {

    protected enum CardType{ENERGY, POKEMON, TRAINER,}

    protected String name;
    protected CardType cardType;
}
