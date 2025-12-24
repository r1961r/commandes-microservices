package ma.microservices.commandesservice.repository;


import ma.microservices.commandesservice.entity.Commande;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface CommandeRepository extends JpaRepository<Commande, Long> {

    List<Commande> findByDateBetween(LocalDate start, LocalDate end);

    List<Commande> findAllByOrderByDateAsc();
}
