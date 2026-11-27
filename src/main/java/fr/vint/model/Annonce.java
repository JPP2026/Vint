package fr.vint.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Entité qui représente une annonce de vente publiée par un utilisatreur
 * Elle est relie au vendeur, aux favoris, aux paniers et à la commande
 */
@Entity
@Table(name = "annonces")
public class Annonce {

    /**
     * Clé primaire auto-incrémentée
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Titre de rt de l'annonce
     */
    @Column(nullable = false, length = 80)
    private String titre;

    /**
     * Description libre
     */
    @Column(length = 2000)
    private String description;

    /**
     * Prix en euros
     * BigDecimal et pas double à cause des erreurs d'arrondi
     */
    @Column(nullable = false, precision = 8, scale = 2)
    private BigDecimal prix;

    /**
     * Nom du fichier photo dans uploads
     */
    private String photo;

    /**
     * Caractéristiques du vêtement rangées dans les colonnes de cette table
     */
    @Embedded
    private Vetement vetement = new Vetement();

    /**
     * Statut de l'annonce
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatutAnnonce statut = StatutAnnonce.DISPONIBLE;

    /**
     * Date de mise en ligne
     */
    private LocalDateTime datePublication = LocalDateTime.now();

    /**
     * Rel Many-to-One avec le vendeur
     * Ce côté possède la clé étrangère vendeur_id
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "vendeur_id")
    private Utilisateur vendeur;

    /**
     * Constructeur vide obligatoire pour JPA
     */
    public Annonce() {
    }

    /**
     * Constructeur
     * @param titre titre
     * @param description description
     * @param prix prix en euros
     * @param vetement caractéristiques du vêtement
     * @param vendeur util qui vend
     */
    public Annonce(String titre, String description, BigDecimal prix, Vetement vetement, Utilisateur vendeur) {
        this.titre = titre;
        this.description = description;
        this.prix = prix;
        this.vetement = vetement;
        this.vendeur = vendeur;
    }

    /**
     * Fct qui permet de savoir si l'annonce est achetable
     * Elle doit etre disponible et son vendeur pas en vacances
     * @return true si on peut l'ajouter au panier
     */
    public boolean estAchetable() {
        return statut == StatutAnnonce.DISPONIBLE && !vendeur.estEnVacances();
    }

    /**
     * Fct qui permet de savoir si un utilisateur est le vendeur de l'annonce
     * Sert à vérifier le droit de modifier ou supprimer
     * @param utilisateurId id de l'util connecté
     * @return true si c'est bien le vendeur
     */
    public boolean appartientA(Long utilisateurId) {
        return utilisateurId != null && vendeur != null && utilisateurId.equals(vendeur.getId());
    }

    /**
     * Fct qui permet de renvoyer le titre avec la marque si elle existe
     * @return le titre avec la marque
     */
    public String getTitreComplet() {
        if (vetement.getMarque() == null || vetement.getMarque().isBlank()) {
            return titre;
        }
        return titre + " · " + vetement.getMarque();
    }

    /**
     * Fct qui permet de renvoyer l'id
     * @return l'id
     */
    public Long getId() {
        return id;
    }

    /**
     * Fct qui permet de modifier l'id
     * @param id l'id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Fct qui permet de renvoyer le titre
     * @return le titre
     */
    public String getTitre() {
        return titre;
    }

    /**
     * Fct qui permet de modifier le titre
     * @param titre le titre
     */
    public void setTitre(String titre) {
        this.titre = titre;
    }

    /**
     * Fct qui permet de renvoyer la description
     * @return la description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Fct qui permet de modifier la description
     * @param description la description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Fct qui permet de renvoyer le prix
     * @return le prix
     */
    public BigDecimal getPrix() {
        return prix;
    }

    /**
     * Fct qui permet de modifier le prix
     * @param prix le prix
     */
    public void setPrix(BigDecimal prix) {
        this.prix = prix;
    }

    /**
     * Fct qui permet de renvoyer le nom du fichier photo
     * @return le nom du fichier photo
     */
    public String getPhoto() {
        return photo;
    }

    /**
     * Fct qui permet de modifier le nom du fichier photo
     * @param photo le nom du fichier photo
     */
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    /**
     * Fct qui permet de renvoyer le vêtement
     * @return le vêtement
     */
    public Vetement getVetement() {
        return vetement;
    }

    /**
     * Fct qui permet de modifier le vêtement
     * @param vetement le vêtement
     */
    public void setVetement(Vetement vetement) {
        this.vetement = vetement;
    }

    /**
     * Fct qui permet de renvoyer le statut
     * @return le statut
     */
    public StatutAnnonce getStatut() {
        return statut;
    }

    /**
     * Fct qui permet de modifier le statut
     * @param statut le statut
     */
    public void setStatut(StatutAnnonce statut) {
        this.statut = statut;
    }

    /**
     * Fct qui permet de renvoyer la date de publication
     * @return la date de publication
     */
    public LocalDateTime getDatePublication() {
        return datePublication;
    }

    /**
     * Fct qui permet de modifier la date de publication
     * @param datePublication la date de publication
     */
    public void setDatePublication(LocalDateTime datePublication) {
        this.datePublication = datePublication;
    }

    /**
     * Fct qui permet de renvoyer le vendeur
     * @return le vendeur
     */
    public Utilisateur getVendeur() {
        return vendeur;
    }

    /**
     * Fct qui permet de modifier le vendeur
     * @param vendeur le vendeur
     */
    public void setVendeur(Utilisateur vendeur) {
        this.vendeur = vendeur;
    }
}
