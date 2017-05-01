package les11.logic.dto;

import java.util.*;

import les11.logic.controller.*;
import les11.logic.dao.*;
import les11.logic.exception.*;
import les11.logic.mysql.*;

public class Student {
    private int id;
    private String name;
    private String surname;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getSurname () {
        return surname;
    }

    public void setSurname (String surname) {
        this.surname = surname;
    }

    public String toString() {
        return "[Student's name: " + this.name + " " + this.surname + "]";
    }
}