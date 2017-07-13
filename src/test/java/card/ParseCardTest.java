package card;

import entry.Config;
import org.junit.Test;
import parser.cards.CardParser;
import parser.cards.EnergyCost;
import util.TestResultHelper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by ERIC_LAI on 2017-05-29.
 */
public class ParseCardTest {

    @Test
    public void testParseCard() throws IOException {
        File expected = new File("src/main/resources/test/CorrectCostFile.txt");
        File output = new File("src/main/resources/output/PokemonCardAbilitiesCost.txt");
        buildCardResult(output.getPath());

        boolean res = TestResultHelper.compareTwoFiles(expected, output);
        assertEquals(true, res);
    }


    public void buildCardResult() throws IOException {
        File output = new File("src/main/resources/output/PokemonCardAbilitiesCost.txt");
        buildCardResult(output.getPath());
    }

    private void buildCardResult(String path) throws IOException {
        File file = new File(path);
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));

        CardParser cardParser = new CardParser(Config.FILE_PATH_CARDS_TXT);
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

    private void generateNoPokemonDeckFile() {
        String path = "src/main/resources/deck_no_pokemon.txt";
        File file = new File(path);
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));

            int i = 0;
            // card index 25 refer to an energy card
            while (i++ < 60) {
                bw.write("25" + '\n');
            }

            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
