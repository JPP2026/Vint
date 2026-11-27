package fr.vint.model;

/**
<<<<<<< HEAD
 * Enumerateur qui permet d'avoir les differents types de vétement
=======
 * Enumérateur qui permet d'avoir les differents types de vétement
>>>>>>> origin/main
 */
public enum TypeVetement {
    PULL("Pull"),
    BAS("Bas"),
    HAUT("Haut"),
    CHAUSSURES("Chaussures"),
    SOUS_VETEMENT("Sous-vêtement"),
    ACCESSOIRE("Accessoire"),
    ROBE("Robe"),
    MANTEAU("Manteau");

    /**
     * Texte affiché
     */
    private final String libelle;

    /**
     * Constructeur de l'enum
     * @param libelle est un texte qui est lisible
     */
    TypeVetement(String libelle) {
        this.libelle = libelle;
    }

    /**
     * Fonction qui retourne le libelle
     * @return le libelle (texte: string)
     */
    public String getLibelle() {
        return libelle;
    }
}
