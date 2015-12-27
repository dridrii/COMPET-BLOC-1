package com.cptbloc.beans;

public class Bloc {
    private Long   IdBloc;
    private String NumBloc;
    private String CouleurDiff;
    private String CouleurVoie;
    private String Ouvreur;
    private int    NbReussi;
    private int    ValeurInit;
    private int    ValeurFinal;

    public Long getIdBloc() {
        return this.IdBloc;
    }

    public String getNumBloc() {
        return this.NumBloc;
    }

    public String getCouleurDiff() {
        return this.CouleurDiff;
    }

    public String getCouleurVoie() {
        return this.CouleurVoie;
    }

    public String getOuvreur() {
        return this.Ouvreur;
    }

    public int getNbReussi() {
        return this.NbReussi;
    }

    public int getValeurInit() {
        return this.ValeurInit;
    }

    public int getValeurFinal() {
        return this.ValeurFinal;
    }

    public void setIdBloc( Long IdBloc ) {
        this.IdBloc = IdBloc;
    }

    public void setNumBloc( String NumBloc ) {
        this.NumBloc = NumBloc;
    }

    public void setCouleurDiff( String CouleurDiff ) {
        this.CouleurDiff = CouleurDiff;
    }

    public void setCouleurVoie( String CouleurVoie ) {
        this.CouleurVoie = CouleurVoie;
    }

    public void setOuvreur( String Ouvreur ) {
        this.Ouvreur = Ouvreur;
    }

    public void setNbReussi( int NbReussi ) {
        this.NbReussi = NbReussi;
    }

    public void setValeurInit( int ValeurInit ) {
        this.ValeurInit = ValeurInit;
    }

    public void setValeurFinal( int ValeurFinal ) {
        this.ValeurFinal = ValeurFinal;
    }
}
