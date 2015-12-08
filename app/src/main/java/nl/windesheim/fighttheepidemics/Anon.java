package nl.windesheim.fighttheepidemics;

/**
 * Created by Admin on 02/12/2015.
 */
public class Anon {
    private static Anon ourInstance = new Anon();

    public static Anon getInstance() {
        if (ourInstance == null){
            ourInstance = new Anon();
        }
        return ourInstance;
    }

    private Anon() {
    }

}
