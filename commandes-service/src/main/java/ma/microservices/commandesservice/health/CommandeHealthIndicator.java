package ma.microservices.commandesservice.health;


import lombok.RequiredArgsConstructor;

import org.springframework.boot.health.contributor.Health;
import org.springframework.boot.health.contributor.HealthIndicator;
import org.springframework.stereotype.Component;
import ma.microservices.commandesservice.repository.CommandeRepository;

@Component("commandes")
@RequiredArgsConstructor
public class CommandeHealthIndicator implements HealthIndicator {

    private final CommandeRepository commandeRepository;

    @Override
    public Health health() {
        long count = commandeRepository.count();

        if (count > 0) {
            return Health.up()
                    .withDetail("message", "Le service commandes est fonctionnel")
                    .withDetail("nombre_de_commandes", count)
                    .build();
        } else {
            return Health.down()
                    .withDetail("message", "Aucune commande trouvée")
                    .build();
        }
    }
}