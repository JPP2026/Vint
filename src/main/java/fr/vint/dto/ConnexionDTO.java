package fr.vint.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * Données du formulaire de connexion 
 * 
 * (intermédiaire pour comparer l'id et le mdp sans stocker dans la BDD)
 */
public class ConnexionDTO {

    /** Email à saisir */
    @NotBlank(message = "Renseigne ton email")
    private String email;

    /** Mot de passe à saisir */
    @NotBlank(message = "Renseigne ton mot de passe")
    private String motDePasse;


    //-------------Getter and Setters---------------
    /** @return l'email */
    public String getEmail() {
        return email;
    }

    /** @param email l'email */
    public void setEmail(String email) {
        this.email = email;
    }

    /** @return le mot de passe */
    public String getMotDePasse() {
        return motDePasse;
    }

    /** @param motDePasse le mot de passe */
    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
}
