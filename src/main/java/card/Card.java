/*
 * description:  The abstract class of the Card
 * author(s):    Eric(Haotao) Lai
 * reviewer(s):
 * date:         2017-05-17
 */

package card;

import java.io.*;
import java.util.BitSet;

public abstract class Card implements Serializable{

    protected enum CardType{ENERGY, POKEMON, TRAINER,}

    protected String name;
    protected CardType cardType;
    protected String type;

    private boolean isSelected = false;

    public String getCardName()
    {
        return name;
    }
    public CardType getCardType()
    {
        return cardType;
    }
    
    public String getType(){
    	return cardType.name();
    }

    public void setSelected(boolean selected){
        isSelected = selected;
    }

    public boolean getSelected(){
        return isSelected;
    }

//	public Object deepClone() throws IOException, ClassNotFoundException {
//		ByteArrayOutputStream bos = new ByteArrayOutputStream();
//		ObjectOutputStream oos = new ObjectOutputStream(bos);
//
//		oos.writeObject(this);
//
//		ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
//		ObjectInputStream ois = new ObjectInputStream(bis);
//
//		return ois.readObject();
//	}
	
	public abstract Card copy();
    
}
