/*
 * description:  The tool for get files under directory resources
 * author(s):    Eric(Haotao) Lai
 * reviewer(s):
 * date:         2017-05-30
 */

package util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;


public class ResourceReader {

    private final static Logger logger = LogManager.getLogger(ResourceReader.class.getName());

    public static InputStream readFile(String path) {
        return ClassLoader.getSystemClassLoader().getResourceAsStream(path);
    }
}
