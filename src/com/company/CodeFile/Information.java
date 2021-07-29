package com.company.CodeFile;

import java.util.ArrayList;

public class Information {
    private final String nume;
    private final String prenume;
    private String email;
    private String telefon;
    private String dataNastere;
    private String sex;
    private ArrayList<Limba> limbi;

    public Information() {
        nume = new String();
        prenume = new String();
        email = new String();
        telefon = new String();
        dataNastere = new String();
        sex = new String();
        limbi = new ArrayList<>();
    }

    public Information(String nume, String prenume, String email, String telefon, String dataNastere,
                       String sex, ArrayList<Limba> limbi) {
        this.nume = nume;
        this.prenume = prenume;
        this.email = email;
        this.telefon = telefon;
        this.dataNastere = dataNastere;
        this.sex = sex;
        this.limbi = limbi;
    }

    public String getNume() {
        return nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefon() {
        return telefon;
    }

    public String getDataNastere() {
        return dataNastere;
    }

    public String getSex() {
        return sex;
    }

    public ArrayList<Limba> getLimbi() {
        return limbi;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Limba ll : limbi)
            s.append("\t\t").append(ll.l).append(" -> ").append(ll.nivel).append("\n");
        return  "\tName: " + nume + " " + prenume + "\n"
                + "\tEmail: " + email + "\n"
                + "\tPhone: " + telefon + "\n"
                + "\tGenre: " + sex + "\n" + "\tLanguages:\n"
                + s;
    }
}
