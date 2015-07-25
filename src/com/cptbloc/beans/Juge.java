package com.cptbloc.beans;

public class Juge {
    private String id;
    private String pseudo;
    private String nom;
    private String prenom;
    private String mdp;

    public String getId() {
        return this.id;
    }

    public String getPseudo() {
        return this.pseudo;
    }

    public String getNom() {
        return this.nom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public String getMdp() {
        return this.mdp;
    }

    public void setId( String id ) {
        this.id = id;
    }

    public void setPseudo( String pseudo ) {
        this.pseudo = pseudo;
    }

    public void setNom( String nom ) {
        this.nom = nom;
    }

    public void setPrenom( String prenom ) {
        this.prenom = prenom;
    }

    public void setMdp( String mdp ) {
        this.mdp = mdp;
    }
}
