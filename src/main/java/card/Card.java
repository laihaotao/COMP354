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
    public String getCardType()
    {
        return type;
    }

    public void setSelected(boolean selected){
        isSelected = selected;
    }

    public boolean getSelected(){
        return isSelected;
    }

	public Object deepClone() throws IOException, ClassNotFoundException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(bos);

		oos.writeObject(this);

		ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
		ObjectInputStream ois = new ObjectInputStream(bis);

		return ois.readObject();
	}

	public static String getAbilityNameInFileAt(int lineID) throws IOException{
		
		String abilityName = "";
		
		
		FileReader abilities = new FileReader("src/main/resources/abilities.txt");
		//Create Scanner to read deck.txt files
		BufferedReader reader = new BufferedReader(abilities);
		
		String line = "";
		
		//Will split the line so that we have the abilityName on the first part, and the rest in the second part.
		String [] lineParts = new String [2];
		
		
		//int lineCount = 0;
		//lineCount++;
		
		//the Reader will keep reading the file until we find the line that we are looking for which is identified as the lineID.
		for (int i=0; i<lineID;i++){
			line = reader.readLine();
		
			
			if (line == null){
				System.out.println("Line ID is not found");
				break;
			}
			
		}
		
		lineParts = line.split(":");
		abilityName = lineParts[0];
		
		
		return abilityName;
		
	}
	
	
	
	public static String getAbilityNameInFileAt(String lineID) throws IOException{
		
		
		int intID = Integer.parseInt(lineID);
		
		String abilityName = "";
		
		FileReader abilities = new FileReader("src/main/resources/abilities.txt");
		//Create Scanner to read deck.txt files
		BufferedReader reader = new BufferedReader(abilities);
		
		String line = "";
		
		//Will split the line so that we have the abilityName on the first part, and the rest in the second part.
		String [] lineParts = new String [2];
		

		
		//the Reader will keep reading the file until we find the line that we are looking for which is identified as the lineID.
		for (int i=0; i<intID;i++){
			line = reader.readLine();
		
			
			if (line == null){
				System.out.println("Line ID is not found");
				break;
			}
			
		}
		
		lineParts = line.split(":");
		abilityName = lineParts[0];
		
		reader.close();
		return abilityName;
		
	}
    
    
    
    
}
