package com.lxm.ss.test;

import java.util.Comparator;

/**
 * Created by lxm on 2017/8/11.
 */

public class Student extends Person implements Comparator{

    private String id ;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int compare(Object o1, Object o2) {

        return 0;
    }
    public int compare(Object o1) {
        Student student = (Student) o1;
        return this.id.compareTo(student.getId());
    }

}
