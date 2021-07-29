package com.company.CodeFile;

import java.util.ArrayList;

public class DepartmentFactory {
    public Department getDepartment(String name) {
        if (name == null) {
            return null;
        }
        if(name.equalsIgnoreCase("IT")) {
            return new IT(null,null);
        }
        if(name.equalsIgnoreCase("Management")) {
            return new Management(null,null);
        }
        if(name.equalsIgnoreCase("Marketing")) {
            return new Marketing(null,null);
        }
        if(name.equalsIgnoreCase("Finance")) {
            return new Finance(null,null);
        }
        return null;
    }
}

class IT extends Department {
    public IT() {
    }

    public IT(ArrayList<Employee> employees, ArrayList<Job> jobs) {
        super(employees, jobs);
    }

    @Override
    public double getTotalSalaryBudget() {
        double suma = 0.0;
        if(this.getEmployees() != null) {
            for (Employee e : this.getEmployees()) {
                suma += e.getSalariu();
            }
        }
        return suma;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        StringBuilder ss = new StringBuilder();
        if(this.getEmployees() != null) {
            for (Employee e : this.getEmployees()) {
                s.append("\t\t").append(e.r.getInformation().getNume()).append(" ").append(e.r.getInformation().getPrenume()).append("\n");
            }
        }
        if(this.getJobs() != null) {
            for (Job j : this.jobs) {
                ss.append("\t\t").append(j.numeJob).append("\n");
            }
        }
        return "| IT |\n" +
                "\tTotal Salry Budget: " + getTotalSalaryBudget() + "\n" + "\tEmployees:\n" + s + "\tJobs:\n" + ss;
    }
}

class Management extends Department {
    public Management() {
    }

    public Management(ArrayList<Employee> employees, ArrayList<Job> jobs) {
        super(employees, jobs);
    }

    @Override
    public double getTotalSalaryBudget() {
        double suma = 0.0;
        if(this.getEmployees() != null) {
            for (Employee e : this.getEmployees()) {
                suma += e.getSalariu();
            }
        }
        return suma + 0.16 * suma;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        StringBuilder ss = new StringBuilder();
        if(this.getEmployees() != null) {
            for (Employee e : this.getEmployees()) {
                s.append("\t\t").append(e.r.getInformation().getNume()).append(" ").append(e.r.getInformation().getPrenume()).append("\n");
            }
        }
        if(this.getJobs() != null) {
            for (Job j : this.jobs) {
                ss.append("\t\t").append(j.numeJob).append("\n");
            }
        }
        return "| Management |\n" +
                "\tTotal Salry Budget: " + getTotalSalaryBudget() + "\n" + "\tEmployees:\n" + s + "\tJobs:\n" + ss;
    }
}

class Marketing extends Department {
    public Marketing() {
    }

    public Marketing(ArrayList<Employee> employees, ArrayList<Job> jobs) {
        super(employees, jobs);
    }

    @Override
    public double getTotalSalaryBudget() {
        double sumaSalariuMare = 0.0;
        double sumaSalariuMic = 0.0;
        double sumaSalariuMediu = 0.0;
        if(this.getEmployees() != null) {
            for (Employee e : this.getEmployees()) {
                if (e.getSalariu() - 5000.0 > 0.0) {
                    sumaSalariuMare += e.getSalariu();
                } else if (e.getSalariu() - 3000.0 < 0) {
                    sumaSalariuMediu += e.getSalariu();
                } else {
                    sumaSalariuMic += e.getSalariu();
                }
            }
        }
        return sumaSalariuMare + 0.1 * sumaSalariuMare + sumaSalariuMediu + 0.16 * sumaSalariuMediu + sumaSalariuMic;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        StringBuilder ss = new StringBuilder();
        if(this.getEmployees() != null) {
            for (Employee e : this.getEmployees()) {
                s.append("\t\t").append(e.r.getInformation().getNume()).append(" ").append(e.r.getInformation().getPrenume()).append("\n");
            }
        }
        if(this.getJobs() != null) {
            for (Job j : this.jobs) {
                ss.append("\t\t").append(j.numeJob).append("\n");
            }
        }
        return "| Marketing |\n" +
                "\tTotal Salry Budget: " + getTotalSalaryBudget()+ "\n" + "\tEmployees:\n" + s + "\tJobs:\n" + ss;
    }
}

class Finance extends Department {
    public Finance() {
    }

    public Finance(ArrayList<Employee> employees, ArrayList<Job> jobs) {
        super(employees, jobs);
    }

    @Override
    public double getTotalSalaryBudget() {
        double sumaVechimeMare = 0.0;
        double sumaVechimeMica = 0.0;
        if(this.getEmployees() != null) {
            for (Employee e : this.getEmployees()) {
                if (e.totalExperience() > 1) {
                    sumaVechimeMare += e.getSalariu();
                } else {
                    sumaVechimeMica += e.getSalariu();
                }
            }
        }
        return sumaVechimeMare + sumaVechimeMare * 0.16 + sumaVechimeMica + 0.10 * sumaVechimeMica;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        StringBuilder ss = new StringBuilder();
        if(this.getEmployees() != null) {
            for (Employee e : this.getEmployees()) {
                s.append("\t\t").append(e.r.getInformation().getNume()).append(" ").append(e.r.getInformation().getPrenume()).append("\n");
            }
        }
        if(this.getJobs() != null) {
            for (Job j : this.jobs) {
                ss.append("\t\t").append(j.numeJob).append("\n");
            }
        }
        return "| Finance |\n" +
                "\tTotal Salry Budget: " + getTotalSalaryBudget() + "\n" +
                "\tEmployees:\n" + s + "\tJobs:\n" + ss;
    }
}
