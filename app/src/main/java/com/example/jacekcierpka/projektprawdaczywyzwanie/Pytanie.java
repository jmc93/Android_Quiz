package com.example.jacekcierpka.projektprawdaczywyzwanie;

public class Pytanie {
    private String pytanie;
    private String opcja1;
    private String opcja2;
    private String opcja3;
    private String opcja4;
    private int nrOdpowiedzi;


    public  Pytanie() {}

    public Pytanie(String pytanie, String opcja1, String opcja2, String opcja3, String opcja4, int nrOdpowiedzi) {
        this.pytanie = pytanie;
        this.opcja1 = opcja1;
        this.opcja2 = opcja2;
        this.opcja3 = opcja3;
        this.opcja4 = opcja4;
        this.nrOdpowiedzi = nrOdpowiedzi;
    }

    public String getPytanie() {
        return pytanie;
    }

    public void setPytanie(String pytanie) {
        this.pytanie = pytanie;
    }

    public String getOpcja1() {
        return opcja1;
    }

    public void setOpcja1(String opcja1) {
        this.opcja1 = opcja1;
    }

    public String getOpcja2() {
        return opcja2;
    }

    public void setOpcja2(String opcja2) {
        this.opcja2 = opcja2;
    }

    public String getOpcja3() {
        return opcja3;
    }

    public void setOpcja3(String opcja3) {
        this.opcja3 = opcja3;
    }

    public String getOpcja4() {
        return opcja4;
    }

    public void setOpcja4(String opcja4) {
        this.opcja4 = opcja4;
    }

    public int getNrOdpowiedzi() {
        return nrOdpowiedzi;
    }

    public void setNrOdpowiedzi(int nrOdpowiedzi) {
        this.nrOdpowiedzi = nrOdpowiedzi;
    }
}
