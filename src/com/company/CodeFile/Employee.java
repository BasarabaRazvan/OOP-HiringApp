package com.company.CodeFile;

import com.company.CodeFile.Consumer;

import java.util.ArrayList;

public class Employee extends Consumer {
    public String numeleCompaniei;
    private Double salariu;
    public String departament;

    public Employee() {

    }

    public Employee(Resume r, ArrayList<Consumer> consumers) {
        super(r, consumers);
        numeleCompaniei = "Neangajat";
        salariu = 0.0;
        departament = null;
    }

    public Employee(Resume r, ArrayList<Consumer> consumers, String numeleCompaniei, Double salariu) {
        super(r, consumers);
        this.numeleCompaniei = numeleCompaniei;
        this.salariu = salariu;
    }

    public Employee(Resume r, ArrayList<Consumer> consumers, String numeleCompaniei, Double salariu, String departament) {
        super(r, consumers);
        this.numeleCompaniei = numeleCompaniei;
        this.salariu = salariu;
        this.departament = departament;
    }


    public void setNumeleCompaniei(String numeleCompaniei) {
        this.numeleCompaniei = numeleCompaniei;
    }

    public void setSalariu(Double salariu) {
        this.salariu = salariu;
    }

    public Double getSalariu() {
        return salariu;
    }

    @Override
    public String toString() {
        return r.toString()  + "Employee:\n" +
                "\tNumele companiei:\t" + numeleCompaniei + "\n" +
                "\tSalariu:\t" + salariu + "\n\n";
    }
}

