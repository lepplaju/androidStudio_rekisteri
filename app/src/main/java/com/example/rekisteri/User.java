package com.example.rekisteri;


import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class User implements Serializable {
    private String firstName;
    private String lastName;
    private String email;
    private String degreeProgram;

    private int image;

    private ArrayList<String> tutkinnot;

    public User(String firstName, String lastName, String email, String degreeProgram, int image) {
        this.degreeProgram = degreeProgram;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.image = image;
        tutkinnot = new ArrayList<>();
    }

    public User(String firstName, String lastName, String email, String degreeProgram, int image, ArrayList<String> tutkinnot) {
        this.degreeProgram = degreeProgram;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.image = image;
        this.tutkinnot = tutkinnot;
    }

    public int getImage() {
        return image;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getDegreeProgram() {
        return degreeProgram;
    }

    public ArrayList<String> getTutkinnot() {return tutkinnot;}
}
