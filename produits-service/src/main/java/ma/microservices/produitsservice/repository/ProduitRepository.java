package ma.microservices.produitsservice.repository;

import ma.microservices.produitsservice.entity.Produit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProduitRepository extends JpaRepository<Produit, Long> {
}
