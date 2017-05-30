/*
 * description:  The tool for get files under directory resources
 * author(s):    Eric(Haotao) Lai
 * reviewer(s):
 * date:         2017-05-30
 */

package util;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by ERIC_LAI on 2017-05-30.
 */
public class ResourceReader {

    public static File readFile(String path) throws FileNotFoundException {
        ClassLoader cl = ClassLoader.getSystemClassLoader();
        try {
            return new File(cl.getResource(path).getFile());
        } catch (NullPointerException e) {
            throw new FileNotFoundException(path);
        }
    }
}
