package com.example.curticao;

public class User {


    private byte[]foto;
    private String nome, telefone, email, senha, cidade, slogan;
    private int idade;

    public User(){ }

    public User(byte[] foto, String nome, String telefone, String email, String senha, String cidade, String slogan, int idade) {
        this.foto = foto;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;
        this.cidade = cidade;
        this.slogan = slogan;
        this.idade = idade;
    }

    public byte[] getFoto() { return foto; }

    public void setFoto(byte[] foto) { this.foto = foto; }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }
}
