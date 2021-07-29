package com.company.CodeFile;

import com.company.Application.Application;

import java.util.ArrayList;

public class Job {
    public String numeJob;
    public String numeCompanie;
    public boolean status;
    Constraint<Integer> anAbsolvire;
    Constraint<Integer> aniExperienta;
    Constraint<Double> mediaAcademica;
    ArrayList<User> canditati;
    public int nrCanditati;
    public double salariu;
    public String departament;

    public Job() {
        numeJob = new String();
        numeCompanie = new String();
        status = false;
        anAbsolvire = new Constraint<>();
        aniExperienta = new Constraint<>();
        mediaAcademica = new Constraint<>();
        canditati = new ArrayList<>();
        nrCanditati = 0;
        salariu = 0.0;;
    }

    public Job(String numeJob, String numeCompanie, boolean status, Constraint<Integer> anAbsolvire,
               Constraint<Integer> aniExperienta, Constraint<Double> mediaAcademica, ArrayList<User> canditati,
               int nrCanditati, double salariu) {
        this.numeJob = numeJob;
        this.numeCompanie = numeCompanie;
        this.status = status;
        this.anAbsolvire = anAbsolvire;
        this.aniExperienta = aniExperienta;
        this.mediaAcademica = mediaAcademica;
        this.canditati = canditati;
        this.nrCanditati = nrCanditati;
        this.salariu = salariu;
    }

    public Job(String numeJob, String numeCompanie, boolean status, Constraint<Integer> anAbsolvire,
               Constraint<Integer> aniExperienta, Constraint<Double> mediaAcademica, ArrayList<User> canditati,
               int nrCanditati, double salariu, String departament) {
        this.numeJob = numeJob;
        this.numeCompanie = numeCompanie;
        this.status = status;
        this.anAbsolvire = anAbsolvire;
        this.aniExperienta = aniExperienta;
        this.mediaAcademica = mediaAcademica;
        this.canditati = canditati;
        this.nrCanditati = nrCanditati;
        this.salariu = salariu;
        this.departament = departament;
    }

    public void apply(User user) {
        if(!status) {
            Company c = Application.getInstance().getCompany(this.numeCompanie);
            if(c != null) {
                Recruiter r = c.getRecruiter(user);
                int score = r.evaluate(this, user);
                c.addObserver(user);
            }
        }
    }

    public boolean meetsRequirments(User user) {
        int experienta = aniExperienta.inInterval((double)user.totalExperience());
        int absolvire = anAbsolvire.inInterval((double)user.getGraduationYear());
        double medie = mediaAcademica.inInterval(user.meanGPA());
        if(experienta * absolvire * medie == 1)
            return true;
        else
            return false;
    }

    @Override
    public String toString() {
        return  "\nNume Job:\t" + numeJob + '\n' +
                "Nume Companie:\t" + numeCompanie + '\n' +
                "Status:\t" + status + "\n" +
                "An Absolvire:\n" + anAbsolvire +
                "\nAni Experienta:\n" + aniExperienta +
                "\nMedia Academica:\n" + mediaAcademica +
                "\nCanditati:\t" + canditati +
                "\nNr canditati:\t" + nrCanditati +
                "\nSalariu:\t" + salariu + "\n  ";
    }
}

