package com.example.curticao;

public class Foto  {

    private String email;
    private byte[]foto;
    private String legenda;

    public Foto(){}

    public Foto(String email, byte[] foto, String legenda) {
        this.email = email;
        this.foto = foto;
        this.legenda = legenda;
    }

    public byte[] getFoto() {
        return foto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public String getLegenda() {
        return legenda;
    }

    public void setLegenda(String legenda) {
        this.legenda = legenda;
    }
}
