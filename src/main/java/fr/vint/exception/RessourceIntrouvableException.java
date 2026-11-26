package fr.vint.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Erreur qui est lancée quand on cherche un objet qui n'existe pas dans notre BDD.
 * Spring renvoie automatiquement ERROR 404
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class RessourceIntrouvableException extends RuntimeException {

    /**
     * @param message description de ce qui n'a pas été trouvé
     */
    public RessourceIntrouvableException(String message) {
        super(message);
    }
}
