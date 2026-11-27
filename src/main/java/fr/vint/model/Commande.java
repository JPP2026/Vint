package fr.vint.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Entité qui représente une commande, un colis envoyé par un vendeur à un acheteur
 * Si le panier contient des articles de 2 vendeurs, le paiement crée 2 commandes
 */
@Entity
@Table(name = "commandes")
public class Commande {

    /** Clé primaire */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Celui qui achète */
    @ManyToOne(optional = false)
    @JoinColumn(name = "acheteur_id")
    private Utilisateur acheteur;

    /** Celui qui vend et qui doit envoyer le colis */
    @ManyToOne(optional = false)
    @JoinColumn(name = "vendeur_id")
    private Utilisateur vendeur;

    /**
     * One-to-Many : les annonces achetées dans cette commande
     */
    @OneToMany(mappedBy = "commande") // clé étrangère commande_id dans tableau annonces
    private List<Annonce> annonces = new ArrayList<>();

    /** Date à laquelle l'acheteur a payé */
    private LocalDateTime dateCommande = LocalDateTime.now();

    /** Étape du suivi */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatutCommande statut = StatutCommande.PASSEE;

    /** Somme des prix des articles au moment de l'achat */
    @Column(precision = 8, scale = 2)
    private BigDecimal montantArticles;

    /** Frais de port payés pour ce colis */
    @Column(precision = 8, scale = 2)
    private BigDecimal fraisLivraison;

    /** Copie de l'adresse de l'acheteur au moment de la commande */
    private String adresseLivraison;

    /** Constructeur vide pour JPA */
    public Commande() {
    }

    /**
     * Crée une commande vide entre un acheteur et un vendeur
     *
     * @param acheteur l'acheteur
     * @param vendeur  le vendeur
     */
    public Commande(Utilisateur acheteur, Utilisateur vendeur) {
        this.acheteur = acheteur;
        this.vendeur = vendeur;
        this.adresseLivraison = acheteur.getAdresse();
    }

    // -------------------- Méthodes métier --------------------

    /**
     * Ajoute une annonce à la commande et la marque comme vendue
     *
     * @param annonce l'annonce achetée
     */
    public void ajouterAnnonce(Annonce annonce) {
        annonces.add(annonce);
        annonce.setCommande(this);
        annonce.setStatut(StatutAnnonce.VENDUE);
    }

    /**
     * @return montant total payé (articles + port)
     */
    public BigDecimal getTotal() {
        BigDecimal articles = montantArticles == null ? BigDecimal.ZERO : montantArticles;
        BigDecimal port = fraisLivraison == null ? BigDecimal.ZERO : fraisLivraison;
        return articles.add(port);
    }

    /**
     * Fait passer la commande à l'étape suivante
     *
     * @return true si ça a avancé, false si elle était déjà livrée
     */
    public boolean avancer() {
        StatutCommande suivant = statut.suivant();
        if (suivant == null) {
            return false;
        }
        statut = suivant;
        return true;
    }

    /**
     * Sert pour la frise de suivi
     *
     * @param etape l'étape à tester
     * @return true si la commande est à cette étape ou plus loin
     */
    public boolean etapeAtteinte(StatutCommande etape) {
        return statut.ordinal() >= etape.ordinal();
    }

    // -------------------- Getters / Setters --------------------

    /** @return l'identifiant */
    public Long getId() {
        return id;
    }

    /** @param id l'identifiant */
    public void setId(Long id) {
        this.id = id;
    }

    /** @return l'acheteur */
    public Utilisateur getAcheteur() {
        return acheteur;
    }

    /** @param acheteur l'acheteur */
    public void setAcheteur(Utilisateur acheteur) {
        this.acheteur = acheteur;
    }

    /** @return le vendeur */
    public Utilisateur getVendeur() {
        return vendeur;
    }

    /** @param vendeur le vendeur */
    public void setVendeur(Utilisateur vendeur) {
        this.vendeur = vendeur;
    }

    /** @return les annonces achetées */
    public List<Annonce> getAnnonces() {
        return annonces;
    }

    /** @param annonces les annonces achetées */
    public void setAnnonces(List<Annonce> annonces) {
        this.annonces = annonces;
    }

    /** @return la date de commande */
    public LocalDateTime getDateCommande() {
        return dateCommande;
    }

    /** @param dateCommande la date de commande */
    public void setDateCommande(LocalDateTime dateCommande) {
        this.dateCommande = dateCommande;
    }

    /** @return le statut */
    public StatutCommande getStatut() {
        return statut;
    }

    /** @param statut le statut */
    public void setStatut(StatutCommande statut) {
        this.statut = statut;
    }

    /** @return le montant des articles */
    public BigDecimal getMontantArticles() {
        return montantArticles;
    }

    /** @param montantArticles le montant des articles */
    public void setMontantArticles(BigDecimal montantArticles) {
        this.montantArticles = montantArticles;
    }

    /** @return les frais de livraison */
    public BigDecimal getFraisLivraison() {
        return fraisLivraison;
    }

    /** @param fraisLivraison les frais de livraison */
    public void setFraisLivraison(BigDecimal fraisLivraison) {
        this.fraisLivraison = fraisLivraison;
    }

    /** @return l'adresse de livraison */
    public String getAdresseLivraison() {
        return adresseLivraison;
    }

    /** @param adresseLivraison l'adresse de livraison */
    public void setAdresseLivraison(String adresseLivraison) {
        this.adresseLivraison = adresseLivraison;
    }
}
