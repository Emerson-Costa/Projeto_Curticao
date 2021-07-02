package com.example.curticao;

public class Foto  {

    private byte[] fotoPerfil;
    private byte[] foto;

    private String nome;
    private String cidade;
    private String Slogan;
    private String legenda;

    private String email;

    public Foto(){}

    public Foto(byte[] foto, String nome, String cidade, String slogan, String legenda, String email) {
        this.foto = foto;
        this.nome = nome;
        this.cidade = cidade;
        Slogan = slogan;
        this.legenda = legenda;
        this.email = email;
    }

    public byte[] getFotoPerfil() { return fotoPerfil;}

    public void setFotoPerfil(byte[] fotoPerfil) { this.fotoPerfil = fotoPerfil; }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getSlogan() {
        return Slogan;
    }

    public void setSlogan(String slogan) {
        Slogan = slogan;
    }

    public String getLegenda() {
        return legenda;
    }

    public void setLegenda(String legenda) {
        this.legenda = legenda;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
