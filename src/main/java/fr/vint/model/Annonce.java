package fr.vint.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Une annonce de vente publiée par un utilisateur
 * reliée au vendeur (N-1), aux utilisateurs qui l'ont en favori (N-N), aux paniers (N-N) et à la commande qui l'a achetée (N-1)
 */
@Entity
@Table(name = "annonces")
public class Annonce {

    /** Clé primaire */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Titre court */
    @Column(nullable = false, length = 80)
    private String titre;

    /** Description libre */
    @Column(length = 2000)
    private String description;

    /**
     * Prix en euros
     * on utilise BigDecimal pour eviter les erreurs d'arrondi
     */
    @Column(nullable = false, precision = 8, scale = 2)
    private BigDecimal prix;

    /** Nom du fichier de la photo dans le dossier uploads/ (null si pas de photo) */
    private String photo;

    /** Les caractéristiques du vêtement, rangées dans les colonnes de cette table */
    @Embedded
    private Vetement vetement = new Vetement();

    /** DISPONIBLE ou VENDUE */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatutAnnonce statut = StatutAnnonce.DISPONIBLE;

    /** Date de mise en ligne, sert pour le tri "plus récentes" */
    private LocalDateTime datePublication = LocalDateTime.now();

    /**
     * Many-to-One : plusieurs annonces pour un seul vendeur
     * possède la clé étrangère vendeur_id
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "vendeur_id")
    private Utilisateur vendeur;

    /** Côté inverse du Many-to-Many panier/annonces */
    @ManyToMany(mappedBy = "annonces")
    private Set<Panier> paniers = new HashSet<>();

    /** Constructeur vide pour JPA */
    public Annonce() {
    }

    /**
     * Constructeur pratique (utilisé surtout pour les données de démo)
     *
     * @param titre       titre
     * @param description description
     * @param prix        prix en euros
     * @param vetement    caractéristiques du vêtement
     * @param vendeur     utilisateur qui vend
     */
    public Annonce(String titre, String description, BigDecimal prix, Vetement vetement, Utilisateur vendeur) {
        this.titre = titre;
        this.description = description;
        this.prix = prix;
        this.vetement = vetement;
        this.vendeur = vendeur;
    }

    // ----------------------- Méthodes métier -----------------------

    /**
     * Une annonce est achetable si elle n'est pas déjà vendue et que son vendeur n'est pas en vacances
     *
     * @return true si on peut l'ajouter au panier
     */
    public boolean estAchetable() {
        return statut == StatutAnnonce.DISPONIBLE && !vendeur.estEnVacances();
    }

    /**
     * Vérifie si l'utilisateur donné est le vendeur de l'annonce (pour droit de la modifier/supprimer)
     *
     * @param utilisateurId id de l'utilisateur connecté
     * @return true si c'est bien lui le vendeur
     */
    public boolean appartientA(Long utilisateurId) {
        return utilisateurId != null && vendeur != null && utilisateurId.equals(vendeur.getId());
    }

    /**
     * Petit titre complet pour l'affichage : on colle la marque si elle existe.
     *
     * @return le titre avec la marque
     */
    public String getTitreComplet() {
        if (vetement.getMarque() == null || vetement.getMarque().isBlank()) {
            return titre;
        }
        return titre + " · " + vetement.getMarque();
    }

    // ----------------------- Getters / Setters -----------------------

    /** @return l'identifiant */
    public Long getId() {
        return id;
    }

    /** @param id l'identifiant */
    public void setId(Long id) {
        this.id = id;
    }

    /** @return le titre */
    public String getTitre() {
        return titre;
    }

    /** @param titre le titre */
    public void setTitre(String titre) {
        this.titre = titre;
    }

    /** @return la description */
    public String getDescription() {
        return description;
    }

    /** @param description la description */
    public void setDescription(String description) {
        this.description = description;
    }

    /** @return le prix */
    public BigDecimal getPrix() {
        return prix;
    }

    /** @param prix le prix */
    public void setPrix(BigDecimal prix) {
        this.prix = prix;
    }

    /** @return le nom du fichier photo */
    public String getPhoto() {
        return photo;
    }

    /** @param photo le nom du fichier photo */
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    /** @return le vêtement */
    public Vetement getVetement() {
        return vetement;
    }

    /** @param vetement le vêtement */
    public void setVetement(Vetement vetement) {
        this.vetement = vetement;
    }

    /** @return le statut */
    public StatutAnnonce getStatut() {
        return statut;
    }

    /** @param statut le statut */
    public void setStatut(StatutAnnonce statut) {
        this.statut = statut;
    }

    /** @return la date de publication */
    public LocalDateTime getDatePublication() {
        return datePublication;
    }

    /** @param datePublication la date de publication */
    public void setDatePublication(LocalDateTime datePublication) {
        this.datePublication = datePublication;
    }

    /** @return le vendeur */
    public Utilisateur getVendeur() {
        return vendeur;
    }

    /** @param vendeur le vendeur */
    public void setVendeur(Utilisateur vendeur) {
        this.vendeur = vendeur;
    }

    /** @return les paniers qui contiennent l'annonce */
    public Set<Panier> getPaniers() {
        return paniers;
    }

    /** @param paniers les paniers qui contiennent l'annonce */
    public void setPaniers(Set<Panier> paniers) {
        this.paniers = paniers;
    }
}
