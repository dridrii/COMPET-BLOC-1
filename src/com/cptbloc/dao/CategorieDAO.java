package com.cptbloc.dao;

import com.cptbloc.beans.Categorie;

public interface CategorieDAO {

    Categorie trouverAgeHomme( String age ) throws DAOException;

    Categorie trouverAgeFemme( String age ) throws DAOException;

}