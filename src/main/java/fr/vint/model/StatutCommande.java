package fr.vint.model;

/**
 * Les étapes de suivi d'une commande 
 * Le vendeur fait avancer la commande étape par étape
 */
public enum StatutCommande {
    PASSEE("Commande passée"),
    EN_PREPARATION("En préparation"),
    EXPEDIEE("Expédiée"),
    LIVREE("Livrée");

    /** Texte affiché */
    private final String libelle;

    /**
     * @param libelle texte lisible
     */
    StatutCommande(String libelle) {
        this.libelle = libelle;
    }

    /** @return le libellé à afficher */
    public String getLibelle() {
        return libelle;
    }

    /**
     * Donne l'étape suivante
     *
     * @return le statut suivant, ou null si la commande est déjà livrée
     */
    public StatutCommande suivant() {
        if (this == LIVREE) {
            return null;
        }
        return values()[this.ordinal() + 1];
    }
}
