package com.company.CodeFile;

import com.company.ExceptionFile.InvalidDatesException;

import java.util.StringTokenizer;
import java.util.Vector;

public class Education implements Comparable {
    String dataDeInceput;
    String dataDeSfarsit;
    String numeInstitutie;
    String nivelEducatie;
    Double mediaDeFinalizare;

    public Education() {
        dataDeInceput = new String();
        dataDeSfarsit = new String();
        numeInstitutie = new String();
        nivelEducatie = new String();
    }

    public Education(String dataDeInceput, String numeInstitutie, String nivelEducatie, Double mediaDeFinalizare) throws InvalidDatesException {
            this.dataDeInceput = dataDeInceput;
            this.dataDeSfarsit = null;
            this.numeInstitutie = numeInstitutie;
            this.nivelEducatie = nivelEducatie;
            this.mediaDeFinalizare = mediaDeFinalizare;
    }

    public Education(String dataDeInceput, String dataDeSfarsit, String numeInstitutie, String nivelEducatie, Double mediaDeFinalizare) throws InvalidDatesException {
            Vector<String> data1, data2;
            data1 = obtinereData(dataDeInceput);
            data2 = null;
            if(dataDeSfarsit != null)
                data2 = obtinereData(dataDeSfarsit);
            if(comparareData(data1, data2) == 1)
                throw new InvalidDatesException("Datele sunt invalide@");
            else {
                this.dataDeInceput = dataDeInceput;
                this.dataDeSfarsit = dataDeSfarsit;
                this.numeInstitutie = numeInstitutie;
                this.nivelEducatie = nivelEducatie;
                this.mediaDeFinalizare = mediaDeFinalizare;
        }
    }

    //separam  un string in tokens care reprezinta data (dd.yy.mm)
    public Vector<String> obtinereData(String data) {
        Vector<String> v = new Vector<>();
        StringTokenizer token = new StringTokenizer(data, ".");
        while (token.hasMoreTokens()) {
            v.add(token.nextToken());
        }
        return v;
    }

    //verificam daca doua date sunt ok din punct de vedere calendaristic
    public int comparareData(Vector<String> data1, Vector<String> data2) {
        if (Integer.parseInt(data1.get(2)) -  Integer.parseInt(data2.get(2)) > 0) {
            return 1;
        }
        else if (Integer.parseInt(data1.get(2)) -  Integer.parseInt(data2.get(2)) < 0) {
            return -1;
        } else {
            if (Integer.parseInt(data1.get(1)) -  Integer.parseInt(data2.get(1)) > 0) {
                return 1;
            }
            else if (Integer.parseInt(data1.get(1)) -  Integer.parseInt(data2.get(1)) < 0) {
                return -1;
            } else {
                if (Integer.parseInt(data1.get(0)) -  Integer.parseInt(data2.get(0)) > 0) {
                    return 1;
                }
                else if (Integer.parseInt(data1.get(0)) -  Integer.parseInt(data2.get(0)) < 0) {
                    return -1;
                }
            }
        }
        return 0;
    }

    public void setMediaDeFinalizare(Double mediaDeFinalizare) {
        this.mediaDeFinalizare = mediaDeFinalizare;
    }

    @Override
    public String toString() {
        return "\tData de inceput:\t" + dataDeInceput + "\n" +
                "\tData de sfarsit:\t" + dataDeSfarsit + "\n" +
                "\tInstitutia:\t" + numeInstitutie + "\n" +
                "\tNivel: " + nivelEducatie + "\n" +
                "\tMedia de finalizare\t" + mediaDeFinalizare + "\n";
    }

    @Override
    public int compareTo(Object o) {
        Education e = (Education) o;

        if(this.dataDeSfarsit == null || e.dataDeSfarsit == null) {
            Vector<String> data1, data2;
            data1 = obtinereData(e.dataDeInceput);
            data2 = obtinereData(this.dataDeInceput);
            int rezultat = comparareData(data1, data2);
            return -rezultat;
        } else {
            Vector<String> data1, data2;
            data1 = obtinereData(e.dataDeSfarsit);
            data2 = obtinereData(this.dataDeSfarsit);
            int rezultat = comparareData(data1, data2);
            if(rezultat != 0)
                return rezultat;
            else {
                if(this.mediaDeFinalizare - e.mediaDeFinalizare < 0.0)
                    return 1;
                else
                    return -1;
            }
        }
    }
}
