package com.uca.core;


import com.uca.dao.ImmeubleDao;
import com.uca.entity.ImmeubleEntity;

import java.util.ArrayList;

public class ImmeubleCore {
    public static ArrayList<ImmeubleEntity> getAllImmeuble() {
        try{
            return new ImmeubleDAO().getAllImmeuble();
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static ImmeubleEntity create(){
        try{
            ImmeubleEntity newImmeuble= new ImmeubleEntity();
            newImmeuble.set
        }
    }
}
