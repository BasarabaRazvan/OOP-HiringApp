package com.company.Application;

import com.company.CodeFile.*;

import java.util.ArrayList;
import java.util.List;

public class Application {
    private static Application application = null;

    private ArrayList<Company> companies;
    private ArrayList<User> users;

    public void setCompanies(ArrayList<Company> companies) {
        this.companies = companies;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }


    public ArrayList<User> getUsers() {
        return users;
    }

    public ArrayList<Company> getCompanies() {
        return companies;
    }

    private Application() {
        companies = new ArrayList<>();
        users = new ArrayList<>();
    }

    private Application(ArrayList<Company> companies, ArrayList<User> users) {
        this.companies = companies;
        this.users = users;
    }

    public static Application getInstance() {
        if(application == null) {
            application = new Application();
        }
        return application;
    }

    public Company getCompany(String name) {
        for (Company c : companies) {
            if(c.name.equals(name))
                return c;
        }
        return null;
    }

    public void add(Company company) {
        if(companies == null) {
            ArrayList<Company> c = new ArrayList<>();
            c.add(company);
            setCompanies(c);
            return;
        }
        if(companies.contains(company))
            return;
        companies.add(company);
    }

    public void add(User user) {
        if(users == null) {
            ArrayList<User> u = new ArrayList<>();
            u.add(user);
            setUsers(u);
            return;
        }
        if(users.contains(user))
            return;
        users.add(user);
    }

    public boolean remove(Company company) {
        if(companies.contains(company)) {
            companies.remove(company);
            return true;
        }
        return false;
    }

    public boolean remove(User user) {
        if(users.contains(user)) {
            users.remove(user);
            return true;
        }
        return false;
    }

    public ArrayList<Job> getJobs(List<String> companies) {
        ArrayList<Job> freeJobs = new ArrayList<>();
        for (String it : companies) {
            Company c = getCompany(it);
            if(c != null) {
                ArrayList<Job> free;
                free = c.getJobs();
                freeJobs.addAll(free);
            }
        }
        return freeJobs;
    }

    public Consumer findName(String name) {
        for (Company company : Application.getInstance().getCompanies()) {
            if(company.departaments != null) {
                for (Department d : company.departaments) {
                    if(d.getEmployees() != null) {
                        for (Employee e : d.getEmployees()) {
                            String string = e.r.getInformation().getNume() + " " + e.r.getInformation().getPrenume();
                            if(string.equals(name))
                                return e;
                        }
                    }
                }
            }

            String string = company.manager.r.getInformation().getNume() + " "
                    + company.manager.r.getInformation().getPrenume();
            if(string.equals(name))
                return company.manager;
        }

        for (User user : Application.application.getUsers()) {
            String string = user.r.getInformation().getNume() + " " + user.r.getInformation().getPrenume();
            if (string.equals(name))
                return user;
        }
        return null;
    }
}
