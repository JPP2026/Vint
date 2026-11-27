package fr.vint.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Panier d'un utilisateur
 * règle des frais de port : 3,99 € par vendeur différent
 * une commande par vendeur car chaque vendeur envoie son colis
 */
@Entity
@Table(name = "paniers")
public class Panier {

    /** Frais de livraison pour un colis */
    public static final BigDecimal FRAIS_PAR_COLIS = new BigDecimal("3.99");

    /** Clé primaire */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * One-to-One avec l'utilisateur
     * possède la clé étrangère
     */
    @OneToOne(optional = false)
    @JoinColumn(name = "utilisateur_id", unique = true)
    private Utilisateur proprietaire;

    /**
     * Many-to-Many : un panier contient plusieurs annonces et une annonce peut être dans le panier de plusieurs personnes (premier qui paie)
     */
    @ManyToMany
    @JoinTable(
            name = "panier_annonces",
            joinColumns = @JoinColumn(name = "panier_id"),
            inverseJoinColumns = @JoinColumn(name = "annonce_id")
    )
    private List<Annonce> annonces = new ArrayList<>();

    /** Constructeur vide pour JPA */
    public Panier() {
    }

    /**
     * Crée le panier vide d'un utilisateur
     *
     * @param proprietaire l'utilisateur
     */
    public Panier(Utilisateur proprietaire) {
        this.proprietaire = proprietaire;
    }

    // ----------------------- Méthodes métier -----------------------

    /**
     * Ajoute une annonce si elle n'est pas déjà dedans (pas de quantité)
     *
     * @param annonce l'annonce à ajouter
     * @return true si elle a été ajoutée, false si elle y était déjà
     */
    public boolean ajouter(Annonce annonce) {
        if (annonces.contains(annonce)) {
            return false;
        }
        annonces.add(annonce);
        annonce.getPaniers().add(this);
        return true;
    }

    /**
     * Retire une annonce du panier
     *
     * @param annonce l'annonce à retirer
     */
    public void retirer(Annonce annonce) {
        annonces.remove(annonce);
        annonce.getPaniers().remove(this);
    }

    /**
     * Vide complètement le panier (après le paiement)
     */
    public void vider() {
        // on passe par une copie sinon on modifie la liste pendant qu'on la parcourt
        for (Annonce a : new ArrayList<>(annonces)) {
            retirer(a);
        }
    }

    /**
     * @return true si le panier est vide
     */
    public boolean estVide() {
        return annonces.isEmpty();
    }

    /**
     * Somme des prix des articles (sans les frais de port)
     *
     * @return le sous-total
     */
    public BigDecimal getSousTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for (Annonce a : annonces) {
            total = total.add(a.getPrix());
        }
        return total;
    }

    /**
     * Nombre de vendeurs différents dans le panier donc nombre de colis
     *
     * @return le nombre de vendeurs distincts
     */
    public long getNombreVendeurs() {
        return annonces.stream()
                .map(a -> a.getVendeur().getId())
                .distinct()
                .count();
    }

    /**
     * Frais de livraison = 3,99 € x nombre de vendeurs
     *
     * @return les frais de port
     */
    public BigDecimal getFraisLivraison() {
        return FRAIS_PAR_COLIS.multiply(BigDecimal.valueOf(getNombreVendeurs()));
    }

    /**
     * Total à payer
     *
     * @return sous-total + frais de port
     */
    public BigDecimal getTotal() {
        return getSousTotal().add(getFraisLivraison());
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

    /** @return le propriétaire */
    public Utilisateur getProprietaire() {
        return proprietaire;
    }

    /** @param proprietaire le propriétaire */
    public void setProprietaire(Utilisateur proprietaire) {
        this.proprietaire = proprietaire;
    }

    /** @return les annonces du panier */
    public List<Annonce> getAnnonces() {
        return annonces;
    }

    /** @param annonces les annonces du panier */
    public void setAnnonces(List<Annonce> annonces) {
        this.annonces = annonces;
    }
}
