package fr.vint.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

/**
 * Caractéristiques du vetement vendu dans une annonce
 * Cette classe va permettre d'avoir acces à toutes les informations d'un vetement
 */
@Embeddable
public class Vetement {

    /** Taille */
    @Enumerated(EnumType.STRING)
    private TailleVetement taille;

    /** Couleur */
    @Enumerated(EnumType.STRING)
    private CouleurVetement couleur;

    /** État d'usure */
    @Enumerated(EnumType.STRING)
    private EtatVetement etat;

    /** Genre */
    @Enumerated(EnumType.STRING)
    private Genre genre;

    /** Catégorie (pull, chaussures...) */
    @Enumerated(EnumType.STRING)
    private TypeVetement type;

    /** Marque du vetement */
    private String marque;

    /** Constructeur vide  */
    public Vetement() {
    }

    /**
     * Constructeur
     * @param type    catégorie
     * @param taille  taille
     * @param couleur couleur
     * @param etat    état
     * @param genre   rayon
     * @param marque  marque 
     */
    public Vetement(TypeVetement type, TailleVetement taille, CouleurVetement couleur, EtatVetement etat, Genre genre, String marque) {
        this.type = type;
        this.taille = taille;
        this.couleur = couleur;
        this.etat = etat;
        this.genre = genre;
        this.marque = marque;
    }

    /** 
     * Fct qui renvoie la taille d'un vetement 
     * @return la taille 
     */
    public TailleVetement getTaille() {
        return taille;
    }

    /** 
     * Fct qui pertmet de changer la taille d'un vetement
     * @param taille la taille */
    public void setTaille(TailleVetement taille) {
        this.taille = taille;
    }

    /** 
     * Fct qui renvoie la couleur du vetement
     * @return la couleur 
     */
    public CouleurVetement getCouleur() {
        return couleur;
    }

    /**
     * Fct qui permet de changer la 
     * @param couleur la couleur 
     */
    public void setCouleur(CouleurVetement couleur) {
        this.couleur = couleur;
    }

    /** 
     * Fct qui permet de renvoyer l'etat du vetement
     * @return l'état 
     */
    public EtatVetement getEtat() {
        return etat;
    }

    /** 
     * Fct qui permet de changer l'etat d'un vetement
     * @param etat l'état 
     */
    public void setEtat(EtatVetement etat) {
        this.etat = etat;
    }

    /** 
     * Fct qui perrmet de renvoyer le genre du vetement
     * @return le genre 
     */
    public Genre getGenre() {
        return genre;
    }

    /**
     * Fct qui permet de changer le genre du vetement
     * @param genre le genre 
     */
    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    /** 
     * Fct qui permet de renvoyer le type de veteler
     * @return le type 
     */
    public TypeVetement getType() {
        return type;
    }

    /** 
     * Fct qui permet de changer le type de vetement
     * @param type le type 
     */
    public void setType(TypeVetement type) {
        this.type = type;
    }

    /** 
     * Fct qui permet de renvoyer la marque du vetement
     * @return la marque 
     */
    public String getMarque() {
        return marque;
    }

    /** 
     * Fct qui permet de changer la marque du vetement
     * @param marque la marque 
     */
    public void setMarque(String marque) {
        this.marque = marque;
    }
}
