package unit;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;


/**
 * Author:   Eric(Haotao) Lai
 * Date:     ${Date}
 * E-mail:   haotao.lai@gmail.com
 * Website:  http://laihaotao.me
 */
public class SelectDeckTest {

    @Test
    public void test() throws IOException {
        File dir = new File("/Users/ERIC_LAI/IdeaProjects/COMP354/target/COMP354-1.0-SNAPSHOT-jar-with-dependencies.jar!/decks/");
        if (dir.isDirectory()) {
            for (File f : dir.listFiles()) {
                System.out.println(f.getName());
            }
        }else {
            Enumeration<JarEntry> entries = new JarFile
                    ("/Users/ERIC_LAI/IdeaProjects/COMP354/target/COMP354-1.0" +
                    "-SNAPSHOT-jar-with-dependencies.jar").entries();


        }
    }
}
