package fr.vint.repository;

import fr.vint.model.Annonce;
import fr.vint.model.StatutAnnonce;
import fr.vint.model.TypeVetement;
import fr.vint.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Accès BDD pour les annonce
 */
@Repository
public interface AnnonceRepository extends JpaRepository<Annonce, Long> {

    /**
     * Toutes les annonces d'un vendeur, les plus récentes
     * @param vendeur le vendeur
     * @return ses annonces
     */
    List<Annonce> findByVendeurOrderByDatePublicationDesc(Utilisateur vendeur);

    /**
     * Liste des annonces encore en vente d'un vendeur
     * @param vendeur le vendeur
     * @param statut  disponible
     * @return ses annonces avec ce statut
     */
    List<Annonce> findByVendeurAndStatutOrderByDatePublicationDesc(Utilisateur vendeur, StatutAnnonce statut);

    /**
     * Nombre d'annonces disponibles par catégorie
     * @param type   la catégorie
     * @param statut le statut
     * @return le nb d'annonces
     */
    long countByVetementTypeAndStatut(TypeVetement type, StatutAnnonce statut);
}
