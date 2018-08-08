package com.lam;

import java.util.Random;

/**
 * Global controls the global variables and methods for the system.
 */
public class Global {
    // random is the global random generator.
    public static Random random = new Random();

    /**
     * Global constructor.  Private, so unable to instantiate.
     */
    private Global() {
    }
}
