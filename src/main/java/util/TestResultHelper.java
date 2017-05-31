package util;


import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by Eric on 5/30/2017.
 */
public class TestResultHelper {


    public static boolean compareTwoFiles(File e, File a) throws IOException {
        BufferedReader expectedReader = new BufferedReader(new FileReader(e));
        BufferedReader actualReader = new BufferedReader(new FileReader(a));

        boolean res = true;
        String expected = "";
        String actual = "";
        do {
            res &= Objects.equals(expected, actual);

            if (!res) {
                System.err.println("expected: " + expected);
                System.err.println("actual: " + actual);
            }

            expected = expectedReader.readLine();
            actual = actualReader.readLine();
        } while (expected != null);

        expectedReader.close();
        actualReader.close();

        return res;
    }

    public static ArrayList<String> readResultFile(String path) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        File resultFile = ResourceReader.readFile("test/" + path);

        BufferedReader br = new BufferedReader(new FileReader(resultFile));
        String line;

        while ((line = br.readLine()) != null) {
            list.add(line);
        }

        return list;
    }
}
