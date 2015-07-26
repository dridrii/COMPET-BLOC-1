package com.cptbloc.dao;

import java.util.List;

import com.cptbloc.beans.Juge;
import com.sun.security.ntlm.Client;

public class JugeDAO {
    void creer( Juge juge ) throws DAOException;

    Juge trouver( long id ) throws DAOException;

    List<Juge> lister() throws DAOEXception;

    void supprimer( Client client ) throws DAOException;

}