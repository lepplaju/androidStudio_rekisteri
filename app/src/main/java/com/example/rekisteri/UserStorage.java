package com.example.rekisteri;

import java.util.ArrayList;

public class UserStorage {

    private ArrayList<User> users = new ArrayList<>();
    private static UserStorage storage = null;

    private UserStorage() {

    }



    public static UserStorage getInstance() {
        if (storage == null) {
            storage = new UserStorage();
        }
        return storage;
    }
    public ArrayList<User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        users.add(user);

    }

}
