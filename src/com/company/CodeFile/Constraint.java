package com.company.CodeFile;

public class Constraint<E> {
    Double limitaInferioara;
    Double limitaSuperioara;

    public Constraint() {
    }

    public Constraint(Double limitaInferioara, Double limitaSuperioara) {
        this.limitaInferioara = limitaInferioara;
        this.limitaSuperioara = limitaSuperioara;
    }

    public int inInterval(Double data) {
       if(limitaInferioara != null && limitaSuperioara != null) {
           if(limitaInferioara <= data && limitaSuperioara >= data)
               return 1;
           return 0;
       } else  if(limitaInferioara != null && limitaSuperioara == null) {
           if(limitaInferioara <= data)
               return 1;
           return 0;
       } else  if(limitaInferioara == null && limitaSuperioara != null) {
           if(limitaSuperioara >= data)
               return 1;
           return 0;
       } else
           return 1;
    }

    @Override
    public String toString() {
        return "\tLimita Inferioara = " + limitaInferioara +
                "\n\tLimita Superioara = " + limitaSuperioara;
    }
}
