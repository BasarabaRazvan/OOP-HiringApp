package com.company.CodeFile;

import com.company.CodeFile.Interfaces.Observer;
import com.company.CodeFile.Interfaces.Subject;

import java.util.ArrayList;

public class Company implements Subject {
    public String name;
    public Manager manager;
    public ArrayList<Department> departaments;
    ArrayList<Recruiter> recruiters;

    public Company() {
        name = new String();
        manager = new Manager();
        departaments = new ArrayList<>();
        recruiters = new ArrayList<>();
    }

    public Company(String name, Manager manager, ArrayList<Department> departaments, ArrayList<Recruiter> recruiters) {
        this.name = name;
        this.manager = manager;
        this.departaments = departaments;
        this.recruiters = recruiters;
    }

    public void setDepartaments(ArrayList<Department> departaments) {
        this.departaments = departaments;
    }

    public void setRecruiters(ArrayList<Recruiter> recruiters) {
        this.recruiters = recruiters;
    }

    public void add(Department department) {
        if(departaments == null) {
            ArrayList<Department> d = new ArrayList<>();
            d.add(department);
            setDepartaments(d);
            return;
        }
        if(departaments.contains(department))
            return;
        departaments.add(department);
    }

    public void add(Recruiter recruiter) {
        if(recruiters == null) {
            ArrayList<Recruiter> r = new ArrayList<>();
            r.add(recruiter);
            setRecruiters(r);
            return;
        }
        if(recruiters.contains(recruiter))
            return;
        recruiters.add(recruiter);
    }

    public void add(Employee employee, Department department) {
        department.add(employee);
    }

    public void remove(Employee employee) {
        if(manager.equals(employee))
            manager = null;
        for (Department d : departaments) {
            if(d.getEmployees() != null) {
                if (d.getEmployees().contains(employee)) {
                    d.getEmployees().remove(employee);
                    break;
                }
            }
        }
    }

    public void remove(Department department) {
        if (departaments.contains(department)) {
            department.setEmployees(null);
            department.setJobs(null);
            departaments.remove(department);
        }
    }

    public void remove(Recruiter recruiter) {
        if(recruiters.contains(recruiter))
            recruiters.remove(recruiter);
    }

    public void move(Department source, Department destination) {
        if(destination.getEmployees() != null) {
            destination.getEmployees().addAll(source.getEmployees());
        }
        if(destination.getEmployees() == null) {
            ArrayList<Employee> e = new ArrayList<>();
            e.addAll(source.getEmployees());
            destination.setEmployees(e);
        }
       source.setEmployees(null);
    }

    public void move(Employee employee, Department newDepartment) {
        if(this.departaments != null) {
            for(Department d : this.departaments) {
                if(d.getEmployees() != null) {
                    if(d.getEmployees().contains(employee));
                        d.getEmployees().remove(employee);
                }
            }
        }
        newDepartment.add(employee);
    }

    public boolean contains(Department department) {
        if(departaments.contains(department))
            return true;
        return false;
    }

    public boolean contains(Employee employee) {
        if (employee.equals(manager))
            return true;
        for (Department d : departaments) {
            if(d.getEmployees() != null) {
                if (d.getEmployees().contains(employee))
                    return true;
            }
        }
        return false;
    }

    public boolean contains(Recruiter recruiter) {
        if(recruiters.contains(recruiter))
            return true;
        return false;
    }

    //recruterul potrivit pentru un user este cel mai departat ca si nivel.
    //Daca mai multi recruteri au acelas nivel maxim, il selectam pe acela
    //cu raiting maxim
    public Recruiter getRecruiter(User user) {
        int maxLevel = 0;
        for(Recruiter r : recruiters) {
            if(user.getDegreeInFriendship(r) > maxLevel) {
                maxLevel = user.getDegreeInFriendship(r);
            }
        }
        ArrayList<Recruiter> recDisponibili = new ArrayList<>();
        double raitingMax = 5.0;
        for (Recruiter r : recruiters) {
            if(user.getDegreeInFriendship(r) == maxLevel) {
                recDisponibili.add(r);
                if (r.rating != null) {
                    if (r.rating > raitingMax)
                        raitingMax = r.rating;
                }
            }
        }
        if(recDisponibili.size() == 1) {
            return recDisponibili.get(0);
        } else if(recDisponibili.size() > 1) {
            for (Recruiter r : recruiters) {
                if(r.rating == raitingMax)
                    return r;
            }
        }
        return null;
    }

    public ArrayList<Job> getJobs() {
        ArrayList<Job> freeJobs = new ArrayList<>();
        if(departaments != null) {
            for (Department d : departaments) {
                if (d.getJobs() != null) {
                    freeJobs.addAll(d.getJobs());
                }
            }
        }
        return freeJobs;
    }

    public ArrayList<Employee> totalEmployee() {
        ArrayList<Employee> total = new ArrayList<>();
        if(this.manager != null)
            total.add(this.manager);
        if(this.departaments != null) {
            for (Department d  : this.departaments) {
                if(d.getEmployees() != null)
                    total.addAll(d.getEmployees());
            }
        }
        return total;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        StringBuilder ss = new StringBuilder();
        for (Department d : this.departaments) {
            s.append("\t").append(d);
        }

        for (Recruiter r : this.recruiters) {
            ss.append("\t\t").append(r.r.getInformation().getNume()).append(" ").
                    append(r.r.getInformation().getPrenume()).append("\n");
        }
        return "Company:\n" +
                "\tName:\t" + name  +
                "\n\tManager:\t" + manager.r.getInformation().getNume() + " " + manager.r.getInformation().getPrenume()
                + "\n" + s + "\tRecruiters:\n" + ss  +"\n";
    }

    @Override
    public void addObserver(User user) {
        if(arr.contains(user))
            return;
        arr.add(user);
    }

    @Override
    public void removeObserver(User user) {
        arr.remove(user);
    }

    @Override
    public void notifyAllObservers(String text) {
        for (Observer<User> observer : arr) {
            observer.update(text);
        }
    }
}