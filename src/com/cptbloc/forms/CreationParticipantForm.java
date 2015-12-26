package com.cptbloc.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.cptbloc.beans.Categorie;
import com.cptbloc.beans.Participant;
import com.cptbloc.dao.CategorieDAO;
import com.cptbloc.dao.DAOException;
import com.cptbloc.dao.ParticipantDAO;

public final class CreationParticipantForm {
	private static final String CHAMP_DOSSARD = "dossardParticipant";
	private static final String CHAMP_NOM = "nomParticipant";
	private static final String CHAMP_PRENOM = "prenomParticipant";
	private static final String CHAMP_AGE = "ageParticipant";
	private static final String CHAMP_SEX = "sexParticipant";

	private String resultat;
	private Map<String, String> erreurs = new HashMap<String, String>();
	private ParticipantDAO participantDAO;
	private CategorieDAO categorieDAO;

	public CreationParticipantForm(ParticipantDAO participantDAO, CategorieDAO categorieDAO) {
		this.participantDAO = participantDAO;
		this.categorieDAO = categorieDAO;
	}

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	public String getResultat() {
		return resultat;
	}

	public Participant creerParticipant(HttpServletRequest request) {
		String dossard = getValeurChamp(request, CHAMP_DOSSARD);
		String nom = getValeurChamp(request, CHAMP_NOM);
		String prenom = getValeurChamp(request, CHAMP_PRENOM);
		String agetx = getValeurChamp(request, CHAMP_AGE);
		String sex = getValeurChamp(request, CHAMP_SEX);

		String str = agetx;
		int age = Integer.parseInt(str);

		int idDefCategorie = 1;
		
		Categorie categorie = new Categorie();
		categorie = categorieDAO.trouverAgeCategorie(idDefCategorie);

		Participant participant = new Participant();
		try {
			traiterDossard(dossard, participant);
			traiterNom(nom, participant);
			traiterPrenom(prenom, participant);
			traiterAge(age, participant);
			participant.setSex(sex);
			traiterCategorie(categorie, participant);

			if (erreurs.isEmpty()) {
				participantDAO.creer(participant);
				resultat = "Succès de l'inscription.";
			} else {
				resultat = "Echec de l'inscription.";
			}

		} catch (DAOException e) {
			setErreur("imprévu", "Erreur imprévue lors de la création.");
			resultat = "Echec de l'insciption : une erreur imprévue et survenue, merci de réessayer dans quelques instants.";
			e.printStackTrace();
		}
		return participant;
	}

	private void traiterDossard(String dossard, Participant participant) {
		try {
			validationDossard(dossard);
		} catch (FormValidationException e) {
			setErreur(CHAMP_DOSSARD, e.getMessage());
		}
		participant.setDossard(dossard);
	}

	private void traiterNom(String nom, Participant participant) {
		try {
			validationNom(nom);
		} catch (FormValidationException e) {
			setErreur(CHAMP_NOM, e.getMessage());
		}
		participant.setNom(nom);
	}

	private void traiterPrenom(String prenom, Participant participant) {
		try {
			validationPrenom(prenom);
		} catch (FormValidationException e) {
			setErreur(CHAMP_PRENOM, e.getMessage());
		}
		participant.setPrenom(prenom);
	}

	private void traiterAge(int age, Participant participant) {
		try {
			validationAge(age);
		} catch (FormValidationException e) {
			setErreur(CHAMP_AGE, e.getMessage());
		}
		participant.setAge(age);
	}

	private void traiterCategorie(Categorie categorie, Participant participant) {
		try {
			validationCategorie(categorie, participant);
		} catch (FormValidationException e) {
			setErreur(CHAMP_AGE, e.getMessage());
		}
	}

	private void validationDossard(String dossard) throws FormValidationException {
		if (dossard != null) {
			if (participantDAO.trouver(dossard) != null) {
				throw new FormValidationException("Ce dossard est déjà attribué");

			}
		} else {
			throw new FormValidationException("Merci d'entrer un numéro de dossard");
		}
	}

	private void validationNom(String nom) throws FormValidationException {
		if (nom != null) {
			if (nom.length() < 3) {
				throw new FormValidationException("Le nom d'un participant doit contenir au moins 3 caractères.");
			}
		} else {
			throw new FormValidationException("Merci d'entrer un nom.");
		}
	}

	private void validationPrenom(String prenom) throws FormValidationException {
		if (prenom != null && prenom.length() < 3) {
			throw new FormValidationException("Le prénom d'un participant doit contenir au moins 3 caractères.");
		}
	}

	private void validationAge(int age) throws FormValidationException {

		if (age > 0 && age < 99) {

		} else {
			throw new FormValidationException("Merci d'insérer votre age !");
		}
	}

	private void validationCategorie(Categorie categorie, Participant participant) throws FormValidationException {

		if (participant.getSex().equals("Homme")) {

			if (participant.getAge() < categorie.getageConfigHomme()) {
				participant.setCategorieparti("JuniorHomme");

			} else {
				participant.setCategorieparti("EliteHomme");
			}
		}

		if (participant.getSex().equals("Femme")) {

			if (participant.getAge() < categorie.getageConfigFemme()) {
				participant.setCategorieparti("JuniorFemme");

			} else {
				participant.setCategorieparti("EliteFemme");
			}
		}

	}

	private void setErreur(String champ, String message) {
		erreurs.put(champ, message);
	}

	private static String getValeurChamp(HttpServletRequest request, String nomChamp) {
		String valeur = request.getParameter(nomChamp);
		if (valeur == null || valeur.trim().length() == 0) {
			return null;
		} else {
			return valeur;
		}
	}
}
