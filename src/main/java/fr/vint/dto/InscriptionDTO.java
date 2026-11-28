package fr.vint.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Données du formulaire d'inscription 
 * 
 */
public class InscriptionDTO {

    /** Pseudo de l'utilisateur */
    @NotBlank(message = "Choisis un pseudo")
    @Size(min = 2, max = 50, message = "Le pseudo doit faire entre 2 et 50 caractères")
    private String nom;

    /** Email de connexion */
    @NotBlank(message = "L'email est obligatoire")
    @Email(message = "Cet email n'a pas l'air valide")
    private String email;

    /** Mot de passe */
    @NotBlank(message = "Le mot de passe est obligatoire")
    @Size(min = 8, message = "8 caractères minimum")
    private String motDePasse;

    /** Confirmation du mot de passe choisi */
    @NotBlank(message = "Confirme ton mot de passe")
    private String confirmationMotDePasse;

    /** @return pseudo */
    public String getNom() {
        return nom;
    }

    /** @param nom pseudo */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /** @return email */
    public String getEmail() {
        return email;
    }

    /** @param email email */
    public void setEmail(String email) {
        this.email = email;
    }

    /** @return mot de passe */
    public String getMotDePasse() {
        return motDePasse;
    }

    /** @param motDePasse le mot de passe */
    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    /** @return confirmation */
    public String getConfirmationMotDePasse() {
        return confirmationMotDePasse;
    }

    /** @param confirmationMotDePasse confirmation du mot de passe*/
    public void setConfirmationMotDePasse(String confirmationMotDePasse) {
        this.confirmationMotDePasse = confirmationMotDePasse;
    }
}
