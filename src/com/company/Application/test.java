//import java.util.*;
//import java.util.zip.DataFormatException;
//
//public class test {
//    public static void main(String[] args) {
//        try {
//            Limba l1 = new Limba("English", "Experienced");
//            Limba l2 = new Limba("Romanian", "Experienced");
//            Limba l3 = new Limba("German", "Beginner");
//            ArrayList<Limba> limba1 = new ArrayList<>();
//            limba1.add(l1);
//            limba1.add(l2);
//            ArrayList<Limba> limba2 = new ArrayList<>();
//            limba2.add(l1);
//            limba2.add(l2);
//            limba2.add(l3);
//
//            Information inf1 = new Information("Andrei", "Dumitrescu", "andrei_dumitrescu@yahoo.com", "0722 233 822", "30.07.2000", "Barbat", limba1);
//            Information inf2 = new Information("Anica", "Gabriel", "anica.gabi@gamil.com", "0762 837 333", "15.09.2000", "Barbat", limba1);
//            Information inf3 = new Information("Buzera", "Tibieriu", "tibi_buzera@yahoo.com", "0745 234 567", "12.08.2000", "Barbat", limba1);
//            Information inf4 = new Information("Durla", "Cosmin", "cosmin39@gmail.com", "0762 345 990", "23.09.2000", "Barbat", limba1);
//            Information inf5 = new Information("Iulian", "Radoi", "iuliradoi39@gmail.com", "0733 322 990", "23.10.2000", "Barbat", limba1);
//            Information inf6 = new Information("Razvan", "Basaraba", "razvan_basaraba@yahoo.ro", "0760 332 009", "12.11.2000", "Barbat", limba2);
//
//            //dumi
//            Education e1 = new Education("12.09.2014", "30.06.2019", "CNET", "Liceu", 9.88);
//            Education e2 = new Education("12.09.2019", "ACS", "Universitate",9.71);
//            Education e3 = new Education("11.09.2010","03.07.2014","Nr2","Gimnaziu",10.0);
//            Vector<Education> ed1 = new Vector<>();
//            ed1.add(e1); ed1.add(e2);
//            Collections.sort(ed1);
//
//            Experience ex1 = new Experience("30.06.2020","02.10.2020","Intern developer","VreauLa");
//            Experience ex2 = new Experience("23.07.2021","Intern Software","Bloomberg");
//            Vector<Experience> exp1 = new Vector<>();
//            exp1.add(ex1); exp1.add(ex2);
//            Collections.sort(exp1);
//
//            Consumer.Resume r1 = new Consumer.Resume.ResumeBuilder(inf1).educations(ed1).experiences(exp1).build();
//
//            //gabi
//            Education e4 = new Education("12.09.2014", "30.06.2019", "CNET", "Liceu", 9.56);
//            Education e5 = new Education("12.09.2019", "ACS", "Universitate",9.02);
//            Vector<Education> ed2 = new Vector<>();
//            ed2.add(e5);
//
//            Experience ex3 = new Experience("12.10.2020","22.07.2021","Web", "La Pirvu");
//            Vector<Experience> exp2 = new Vector<>();
//            exp2.add(ex3);
//
//            Consumer.Resume r2 = new Consumer.Resume.ResumeBuilder(inf2).educations(ed2).experiences(exp2).build();
//
//            //tibi
//            Education e6 = new Education("12.09.2014", "30.06.2019", "CNET", "Liceu", 8.88);
//            Education e7 = new Education("12.09.2019", "ACS", "Universitate",8.27);
//            Vector<Education> ed3 = new Vector<>();
//            ed3.add(e7);
//
//            Experience ex4 = new Experience("12.11.2019", "05.11.2020","Logistica","Lsac");
//            Experience ex5 = new Experience("05.04.2019","Geo", "DOt.");
//            Vector<Experience> exp3 = new Vector<>();
//            exp3.add(ex4);
//
//            Consumer.Resume r3 = new Consumer.Resume.ResumeBuilder(inf3).educations(ed3).experiences(exp3).build();
//
//            //durla
//            Education e8 = new Education("12.09.2014", "30.06.2019", "CNTV", "Liceu", 7.38);
//            Education e9 = new Education("12.09.2019", "ACS", "Universitate",7.33);
//            Vector<Education> ed4 = new Vector<>();
//            ed4.add(e8); ed4.add(e9);
//            Collections.sort(ed4);
//
//            Experience ex6 = new Experience("30.05.2020", "03.02.2021","Editor", "Usu the man");
//            Vector<Experience> exp4 = new Vector<>();
//            exp4.add(ex6);
//
//            Consumer.Resume r4 = new Consumer.Resume.ResumeBuilder(inf4).educations(ed4).experiences(exp4).build();
//
//            //Radoi
//            Consumer.Resume r5 = new Consumer.Resume.ResumeBuilder(inf5).educations(ed4).experiences(null).build();
//
//            //Razvan
//            Education e10 = new Education("12.09.2019", "ACS", "Universitate",8.80);
//            Vector<Education> ed5 = new Vector<>();
//            ed5.add(e10);
//
//            Consumer.Resume r6 = new Consumer.Resume.ResumeBuilder(inf6).educations(ed5).experiences(null).build();
//
//            Recruiter dumi = new Recruiter(r1,null,"Dot.",3000.0,10.0);
//            Recruiter tibi = new Recruiter(r3,null,"Dot.",3000.0,7.7);
//            Manager razvan = new Manager(r6,null,"Dot.",5000.0,null);
//            User radoi = new User(r5, null);
//            User gabi = new User(r2,null);
//            User durla = new User(r4, null);
//
//            dumi.add(e3);
//            dumi.add(ex5);
//            ArrayList<Consumer> c1 = new ArrayList<>();
//            c1.add(razvan); c1.add(tibi);
//            dumi.setConsumers(c1);
//
//            gabi.add(e4);
//            gabi.add(durla);
//
//            razvan.add(ex5);
//            razvan.add(dumi);
//
//            tibi.add(e6);
//            tibi.add(dumi);
//            tibi.add(radoi);
//
//            radoi.add(durla);
//            radoi.add(tibi);
//
//            durla.add(radoi);
//            durla.add(gabi);
//
//
//            Application.getInstance().add(radoi);
//            Application.getInstance().add(durla);
//            Application.getInstance().add(gabi);
//
//            Company dot = new Company("Dot.",razvan,null,null);
//
//            DepartmentFactory i = new DepartmentFactory();
//            Department it = i.getDepartment("IT");
//            Department mark = i.getDepartment("Management");
//            it.add(dumi);
//            it.add(tibi);
//            dot.add(it);
//            dot.add(mark);
//            dot.add(dumi);
//            dot.add(tibi);
//            dot.add(it);
//            dot.add(mark);
//
//            Constraint<Integer> co1 = new Constraint<>(2010.0, 2021.0);
//            Constraint<Integer> co2 = new Constraint<>(0.0,5.0);
//            Constraint<Double> co3 = new Constraint<>(6.55,10.0);
//            Job soft = new Job("Software", "Dot.",false, co1, co2, co3, null, 1,1200.0);
//            Job bank = new Job("Account","Dot.",false,co1,co2,co3,null,2,700.0);
//            it.add(soft);
//            it.add(bank);
//
//            Application.getInstance().add(dot);
//            soft.apply(gabi);
//            soft.apply(durla);
//            soft.apply(radoi);
//            razvan.process(soft);
//            razvan.process(bank);
//
//            dot.move(tibi, mark);
//
//        } catch (InvalidDatesException e) {
//            e.printStackTrace();
//        } catch (ResumeIncompleteException e) {
//            e.printStackTrace();
//        }
//    }
//}
