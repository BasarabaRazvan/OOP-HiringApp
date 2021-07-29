package com.company.CodeFile;

import com.company.Application.Application;

import java.util.ArrayList;

public class Manager extends Employee {
    public ArrayList<Request<Job, User>> requests;

    public Manager() {
        requests = new ArrayList<>();
    }

    public Manager(ArrayList<Request<Job, User>> requests) {
        this.requests = requests;
    }

    public Manager(Resume r, ArrayList<Consumer> consumers, String numeleCompaniei, Double salariu,
                   ArrayList<Request<Job, User>> requests) {
        super(r, consumers, numeleCompaniei, salariu);
        this.requests = requests;
    }

    public void setRequests(ArrayList<Request<Job, User>> requests) {
        this.requests = requests;
    }

    public void add(Request<Job, User> request) {
        if(requests == null) {
            ArrayList<Request<Job, User>> r = new ArrayList<>();
            r.add(request);
            setRequests(r);
            return;
        }
        if(requests.contains(request))
            return;
        requests.add(request);
    }

    //Parcurgem lista de requesturi care sunt deja sortate dupa scorul maxim si le selectam doar pe acelea care
    //corespund jobului. Apoi am verificat daca jobul mai are locuri disponibile, iar daca mai sunt atunci verificam
    //si daca userul a fost deja angajat (daca nu il convertim in employee, scadem nr de locuri disponibile si il
    //in departamentul potrivit).
    //Daca numarul de canditati ajunge la zero, inseamna ca jobul a fost inchis si ca trebuie sa notificam toti
    //angajati.
    //Dupa ce adaug un user in job, il sterg din lista de obeserveri pentru ai putea notifica pe ceilalti daca au
    //fost cumva respinsi.
    public void process(Job job) {
        ArrayList<User> usersForThis = new ArrayList<>();
        if(requests != null) {
            for (Request<Job, User> r : requests) {
                if (r.getKey().numeJob.equals(job.numeJob) &&
                        r.getKey().numeCompanie.equals(job.numeCompanie)) {
                    usersForThis.add(r.getValue1());
                }
            }

            for (User u : usersForThis) {
                if (job.nrCanditati == 0) {
                    job.status = true;
                    Application.getInstance().getCompany(job.numeCompanie).notifyAllObservers("Jobul a fost inchis!");
                    break;
                }
                if (Application.getInstance().getUsers().contains(u)) {
                    Application.getInstance().getCompany(job.numeCompanie).removeObserver(u);
                    Employee e = u.convert();
                    e.setSalariu(job.salariu);
                    e.setNumeleCompaniei(job.numeCompanie);
                    Application.getInstance().remove(u);
                    job.nrCanditati--;
                    Company c = Application.getInstance().getCompany(job.numeCompanie);
                    for (Department d : c.departaments) {
                        if(d.jobs != null) {
                            if (d.jobs.contains(job)) {
                                d.add(e);
                            }
                        }
                    }
                }
            }
            if(job.nrCanditati == 0) {
                job.status = true;
                Application.getInstance().getCompany(job.numeCompanie).notifyAllObservers("Jobul a fost inchis!");
            }
        }

        Application.getInstance().getCompany(job.numeCompanie).notifyAllObservers("Ai fost respins! :(");
    }

}
