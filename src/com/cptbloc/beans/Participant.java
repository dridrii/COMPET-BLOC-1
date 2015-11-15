package com.cptbloc.beans;

public class Participant {
    private Long   idParticipant;
    private String dossard;
    private String nom;
    private String prenom;
    private int    age;
    private String sex;
    private String categorieparti;
    private String resultat;

    public Long getidParticipant() {
        return this.idParticipant;
    }

    public String getDossard() {
        return this.dossard;
    }

    public String getNom() {
        return this.nom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public int getAge() {
        return this.age;
    }

    public String getSex() {
        return this.sex;
    }

    public String getCategorieparti() {
        return this.categorieparti;
    }

    public String getResultat() {
        return this.resultat;
    }

    public void setidParticipant( Long idParticipant ) {
        this.idParticipant = idParticipant;
    }

    public void setDossard( String dossard ) {
        this.dossard = dossard;
    }

    public void setNom( String nom ) {
        this.nom = nom;
    }

    public void setPrenom( String prenom ) {
        this.prenom = prenom;
    }

    public void setAge( int age ) {
        this.age = age;
    }

    public void setSex( String sex ) {
        this.sex = sex;
    }

    public void setCategorieparti( String categorieparti ) {
        this.categorieparti = categorieparti;
    }

    public void setResultat( String resultat ) {
        this.resultat = resultat;
    }
}
