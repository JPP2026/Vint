package fr.vint.exception;

/**
 * Classe qui gère quand une règle n'est pas respectée (email déjà pris, mdp ne correspondent pas, verification de la connexion,etc..)
 * 
 */
public class VintException extends RuntimeException {

    /**
     * @param message message lisible par l'utilisateur
     */
    public VintException(String message) {
        super(message);
    }
}
