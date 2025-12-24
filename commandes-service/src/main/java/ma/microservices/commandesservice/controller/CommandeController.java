package ma.microservices.commandesservice.controller;

import ma.microservices.commandesservice.service.CommandeService;
import ma.microservices.commandesservice.service.ProduitServiceClient;
import ma.microservices.commandesservice.entity.Produit;
import ma.microservices.commandesservice.entity.Commande;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/commandes")
@RequiredArgsConstructor
public class CommandeController {

    private final CommandeService commandeService;
    private final ProduitServiceClient produitServiceClient;

    @GetMapping
    public List<Commande> getAll() {
        return commandeService.getAll();
    }

    @GetMapping("/{id}")
    public Commande getById(@PathVariable Long id) {
        return commandeService.getById(id);
    }

    @PostMapping
    public Commande create(@RequestBody Commande commande) {
        return commandeService.create(commande);
    }

    @PutMapping("/{id}")
    public Commande update(@PathVariable Long id, @RequestBody Commande commande) {
        return commandeService.update(id, commande);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        commandeService.delete(id);
    }

    @GetMapping("/last")
    public List<Commande> getLast() {
        return commandeService.getLastDays();
    }

    @GetMapping("/oldest")
    public List<Commande> getOldest(@RequestParam(defaultValue = "10") int limit) {
        return commandeService.getOldest(limit);
    }

    // Endpoint qui renvoie commande + produit associé
    @GetMapping("/{id}/details")
    public Map<String, Object> getCommandeAvecProduit(@PathVariable Long id) {
        Commande commande = commandeService.getById(id);
        Produit produit = null;

        if (commande != null && commande.getIdProduit() != null) {
            produit = produitServiceClient.getProduit(commande.getIdProduit());
        }

        return Map.of(
                "commande", commande,
                "produit", produit
        );
    }
}