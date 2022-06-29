package br.com.agenda.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Aluno implements Serializable {
    private long id = 0;
    private String nome;
    private String telefone;
    private String email;

    public Aluno(String nome, String telefone, String email) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }

    public Aluno() {

    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public long getId() {
        return id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NonNull
    @Override
    public String toString() {
        return nome+" "+telefone+" "+email;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isNovoAluno() {
        return this.id == 0;
    }
}
