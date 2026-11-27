package fr.vint.model;

/**
 * Enumerateur pour l'état des vétements 
 */
public enum EtatVetement {
    NEUF("Neuf avec étiquette"),
    PARFAIT_ETAT("Parfait état"),
    TRES_BON_ETAT("Très bon état"),
    BON_ETAT("Bon état"),
    ETAT_CORRECT("État correct");

    /** 
     * Parametre
     */
    private final String libelle;

    /**
     * Constructeur
     * @param libelle texte lisible
     */
    EtatVetement(String libelle) {
        this.libelle = libelle;
    }

    /**
     * Fonction qui permet de renvoyer l'état d'un vêtement
     * @return le libellé à afficher
     */
    public String getLibelle() {
        return libelle;
    }
}
