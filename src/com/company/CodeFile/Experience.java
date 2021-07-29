package com.company.CodeFile;

import com.company.ExceptionFile.InvalidDatesException;

import java.util.StringTokenizer;
import java.util.Vector;

public class Experience implements Comparable {
    String dataDeStart;
    String dataDeSfarsit;
    String pozitia;
    String compania;

    public Experience() {
        dataDeStart = new String();
        dataDeSfarsit = new String();
        pozitia = new String();
        compania = new String();
    }

    public Experience(String dataDeStart, String pozitia, String compania) throws InvalidDatesException {
        this.dataDeStart = dataDeStart;
        this.dataDeSfarsit = null;
        this.pozitia = pozitia;
        this.compania = compania;
    }

    public Experience(String dataDeStart, String dataDeSfarsit, String pozitia,
                      String compania) throws InvalidDatesException {
        Vector<String> data1, data2;
        data1 = obtinereData(dataDeStart);
        data2 = null;
        if(dataDeSfarsit != null)
            data2 = obtinereData(dataDeSfarsit);
        if(comparareData(data1, data2) == 1)
            throw new InvalidDatesException("Datele sunt invalide!");
        else {
            this.dataDeStart = dataDeStart;
            this.dataDeSfarsit = dataDeSfarsit;
            this.pozitia = pozitia;
            this.compania = compania;
        }
    }

    public Vector<String> obtinereData(String data) {
        Vector<String> v = new Vector<>();
        StringTokenizer token = new StringTokenizer(data, ".");
        while (token.hasMoreTokens()) {
            v.add(token.nextToken());
        }
        return v;
    }

    @Override
    public String toString() {
        return "\tData de inceput:\t" + dataDeStart + "\n" +
                "\tDate de sfarsit:\t" + dataDeSfarsit + "\n" +
                "\tPozitia:\t" + pozitia + "\n" +
                "\tCompania\t" + compania  + "\n";
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

    //calculez anii de experienta:
    //caz1: data de sfarsit este null => 0
    //caz2: diferneta dintre ani e 0, deci comparam lunile, apoi zilele si returnam 1 sau 0
    //caz3: anii sunt diferit si luna din anul de sfarsit e mai mare ca cea din anul de inceput
    //caz3: anii sunt diferit si luna din anul de sfarsit e mai mica ca cea din anul de inceput
    public int aniExperienta() {
        if(dataDeSfarsit == null)
            return 0;
        Vector<String> data1, data2;
        data1 = obtinereData(dataDeStart);
        data2 = obtinereData(dataDeSfarsit);
        int ani = Integer.parseInt(data2.get(2)) - Integer.parseInt(data1.get(2));
        if(ani == 0) {
            int luni = Integer.parseInt(data2.get(1)) - Integer.parseInt(data1.get(1));
            if(luni == 3) {
                int zile = Integer.parseInt(data2.get(0)) - Integer.parseInt(data1.get(0));
                if(zile >= 0)
                    return 1;
                return 0;
            } else if(luni > 3) {
                return 1;
            }
            return 0;
        } else {
            int luni = Integer.parseInt(data2.get(1)) - Integer.parseInt(data1.get(1));
            if(luni == 3) {
                int zile = Integer.parseInt(data2.get(0)) - Integer.parseInt(data1.get(0));
                if(zile >= 0)
                    return ani + 1;
                return ani;
            } else if(luni > 3) {
              return ani + 1;
            } else if(luni < 3 && luni >= 0) {
                return ani;
            } else {
                luni += 12;
                if(luni == 3) {
                    int zile = Integer.parseInt(data2.get(0)) - Integer.parseInt(data1.get(0));
                    if(zile >= 0)
                        return ani;
                    return ani - 1;
                } else if(luni > 3) {
                    return ani;
                }
                return ani - 1;
            }
        }
    }

    @Override
    public int compareTo(Object o) {
        Experience e = (Experience) o;

        if (this.dataDeSfarsit != null && e.dataDeSfarsit != null) {
            Vector<String> data1, data2;
            data1 = obtinereData(e.dataDeSfarsit);
            data2 = obtinereData(this.dataDeSfarsit);
            int rezultat = comparareData(data1, data2);
            if(rezultat != 0)
                return rezultat;
            else {
                return this.compania.compareTo(e.compania);
            }
        }
        return this.compania.compareTo(e.compania);
    }
}
