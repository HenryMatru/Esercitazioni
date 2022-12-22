package it.course.servletstudente.models;

public class Studente {

    private String nome;
    private String cognome;
    private String scuola;

    public Studente() {}

    public Studente(String nome, String cognome, String scuola) {
        this.setNome(nome);
        this.setCognome(cognome);
        this.setScuola(scuola);
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return this.cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getScuola() {
        return this.scuola;
    }

    public void setScuola(String scuola) {
        this.scuola = scuola;
    }

}
