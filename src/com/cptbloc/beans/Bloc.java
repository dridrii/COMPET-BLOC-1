package com.cptbloc.beans;

public class Bloc {
    
    private Long   idBloc;
    private int numBloc;
    private String couleurDiff;
    private String couleurVoie;
    private String ouvreur;
    private int    nbReussi;
    private int    valeurInit;
    private int    valeurFinal;

    public Long getIdBloc() {
        return this.idBloc;
    }

    public int getNumBloc() {
        return this.numBloc;
    }

    public String getCouleurDiff() {
        return this.couleurDiff;
    }

    public String getCouleurVoie() {
        return this.couleurVoie;
    }

    public String getOuvreur() {
        return this.ouvreur;
    }

    public int getNbReussi() {
        return this.nbReussi;
    }

    public int getValeurInit() {
        return this.valeurInit;
    }

    public int getValeurFinal() {
        return this.valeurFinal;
    }

    public void setIdBloc( Long idBloc ) {
        this.idBloc = idBloc;
    }

    public void setNumBloc( int numBloc ) {
        this.numBloc = numBloc;
    }

    public void setCouleurDiff( String couleurDiff ) {
        this.couleurDiff = couleurDiff;
    }

    public void setCouleurVoie( String couleurVoie ) {
        this.couleurVoie = couleurVoie;
    }

    public void setOuvreur( String ouvreur ) {
        this.ouvreur = ouvreur;
    }

    public void setNbReussi( int nbReussi ) {
        this.nbReussi = nbReussi;
    }

    public void setValeurInit( int valeurInit ) {
        this.valeurInit = valeurInit;
    }

    public void setValeurFinal( int valeurFinal ) {
        this.valeurFinal = valeurFinal;
    }
}
