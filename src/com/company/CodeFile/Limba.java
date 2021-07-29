package com.company.CodeFile;

public class Limba {
    String l;
    String nivel;

    public Limba() {
        l = new String();
        nivel = new String();
    }

    public Limba(String l, String nivel) {
        this.l = l;
        this.nivel = nivel;
    }

    @Override
    public String toString() {
        return  l + "\tnivel: " + nivel + "\n";
    }
}
