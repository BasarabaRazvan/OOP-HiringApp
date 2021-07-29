package com.company.CodeFile;

import com.company.Application.Application;

import java.util.ArrayList;

public abstract class Department {
    private ArrayList<Employee> employees;
    public ArrayList<Job> jobs;

    public Department() {
        employees = new ArrayList<>();
        jobs = new ArrayList<>();
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }

    public void setJobs(ArrayList<Job> jobs) {
        this.jobs = jobs;
    }

    public Department(ArrayList<Employee> employees, ArrayList<Job> jobs) {
        this.employees = employees;
        this.jobs = jobs;
    }

    public ArrayList<Job> getJobs() {
        ArrayList<Job> freeJobs = new ArrayList<>();
        if(jobs != null) {
            for (Job it : jobs) {
                if (!it.status)
                    freeJobs.add(it);
            }
            return freeJobs;
        }
        return null;
    }

    public void add(Employee employee) {
        if(employees == null) {
            ArrayList<Employee> e = new ArrayList<>();
            e.add(employee);
            setEmployees(e);
            return;
        }
        if(employees.contains(employee))
            return;
        employees.add(employee);
    }

    public void remove(Employee employee) {
        employees.remove(employee);
    }

    public void add(Job job) {
        if(jobs == null) {
            ArrayList<Job> j = new ArrayList<>();
            j.add(job);
            setJobs(j);
            if(Application.getInstance().getCompany(job.numeCompanie) != null)
                 Application.getInstance().getCompany(job.numeCompanie).notifyAllObservers("A fost adaugat un job nou!");
            return;
        }
        if(jobs.contains(job))
            return;
        jobs.add(job);
        if(Application.getInstance().getCompany(job.numeCompanie) != null)
            Application.getInstance().getCompany(job.numeCompanie).notifyAllObservers("A fost adaugat un job nou!");
    }


    public abstract double getTotalSalaryBudget();

}
