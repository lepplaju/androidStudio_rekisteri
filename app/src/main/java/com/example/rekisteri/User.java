package com.example.rekisteri;


public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String degreeProgram;

    private int image;

    public User(String firstName, String lastName, String email, String degreeProgram, int image) {
        this.degreeProgram = degreeProgram;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.image = image;
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
}
