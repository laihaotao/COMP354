/*
 * description:  This class is used for testing
 * author(s):    Eric(Haotao) Lai
 * reviewer(s):
 * date:         2017-04-08
 */

package util;


import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public class TestResultHelper {


    /**
     * compare two file
     * @param e expected file
     * @param a actual file
     * @return true, if two file are the same; otherwise, false;
     * @throws IOException
     */
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

    /**
     * Read a file and return a list contains each line's string as element
     * @param path the file's path
     * @return
     * @throws IOException
     */
    public static ArrayList<String> readResultFile(String path) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        InputStream is = ResourceReader.readFile("test/" + path);

        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line;

        while ((line = br.readLine()) != null) {
            list.add(line);
        }

        return list;
    }
}
