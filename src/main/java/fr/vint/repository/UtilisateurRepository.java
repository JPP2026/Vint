package fr.vint.repository;

import fr.vint.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Accès BDD pour les utilisateurs
 */
@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

    /**
     * Cherche un utilisateur par son email
     *
     * @param email email saisi
     * @return utilisateur s'il existe, sinon Optional vide
     */
    Optional<Utilisateur> findByEmail(String email);

    /**
     * Vérifie si un email est déjà pris (pour l'inscription)
     *
     * @param email email à tester
     * @return true si un compte existe déjà avec cet email
     */
    boolean existsByEmail(String email);
}
