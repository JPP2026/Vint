package fr.vint.model;

/**
 * Enumerateur pour la couleur des vêtements 
 */
public enum CouleurVetement {
    BLEU("Bleu", "#3b6ea8"),
    VERT("Vert", "#2f7a5b"),
    JAUNE("Jaune", "#e8c547"),
    ORANGE("Orange", "#e08a3c"),
    ROUGE("Rouge", "#b8413a"),
    NOIR("Noir", "#222222"),
    VIOLET("Violet", "#7357a3"),
    BLANC("Blanc", "#f4f2ec"),
    GRIS("Gris", "#8d8d8d"),
    ROSE("Rose", "#e3a3b5"),
    MARRON("Marron", "#7a5236"),
    BEIGE("Beige", "#d9c7a7");

    /** 
     * Parametres 
     */
    private final String libelle;
    private final String hexa; //Code couleur pour le CSS

    /**
     * Constructeur
     * @param libelle text nom de la couleur
     * @param hexa code couleur CSS 
     */
    CouleurVetement(String libelle, String hexa) {
        this.libelle = libelle;
        this.hexa = hexa;
    }

    /** 
     * Fonction qui permet de renvoyer le nom de la couleur
     * @return le libellé à afficher 
     */
    public String getLibelle() {
        return libelle;
    }

    /** 
     * Fonction qui permet de renvoyer le code hexa de la couleur
     * @return le code hexa de la couleur 
     */
    public String getHexa() {
        return hexa;
    }
}
