package com.example.rekisteri;

import android.content.Context;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
        // palautetaan käyttäjälista aakkosjärjestyksessä
        Collections.sort(users, Comparator.comparing(User::getLastName).thenComparing(User::getFirstName));
        return users;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void saveUsers(Context context) {
        try {
            ObjectOutputStream fileWriter = new ObjectOutputStream(context.openFileOutput("users.data", Context.MODE_PRIVATE));
            fileWriter.writeObject(users);
            fileWriter.close();
            System.out.println("Tallentaminen onnistui!");
        } catch (IOException e) {
            System.out.println("Käyttäjien tallentamisessa tapahtui virhe!");
        }

    }

    public void loadUsers(Context context) {
        try {
            ObjectInputStream fileReader = new ObjectInputStream(context.openFileInput("users.data"));
            users = (ArrayList<User>) fileReader.readObject();
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Käyttäjien lukemisessa tapahtui virhe!");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Käyttäjien lukemisessa tapahtui virhe!");
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            System.out.println("Käyttäjien lukemisessa tapahtui virhe!");
            e.printStackTrace();
        }

    }


}
