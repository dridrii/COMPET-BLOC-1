package com.cptbloc.dao;

import com.cptbloc.beans.Categorie;

public interface CategorieDAO {

    Categorie trouverAgeCategorie( int idDefCategorie ) throws DAOException;
}