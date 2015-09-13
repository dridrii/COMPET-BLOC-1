package com.cptbloc.beans;

public class Participant {
    private Long   id;
    private String dossard;
    private String nom;
    private String prenom;
    private String age;
    private String sex;
    private String categorie;
    private String resultat;

    public Long getId() {
        return this.id;
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

    public String getAge() {
        return this.age;
    }

    public String getSex() {
        return this.sex;
    }

    public String getCategorie() {
        return this.categorie;
    }

    public String getResultat() {
        return this.resultat;
    }

    public void setId( Long id ) {
        this.id = id;
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

    public void setAge( String age ) {
        this.age = age;
    }

    public void setSex( String sex ) {
        this.sex = sex;
    }

    public void setCategorie( String categorie ) {
        this.categorie = categorie;
    }

    public void setResultat( String resultat ) {
        this.resultat = resultat;
    }
}
