package fr.vint.model;

import jakarta.persistence.*;

/**
 * Profil public d'un utilisateur 
 */
@Entity 
@Table(name = "profils")
public class Profil {

    /** Clé primaire */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** description sur la page du vendeur */
    @Column(length = 500)
    private String bio;

    /** Ville */
    private String ville;

    /**
     * true : les annonces du vendeur sont masquées sur sa page car impossible d'expédier et on peut plus les ajouter au panier
     */
    private boolean modeVacances = false;

    /**
     * Relation OnetoOne avec Utilisateur
     * (créer une colonne profil_id dans utilisateur)
     */
    @OneToOne(mappedBy = "profil")
    private Utilisateur utilisateur;

    /** Constructeur vide pour JPA */
    public Profil() {
    }

    /** @return identifiant */
    public Long getId() {
        return id;
    }

    /** @param id identifiant */
    public void setId(Long id) {
        this.id = id;
    }

    /** @return bio */
    public String getBio() {
        return bio;
    }

    /** @param bio  bio */
    public void setBio(String bio) {
        this.bio = bio;
    }

    /** @return ville */
    public String getVille() {
        return ville;
    }

    /** @param ville ville */
    public void setVille(String ville) {
        this.ville = ville;
    }

    /** @return true si le mode vacances est activé */
    public boolean isModeVacances() {
        return modeVacances;
    }

    /** @param modeVacances active ou désactive le mode vacances */
    public void setModeVacances(boolean modeVacances) {
        this.modeVacances = modeVacances;
    }

    /** @return utilisateur */
    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    /** @param utilisateur utilisateur */
    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
}
