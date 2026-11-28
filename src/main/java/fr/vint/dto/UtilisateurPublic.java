package fr.vint.dto;

import fr.vint.model.Utilisateur;

import java.io.Serializable;

/**
 * Version "publique" d'un utilisateur pendant qu'il est connecté sans les infos confidentielles
 */
public class UtilisateurPublic implements Serializable {

    /** Id de l'utilisateur (pour le retrouver dans la BDD) */
    private Long id;
    /** Pseudo */
    private String nom;
    /** Email (dans les paramètres) */
    private String email;

    //---------Constructeurs----------

    /** Constructeur vide */
    public UtilisateurPublic() {
    }

    /**
     * Constructeur UtilisateurPublic
     * @param id    id
     * @param nom   pseudo
     * @param email email
     */
    public UtilisateurPublic(Long id, String nom, String email) {
        this.id = id;
        this.nom = nom;
        this.email = email;
    }

    /**
     * Construit un UtilisateurPublic à partir de l'entité Utilisateur
     *
     * @param u l'entité
     * @return la version publique
     */
    public static UtilisateurPublic depuis(Utilisateur u) {
        return new UtilisateurPublic(u.getId(), u.getNom(), u.getEmail());
    }

    /**
     * Initiale du pseudo, pour l'avatar rond dans le header
     *
     * @return la première lettre en majuscule
     */
    public String getInitiale() {
        return nom == null || nom.isEmpty() ? "?" : nom.substring(0, 1).toUpperCase();
    }

    //------------Getter and Setters-------------

    /** @return l'id */
    public Long getId() {
        return id;
    }

    /** @param id l'id */
    public void setId(Long id) {
        this.id = id;
    }

    /** @return le pseudo */
    public String getNom() {
        return nom;
    }

    /** @param nom le pseudo */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /** @return l'email */
    public String getEmail() {
        return email;
    }

    /** @param email l'email */
    public void setEmail(String email) {
        this.email = email;
    }
}
