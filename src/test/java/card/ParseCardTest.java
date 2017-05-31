package card;

import card.abilities.Ability;
import card.pokemon.PokemonCard;
import org.junit.Test;
import parser.cards.CardParser;
import parser.cards.EnergyCost;
import util.TestResultHelper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by ERIC_LAI on 2017-05-29.
 */
public class ParseCardTest {

    @Test
    public void parseCard() throws IOException {
        ArrayList<String> expected = TestResultHelper.readResultFile("ParseCardResult.txt");
        CardParser cardParser = new CardParser("cards.txt");

        HashMap<Integer, Card> map = cardParser.getCardMap();

        int j = 0;
        for (int i = 1; i <= map.size(); i++) {
            while (!map.containsKey(i)) i++;
            String name = map.get(i).getCardName();
            String expectedStr = expected.get(j++);
            assertEquals(expectedStr, name);
        }
    }

    @Test
    public void buildCardResult() throws IOException {
        File file = new File("src/main/resources/test/PokemonCardAbilitiesCost.txt");
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));

        CardParser cardParser = new CardParser("cards.txt");
        HashMap<Integer, Card> map = cardParser.getCardMap();

        for (int i = 1; i <= map.size(); i++) {
            while (!map.containsKey(i)) i++;
            Card card = map.get(i);
            if (card instanceof PokemonCard) {
                PokemonCard pcd = (PokemonCard) card;
                ArrayList<Ability> abilities = (ArrayList<Ability>) pcd.getAbilities();

                bw.write(pcd.getCardName() + " {" + '\n');
                for (Ability a : abilities) {
                    EnergyCost cost = a.getEnergyCost();
                    String name = a.getTemplate().name;
                    bw.write(name + ": " + cost.toString() + '\n');
                }
                bw.write(";" + '\n');

                EnergyCost retreatEnergyCost = pcd.getRetreatEnergyCost();
                bw.write("retreat: " + retreatEnergyCost.toString() + '\n');
                bw.write("};" + '\n');

            }
        }

        bw.close();
    }
}
