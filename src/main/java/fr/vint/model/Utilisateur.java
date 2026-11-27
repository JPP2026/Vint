package fr.vint.model;

import jakarta.persistence.*;

import java.time.LocalDate;

/**
 * Entité qui représente un utilisateur de Vint
 * 
 */
@Entity //indique que cette classe est reliée à une table de la BDD
@Table(name = "utilisateurs")
public class Utilisateur {

    /** Clé primaire auto-incrémentée par la BDD */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //génère automatiquement l'incrémentation des id
    private Long id;

    /** Pseudo affiché sur le site */
    @Column(nullable = false, length = 50) //champ obligatoire et max 50 car.
    private String nom;

    /** Email */
    @Column(nullable = false, unique = true)//unique car c'est aussi l'id de connexion 
    private String email;

    /** Mot de passe hashé avec BCrypt*/
    @Column(nullable = false)
    private String motDePasse;

    /** Adresse de livraison */
    private String adresse;

    /** Numéro de téléphone */
    private String telephone;

    /** Date d'inscription (remplie automatiquement dès la création) */
    private LocalDate dateInscription = LocalDate.now();

    /**
     * Constructeur Utilisateur vide pour Hibernate
     */
    public Utilisateur() {
    }

    /**
     * Constructeur Utilisateur pour créer un utilisateur à l'inscription
     *
     * @param nom        pseudo
     * @param email      email (identifiant)
     * @param motDePasse mot de passe hashé
     */
    public Utilisateur(String nom, String email, String motDePasse) {
        this.nom = nom;
        this.email = email;
        this.motDePasse = motDePasse;
    }

    // --------------- Getters / Setters ---------------

    /** @return identifiant */
    public Long getId() {
        return id;
    }

    /** @param id identifiant */
    public void setId(Long id) {
        this.id = id;
    }

    /** @return  pseudo */
    public String getNom() {
        return nom;
    }

    /** @param nom  pseudo */
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

    /** @return mot de passe hashé */
    public String getMotDePasse() {
        return motDePasse;
    }

    /** @param motDePasse mot de passe hashé */
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
}
