package com.company.Application;

import com.company.CodeFile.*;
import com.company.ExceptionFile.InvalidDatesException;
import com.company.ExceptionFile.ResumeIncompleteException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.*;

public class Test {
    //Creez resume-ul unui consumer
    public Consumer.Resume create(JSONObject o) {
        String name = (String) o.get("name");
        String email = (String) o.get("email");
        String phone = (String) o.get("phone");
        String date_of_birth = (String) o.get("date_of_birth");
        String genre = (String) o.get("genre");

        JSONArray languages = (JSONArray) o.get("languages");
        JSONArray languages_level = (JSONArray) o.get("languages_level");
        ArrayList<Limba> l = new ArrayList<>();
        for (int i = 0; i < languages.size(); i++) {
            String lan = (String) languages.get(i);
            String lev = (String) languages_level.get(i);
            Limba limba = new Limba(lan, lev);
            l.add(limba);
        }

        ArrayList<String> arr = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(name, " ");
        while (st.hasMoreTokens()) {
            arr.add(st.nextToken());
        }
        Information information = new Information(arr.get(0), arr.get(1), email, phone, date_of_birth, genre, l);

        JSONArray educations = (JSONArray) o.get("education");
        Iterator<JSONObject> itEd = educations.iterator();
        Vector<Education> arrEd = new Vector<>();
        while (itEd.hasNext()) {
            JSONObject oEd = itEd.next();
            String levelEd = (String) oEd.get("level");
            String nameEd = (String) oEd.get("name");
            String start = (String) oEd.get("start_date");
            String end = (String) oEd.get("end_data");
            if (oEd.get("grade") instanceof Double) {
                Double grade = (Double) oEd.get("grade");
                if (end != null) {
                    try {
                        Education education = new Education(start, end, nameEd, levelEd, grade);
                        arrEd.add(education);
                    } catch (InvalidDatesException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        Education education = new Education(start, nameEd, levelEd, grade);
                        arrEd.add(education);
                    } catch (InvalidDatesException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                Long grade = (Long) oEd.get("grade");
                if (end != null) {
                    try {
                        Education education = new Education(start, end, nameEd, levelEd, (double) grade);
                        arrEd.add(education);
                    } catch (InvalidDatesException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        Education education = new Education(start, nameEd, levelEd, (double) grade);
                        arrEd.add(education);
                    } catch (InvalidDatesException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        Vector<Experience> arrEx = new Vector<>();
        JSONArray experiences = (JSONArray) o.get("experience");
        Iterator<JSONObject> itEx = experiences.iterator();
        while (itEx.hasNext()) {
            JSONObject oEx = itEx.next();
            String company = (String) oEx.get("company");
            String position = (String) oEx.get("position");
            String startEx = (String) oEx.get("start_date");
            if (oEx.get("end_date") instanceof String) {
                try {
                    String endEx = (String) oEx.get("end_date");
                    Experience experience = new Experience(startEx, endEx, position, company);
                    arrEx.add(experience);
                } catch (InvalidDatesException e ) {
                    e.printStackTrace();
                }
            } else {
                try {
                    Experience experience = new Experience(startEx, position, company);
                    arrEx.add(experience);
                } catch (InvalidDatesException e) {
                    e.printStackTrace();
                }
            }
        }

        Collections.sort(arrEd);
        Collections.sort(arrEx);
        try {
            Consumer.Resume resume = new Consumer.Resume.ResumeBuilder(information).
                    educations(arrEd).experiences(arrEx).build();
            return resume;
        } catch (ResumeIncompleteException e) {
            e.printStackTrace();
        }
        return null;
    }

    //creez un job
    public Job creteJobs(JSONObject o) {
        String name = (String) o.get("name");
        String company = (String) o.get("company");
        String department = (String) o.get("department");
        Boolean status = (Boolean) o.get("status");
        Integer nrCanditati;
        if(o.get("nrCanditati") instanceof Long)
            nrCanditati = ((Long) o.get("nrCanditati")).intValue();
        else
            nrCanditati = (Integer) o.get("nrCanditati");
        Long salariu = (Long) o.get("salariu");


        ArrayList<Constraint> cons = new ArrayList<>();
        JSONArray constraint = (JSONArray) o.get("constraint");
        Iterator<JSONObject> itCons = constraint.iterator();
        while (itCons.hasNext()) {
            JSONObject ob = itCons.next();
            if(ob.get("min") instanceof Long) {
                Integer min = ((Long) ob.get("min")).intValue();
                if(ob.get("max") instanceof Long) {
                    Integer max = ((Long) ob.get("max")).intValue();
                    Constraint c = new Constraint((double)min, (double)max);
                    cons.add(c);
                } else {
                    Double max = (Double) ob.get("max");
                    Constraint c = new Constraint((double)min, max);
                    cons.add(c);
                }
            } else {
                Double min = ((Double) ob.get("min"));
                if(ob.get("max") instanceof Long) {
                    Integer max = ((Long) ob.get("max")).intValue();
                    Constraint c = new Constraint((double)min,(double) max);
                    cons.add(c);
                } else {
                    Double max = (Double) ob.get("max");
                    Constraint c = new Constraint(min, max);
                    cons.add(c);
                }
            }
        }
        Job job = new Job(name, company,status,cons.get(0),cons.get(1),cons.get(2),
                null, nrCanditati,salariu, department);
        return job;
    }

    //creez lista de companii
    public void createCommpany(JSONObject jsonC, ArrayList<Employee> employees,
                               ArrayList<Manager> managers, ArrayList<Recruiter> recruiters, ArrayList<Job> jobs) {
        ArrayList<Company> companies = new ArrayList<>();

        JSONArray comp = (JSONArray) jsonC.get("companies");
        Iterator<JSONObject> it = comp.iterator();
        while (it.hasNext()) {
            JSONObject o = it.next();

            DepartmentFactory dep = new DepartmentFactory();
            Department IT = dep.getDepartment("IT");
            Department mark = dep.getDepartment("Marketing");
            Department mana = dep.getDepartment("Management");
            Department finance = dep.getDepartment("Finance");
            ArrayList<Department> departments = new ArrayList<>();
            departments.add(IT);
            departments.add(mark);
            departments.add(mana);
            departments.add(finance);

            String name = (String) o.get("name");

            //caut in lista de manageri, manaferul potrivit companiei
            int count = 0;
            if(managers != null) {
                for (Manager m : managers) {
                    if (m.numeleCompaniei.equals(name)) {
                        break;
                    }
                    count++;
                }
            }

            //caut recruteri potriviti cimpaniei si ii adaug si in departamentul de IT
            ArrayList<Recruiter> recruitersForThis = new ArrayList<>();
            if(recruiters != null) {
                for (Recruiter r : recruiters) {
                    if (r.numeleCompaniei.equals(name)) {
                        recruitersForThis.add(r);
                        IT.add(r);
                    }
                }
            }

            //apelez constructorul din company
            Company company = new Company(name,managers.get(count),departments,recruitersForThis);

            //adaug employees potriviti in departamente
            if(employees != null) {
                for (Employee e : employees) {
                    if (e.numeleCompaniei.equals(name)) {
                        if (e.departament.equals("IT"))
                            IT.add(e);
                        if (e.departament.equals("Marketing"))
                            mark.add(e);
                        if (e.departament.equals("Management"))
                            mana.add(e);
                        if (e.departament.equals("Finance"))
                            finance.add(e);
                    }
                }
            }

            //adaug joburile in departamente
            if(jobs != null) {
                for (Job j : jobs) {
                    if(j.numeCompanie.equals(name)) {
                        if(j.departament.equals("IT"))
                            IT.add(j);
                        if (j.departament.equals("Marketing"))
                            mark.add(j);
                        if (j.departament.equals("Management"))
                            mana.add(j);
                        if (j.departament.equals("Finance"))
                            finance.add(j);
                    }
                }
            }
           Application.getInstance().add(company);
        }
    }

    public void createRelations(JSONObject jsonF) {
        JSONArray arr = (JSONArray) jsonF.get("friendship");
        Iterator<JSONObject> it = arr.iterator();
        while (it.hasNext()) {
            JSONObject o = it.next();
            String name = (String) o.get("name");
            Consumer consumer = Application.getInstance().findName(name);
            JSONArray friends = (JSONArray) o.get("arr");
            for (int i = 0; i < friends.size(); i++) {
                String friendName = (String) friends.get(i);
                consumer.add(Application.getInstance().findName(friendName));
            }
        }
    }

    public static void main(String[] args) {
        Test t = new Test();
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("./src/com/company/InputFile/consumers.json"));
            Object objJ = parser.parse(new FileReader("./src/com/company/InputFile/jobs.json"));
            Object objC = parser.parse(new FileReader("./src/com/company/InputFile/companies.json"));
            Object objF = parser.parse(new FileReader("./src/com/company/InputFile/friendship.json"));
            JSONObject jsonObject = (JSONObject) obj;
            JSONObject jsonJ = (JSONObject) objJ;
            JSONObject jsonC = (JSONObject) objC;
            JSONObject jsonF = (JSONObject) objF;

            ///Citire manageri din fisier si constuirea unui array de manageri
            ArrayList<Manager> managers = new ArrayList<>();
            JSONArray arrManagers = (JSONArray) jsonObject.get("managers");
            Iterator<JSONObject> itM = arrManagers.iterator();
            while (itM.hasNext()) {
                JSONObject o = itM.next();
                Double salariu;
                if(o.get("salary") instanceof Long)
                    salariu = ((Long) o.get("salary")).doubleValue();
                else
                    salariu = (Double) o.get("salary");
                Consumer.Resume resume = t.create(o);
                JSONArray exp = (JSONArray) o.get("experience");
                Iterator<JSONObject> itEx = exp.iterator();
                while (itEx.hasNext()) {
                    JSONObject oEx = itEx.next();
                    if(oEx.get("end_date") == null) {
                        String company = (String) oEx.get("company");
                        Manager m = new Manager(resume,null,company,salariu,null);
                        managers.add(m);
                    }
                }
            }

            //citesc din fisier employees si ii bag intr o lisa
            ArrayList<Employee> employees = new ArrayList<>();
            JSONArray arrEmployee = (JSONArray) jsonObject.get("employees");
            Iterator<JSONObject> it = arrEmployee.iterator();
            while (it.hasNext()) {
                JSONObject o = it.next();
                Consumer.Resume resume = t.create(o);
                Double salariu;
                if(o.get("salary") instanceof Long)
                    salariu = ((Long) o.get("salary")).doubleValue();
                else
                    salariu = (Double) o.get("salary");
                JSONArray exp = (JSONArray) o.get("experience");
                Iterator<JSONObject> itEx = exp.iterator();
                while (itEx.hasNext()) {
                    JSONObject oEx = itEx.next();
                    if(oEx.get("end_date") == null) {
                        String company = (String) oEx.get("company");
                        String departament = (String) oEx.get("departament");
                        Employee e = new Employee(resume,null,company, salariu,departament);
                        employees.add(e);
                    }
                }
            }

            //citire recruiteri
            ArrayList<Recruiter> recruiters = new ArrayList<>();
            JSONArray arrRecruiters = (JSONArray) jsonObject.get("recruiters");
            Iterator<JSONObject> itR = arrRecruiters.iterator();;
            while (itR.hasNext()) {
                JSONObject o = itR.next();
                Consumer.Resume resume = t.create(o);
                Double salariu;
                if(o.get("salary") instanceof Long)
                    salariu = ((Long) o.get("salary")).doubleValue();
                else
                    salariu = (Double) o.get("salary");
                JSONArray exp = (JSONArray) o.get("experience");
                Iterator<JSONObject> itEx = exp.iterator();
                while (itEx.hasNext()) {
                    JSONObject oEx = itEx.next();
                    if(oEx.get("end_date") == null) {
                        String company = (String) oEx.get("company");
                        Recruiter r = new Recruiter(resume,null,company, salariu);
                        recruiters.add(r);
                    }
                }
            }

            //citesc users
            ArrayList<User> users = new ArrayList<>();
            JSONArray arrUsers = (JSONArray) jsonObject.get("users");
            Iterator<JSONObject> itU = arrUsers.iterator();
            while (itU.hasNext()) {
                JSONObject o = itU.next();
                Consumer.Resume resume = t.create(o);
                JSONArray interested_companies = (JSONArray) o.get("interested_companies");
                ArrayList<String> interested = new ArrayList<>();
                for(int i = 0; i < interested_companies.size(); i++) {
                    String inC = (String) interested_companies.get(i);
                    interested.add(inC);
                }
                User u = new User(resume,null, interested);
                users.add(u);
                Application.getInstance().add(u);
            }


            //citesc jobs
            ArrayList<Job> jobs = new ArrayList<>();
            JSONArray arrJob = (JSONArray) jsonJ.get("jobs");
            Iterator<JSONObject> itJob = arrJob.iterator();
            while (itJob.hasNext()) {
                JSONObject o = itJob.next();
                Job job = t.creteJobs(o);
                jobs.add(job);
            }

            //creez lista de companii
            t.createCommpany(jsonC, employees, managers, recruiters,jobs);

            //adaug relatiile intre users
            t.createRelations(jsonF);

            //users aplica la joburile care ii intereseaza
           for (User user : Application.getInstance().getUsers()) {
               for(Company c : Application.getInstance().getCompanies()) {
                   if(user.numeCompani.contains(c.name)) {
                       for (Department d : c.departaments) {
                           if(d.jobs != null) {
                               for (Job j : d.jobs) {
                                   j.apply(user);
                               }
                           }
                       }
                   }
               }
           }

           //manageri analizeaza requesturile
           for (Company c : Application.getInstance().getCompanies()) {
               for (Department d : c.departaments) {
                   if(d.jobs != null) {
                       for (Job j : d.jobs) {
                           c.manager.process(j);
                       }
                   }
               }
           }

           System.out.println(Application.getInstance().getCompanies());


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
