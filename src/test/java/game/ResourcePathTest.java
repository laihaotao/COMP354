package game;

import org.junit.Test;
import ui.popup.GamePopup;

import java.net.URL;

/**
 * Created by ERIC_LAI on 2017-06-04.
 */
public class ResourcePathTest {

    @Test
    public void getResPath() {
        URL location = ClassLoader.getSystemClassLoader().getResource("");
        System.out.println(location);
    }
}
