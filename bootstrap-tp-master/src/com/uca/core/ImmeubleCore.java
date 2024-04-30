package com.uca.core;

public class ImmeubleCore {
    public static ArrayList<ImmeubleEntity> getAllImmeuble() {
        return new ImmeubleDAO().getAllImmeuble();
    }
}
