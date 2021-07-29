package com.company.CodeFile;

import com.company.Application.Application;

import java.util.ArrayList;
import java.util.Collections;

public class Recruiter extends Employee {
    Double rating;

    public Recruiter(Resume r, ArrayList<Consumer> consumers, String numeleCompaniei, Double salariu) {
        super(r, consumers, numeleCompaniei, salariu);
        rating = 5.0;
    }

    public Recruiter(Resume r, ArrayList<Consumer> consumers, String numeleCompaniei, Double salariu, Double rating) {
        super(r, consumers, numeleCompaniei, salariu);
        this.rating = rating;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    //calculam scorul userului, incrementam valoarea raitingului recruterului, vedem daca userul indeplineste
    //requirements urile jobului respectiv, si adaugam requestul in lista managerului
    public int evaluate(Job job, User user) {
        double scor = this.getRating() * user.getTotalScore();
        setRating(rating + 0.1);
        if(!job.meetsRequirments(user))
            return 0;
        Request<Job, User> r = new Request(job,user,this,scor);
        Application.getInstance().getCompany(job.numeCompanie).manager.add(r);
        Collections.sort(Application.getInstance().getCompany(job.numeCompanie).manager.requests);
        if(scor - (int)scor >= 0.5) {
            return (int)scor + 1;
        } else
            return (int)scor;
    }

}
