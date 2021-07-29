package com.company.CodeFile;

import com.company.CodeFile.Interfaces.Observer;

import java.util.ArrayList;

public class User extends Consumer implements Observer {
    public ArrayList<String> numeCompani;

    public User() {
        numeCompani = new ArrayList<>();
    }

    public User(Resume r, ArrayList<Consumer> consumers) {
        super(r, consumers);
    }

    public User(Resume r, ArrayList<Consumer> consumers, ArrayList<String> numeCompani) {
        super(r, consumers);
        this.numeCompani = numeCompani;
    }

    public Employee convert() {
       return new Employee(this.r,this.consumers);
    }

    public Double getTotalScore() {
        return 1.5 * totalExperience() + meanGPA();
    }

    @Override
    public String toString() {
        return  r.toString();
    }

    @Override
    public void update(String text) {
    }
}