package fr.vint.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Entité qui représente un utilisateur de Vint
 */
@Entity //indique que Utilisateur sera lié avec une table de la BDD
@Table(name = "utilisateurs")
public class Utilisateur {

    /** Clé primaire */
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto incrémentation des id 
    private Long id;

    /** Pseudo affiché sur le site */
    @Column(nullable = false, length = 50) //champ obligatoire
    private String nom;

    /** Email */
    @Column(nullable = false, unique = true) //unique car email est l'identifiant de connexion
    private String email;

    /** Mdp hashé avec BCrypt */
    @Column(nullable = false)
    private String motDePasse;

    /** Adresse de livraison (facultatif à l'inscription) */
    private String adresse;

    /** Numéro de téléphone (facultatif) */
    private String telephone;

    /** Date d'inscription (remplie automatiquement dès la création) */
    private LocalDate dateInscription = LocalDate.now();

    /**
     * Relation One-to-One avec le profil (possède la colonne profil_id)
     */
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)//modifications sont directement modifier dans profil
    @JoinColumn(name = "profil_id")
    private Profil profil;

    /**
     * Le panier de l'utilisateur
     */
    @OneToOne(mappedBy = "proprietaire", cascade = CascadeType.ALL, orphanRemoval = true)
    private Panier panier;

    /**
     * One-to-Many : toutes les annonces publiées par cet utilisateur
     */
    @OneToMany(mappedBy = "vendeur") // la clé étrangère est dans Annonce (attribut vendeur)
    @OrderBy("datePublication DESC")
    private List<Annonce> annonces = new ArrayList<>();


    /**
     * Constructeur vide pour JPA
     */
    public Utilisateur() {
    }

    /**
     * Constructeur pour créer un utilisateur à l'inscription
     *
     * @param nom        pseudo
     * @param email      email 
     * @param motDePasse mot de passe hashé
     */
    public Utilisateur(String nom, String email, String motDePasse) {
        this.nom = nom;
        this.email = email;
        this.motDePasse = motDePasse;
    }

    // ----------- Méthodes métier --------------

    /**
     * Si vendeur est en mode vacances -> n'apparait plus dans la boutique
     *
     * @return true si le mode vacances est activé
     */
    public boolean estEnVacances() {
        return profil != null && profil.isModeVacances();
    }

    // ---------- Getters / Setters ---------------

    /** @return identifiant */
    public Long getId() {
        return id;
    }

    /** @param id identifiant */
    public void setId(Long id) {
        this.id = id;
    }

    /** @return pseudo */
    public String getNom() {
        return nom;
    }

    /** @param nom pseudo */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /** @return email */
    public String getEmail() {
        return email;
    }

    /** @param email email */
    public void setEmail(String email) {
        this.email = email;
    }

    /** @return mdp hashé */
    public String getMotDePasse() {
        return motDePasse;
    }

    /** @param motDePasse mdp hashé */
    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    /** @return adresse */
    public String getAdresse() {
        return adresse;
    }

    /** @param adresse adresse */
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    /** @return téléphone */
    public String getTelephone() {
        return telephone;
    }

    /** @param telephone téléphone */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /** @return date d'inscription */
    public LocalDate getDateInscription() {
        return dateInscription;
    }

    /** @param dateInscription date d'inscription */
    public void setDateInscription(LocalDate dateInscription) {
        this.dateInscription = dateInscription;
    }

    /** @return  profil */
    public Profil getProfil() {
        return profil;
    }

    /**
     * Associe un profil à l'utilisateur et met à jour le lien retour
     * 
     * @param profil le profil
     */
    public void setProfil(Profil profil) {
        this.profil = profil;
        if (profil != null) {
            profil.setUtilisateur(this);
        }
    }

    /** @return le panier */
    public Panier getPanier() {
        return panier;
    }

    /** @param panier le panier */
    public void setPanier(Panier panier) {
        this.panier = panier;
    }

    /** @return les annonces publiées */
    public List<Annonce> getAnnonces() {
        return annonces;
    }

    /** @param annonces les annonces publiées */
    public void setAnnonces(List<Annonce> annonces) {
        this.annonces = annonces;
    }
}
