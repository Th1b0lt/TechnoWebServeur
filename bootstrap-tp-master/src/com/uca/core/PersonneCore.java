package com.uca.core;

import com.uca.dao.PersonneDao;
import com.uca.entity.PersonneEntity;

import java.util.ArrayList;

public class PersonneCore {

    public static ArrayList<PersonneEntity> getAllPersonnes() {
        return new PersonneDao().getAllPersonnes();
    }

}
