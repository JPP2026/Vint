package fr.vint.repository;

import fr.vint.model.Panier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Accès BDD pour les paniers
 */
@Repository
public interface PanierRepository extends JpaRepository<Panier, Long> {

    /**
     * Récupère le panier d'un utilisateur à partir de son id
     *
     * @param utilisateurId id du propriétaire
     * @return le panier s'il existe
     */
    Optional<Panier> findByProprietaire_Id(Long utilisateurId);
}
