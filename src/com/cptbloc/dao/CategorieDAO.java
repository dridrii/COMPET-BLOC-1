package com.cptbloc.dao;

import com.cptbloc.beans.Categorie;

public interface CategorieDAO {

    Categorie trouverAgeHomme( int age ) throws DAOException;

    Categorie trouverAgeFemme( int age ) throws DAOException;

}