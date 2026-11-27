package fr.vint.model;

/**
 * Enumérateur pour les tailles possibles d'un vêtement
 */
public enum TailleVetement {
    XS,
    S,
    M,
    L,
    XL,
    XXL,
    UNIQUE;

    /**
     * Fontion qui permet d'afficher le nom de la taille, sauf pour UNIQUE
     * @return le libellé de la taille
     */
    public String getLibelle() {
        if (this == UNIQUE) {
        return "Taille unique";
        }
        return name();
    }
}
