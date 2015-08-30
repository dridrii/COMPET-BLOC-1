package com.cptbloc.beans;

public class Categorie {
    private Long   idDefCategorie;
    private String ageConfigHomme;
    private String ageConfigFemme;

    public Long getId() {
        return this.idDefCategorie;
    }

    public String getageConfigHomme() {
        return this.ageConfigHomme;
    }

    public String getageConfigFemme() {
        return this.ageConfigFemme;
    }

    public void setId( Long idDefCategorie ) {
        this.idDefCategorie = idDefCategorie;
    }

    public void setageConfigHomme( String ageConfigHomme ) {
        this.ageConfigHomme = ageConfigHomme;
    }

    public void setageConfigFemme( String ageConfigFemme ) {
        this.ageConfigFemme = ageConfigFemme;
    }

}
