package fr.vint.repository;

import fr.vint.model.Commande;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Accès BDD pour les commandes
 */
@Repository
public interface CommandeRepository extends JpaRepository<Commande, Long> {

    /**
     * Les commandes passées par un acheteur
     *
     * @param acheteurId id de l'acheteur
     * @return ses commandes, les plus récentes d'abord
     */
    List<Commande> findByAcheteur_IdOrderByDateCommandeDesc(Long acheteurId);

    /**
     * Les commandes reçues par un vendeur
     *
     * @param vendeurId id du vendeur
     * @return ses ventes, les plus récentes d'abord
     */
    List<Commande> findByVendeur_IdOrderByDateCommandeDesc(Long vendeurId);
}
