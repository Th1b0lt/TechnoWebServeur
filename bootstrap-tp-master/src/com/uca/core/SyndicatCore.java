package com.uca.core;

public class SyndicatCore {
    public static ArrayList<SyndicatEntity> getAllSyndicat() {
        return new SyndicatDAO().getAllSyndicat();
    }
}
