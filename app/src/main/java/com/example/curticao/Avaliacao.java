package com.example.curticao;

public class Avaliacao {

    String email;
    private int curti;
    private int bom;
    private int naoGostei;

    public Avaliacao(){}

    public Avaliacao(String email, int curti, int bom, int naoGostei) {
        this.email = email;
        this.curti = curti;
        this.bom = bom;
        this.naoGostei = naoGostei;
    }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public int getCurti() {
        return curti;
    }

    public void setCurti(int curti) {
        this.curti = curti;
    }

    public int getBom() {
        return bom;
    }

    public void setBom(int bom) {
        this.bom = bom;
    }

    public int getNaoGostei() {
        return naoGostei;
    }

    public void setNaoGostei(int naoGostei) {
        this.naoGostei = naoGostei;
    }
}
