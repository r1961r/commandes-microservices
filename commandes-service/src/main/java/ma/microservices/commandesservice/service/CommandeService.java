package ma.microservices.commandesservice.service;

import ma.microservices.commandesservice.entity.Commande;
import ma.microservices.commandesservice.config.CommandeConfig;
import ma.microservices.commandesservice.repository.CommandeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CommandeService {

    private final CommandeRepository repo;
    private final CommandeConfig config;

    public CommandeService(CommandeRepository repo, CommandeConfig config) {
        this.repo = repo;
        this.config = config;
    }

    public List<Commande> getAll() {
        return repo.findAll();
    }

    public Commande getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public Commande create(Commande c) {
        return repo.save(c);
    }

    public Commande update(Long id, Commande c) {
        c.setId(id);
        return repo.save(c);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    // Étude de cas (1) : commandes des N derniers jours (N venant de GitHub)
    public List<Commande> getLastDays() {
        int lastDays = config.getCommandesLast(); // ex : 10 ou 20
        LocalDate end = LocalDate.now();
        LocalDate start = end.minusDays(lastDays);
        return repo.findByDateBetween(start, end);
    }

    // Pour l’interface : les 10 plus anciennes
    public List<Commande> getOldest(int limit) {
        List<Commande> all = repo.findAllByOrderByDateAsc();
        if (all.size() <= limit) return all;
        return all.subList(0, limit);
    }
}
