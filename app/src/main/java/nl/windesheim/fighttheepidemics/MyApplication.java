package nl.windesheim.fighttheepidemics;

import android.app.Application;

/**
 * Created by gideonhessels on 10-12-15.
 */

    public class MyApplication extends Application {

        private int chance;

        public int getChance() {
            return chance;
        }

        public void setChance(int chance) {
            this.chance = chance;
        }
    }

