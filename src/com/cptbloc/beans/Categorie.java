package com.cptbloc.beans;

public class Categorie {
    private Long idDefCategorie;
    private int  ageConfigHomme;
    private int  ageConfigFemme;

    public Long getId() {
        return this.idDefCategorie;
    }

    public int getageConfigHomme() {
        return this.ageConfigHomme;
    }

    public int getageConfigFemme() {
        return this.ageConfigFemme;
    }

    public void setId( Long idDefCategorie ) {
        this.idDefCategorie = idDefCategorie;
    }

    public void setageConfigHomme( int ageConfigHomme ) {
        this.ageConfigHomme = ageConfigHomme;
    }

    public void setageConfigFemme( int ageConfigFemme ) {
        this.ageConfigFemme = ageConfigFemme;
    }

}
