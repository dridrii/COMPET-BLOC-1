package com.cptbloc.dao;

import com.cptbloc.beans.Categorie;

public interface CategorieDAO {

    Categorie trouverAgeCategorie( Long idDefCategorie ) throws DAOException;
}