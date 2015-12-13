package com.cptbloc.beans;

public class Bloc {
    private String idBloc;
    private String NumBloc;
    private String CouleurDif;
    private String CouleurVoie;
    private String Ouvreur;
    private String NbReussi;
    private String ValeurInit;
    private String ValeurFinal;

    public String getIdBloc() {
        return this.idBloc;
    }

    public String getNumBloc() {
        return this.NumBloc;
    }

    public String getCouleurDif() {
        return this.CouleurDif;
    }

    public String getCouleurVoie() {
        return this.CouleurVoie;
    }

    public String getOuvreur() {
        return this.Ouvreur;
    }
    
    public String getNbReussi() {
        return this.NbReussi;
    }
    
    public String getValeurInit() {
        return this.ValeurInit;
    }
    
    public String getValeurFinal() {
        return this.ValeurFinal;
    }

    public void setIdBloc( String idBloc ) {
        this.idBloc = idBloc;
    }

    public void setNumBloc( String NumBloc) {
        this.NumBloc = NumBloc;
    }

    public void setCouleurDif( String CouleurDif) {
        this.CouleurDif = CouleurDif;
    }

    public void setCouleurVoie( String CouleurVoie) {
        this.CouleurVoie = CouleurVoie;
    }

    public void setOuvreur( String Ouvreur) {
        this.Ouvreur = Ouvreur;
    }
    
    public void setNbReussi( String NbReussi) {
        this.NbReussi = NbReussi;
    }
    
    public void setValeurInit( String ValeurInit) {
        this.ValeurInit = ValeurInit;
    }
    
    public void setValeurFinal( String ValeurFinal) {
        this.ValeurFinal = ValeurFinal;
    }
}
