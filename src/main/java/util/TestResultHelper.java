package util;


import java.io.*;
import java.util.ArrayList;

/**
 * Created by Eric on 5/30/2017.
 */
public class TestResultHelper {


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
