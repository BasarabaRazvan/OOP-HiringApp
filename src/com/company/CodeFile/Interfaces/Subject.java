package com.company.CodeFile.Interfaces;

import com.company.CodeFile.User;

import java.util.ArrayList;

public interface Subject {
    ArrayList<Observer<User>> arr = new ArrayList<>();

    void addObserver(User user);

    void removeObserver(User user);

    void notifyAllObservers(String text);
}
