package fr.vint.model;

/**
 * Enumerateur pour les genres
 */
public enum Genre {
    FEMME("Femme"),
    HOMME("Homme"),
    MIXTE("Mixte"),
    ENFANT("Enfant");

    /** 
     * Paramétre
     */
    private final String libelle;

    /**
     * Constructeur
     * @param libelle texte lisible
     */
    Genre(String libelle) {
        this.libelle = libelle;
    }

    /** 
     * Fonction qui permet de renvoyer le libelle
     * @return le libellé à afficher
     */
    public String getLibelle() {
        return libelle;
    }
}
