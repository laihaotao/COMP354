/*
 * description:  The abstract class of the Card
 * author(s):    Eric(Haotao) Lai
 * reviewer(s):
 * date:         2017-05-17
 */

package card;

public abstract class Card {

    protected enum CardType{ENERGY, POKEMON, TRAINER,}

    protected String name;
    protected CardType cardType;
    protected String type;


    public String getCardName()
    {
        return name;
    }
    public String getCardType()
    {
        return type;
    }
}
