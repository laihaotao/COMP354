package game;

import java.util.Random;

/**
 * Created by ERIC_LAI on 2017-05-31.
 */
public class Coin {

    public enum CoinStatus {Head, Tail,}

    public static CoinStatus flip() {
        int randomNum =  new Random().nextInt();
        if (randomNum % 2 == 0) return CoinStatus.Head;
        else return CoinStatus.Tail;
    }
}
