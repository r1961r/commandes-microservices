package ma.microservices.commandesservice.web;

import lombok.RequiredArgsConstructor;
import ma.microservices.commandesservice.entity.Commande;
import ma.microservices.commandesservice.entity.Produit;
import ma.microservices.commandesservice.config.CommandeConfig;
import ma.microservices.commandesservice.service.CommandeService;
import ma.microservices.commandesservice.service.ProduitServiceClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/web/commandes")
@RequiredArgsConstructor
public class WebCommandeController {

    private final CommandeService commandeService;
    private final ProduitServiceClient produitServiceClient;
    private final CommandeConfig config;

    @GetMapping
    public String listCommandes(Model model) {

        List<Commande> commandes = commandeService.getAll();
        List<Commande> oldest = commandeService.getOldest(10);
        List<Commande> last = commandeService.getLastDays();   // ✅ commandes des N derniers jours
        List<Produit> produits = produitServiceClient.getAllProduits();

        model.addAttribute("commandes", commandes);
        model.addAttribute("oldestCommandes", oldest);
        model.addAttribute("lastCommandes", last);              // ✅ utilisé dans le bloc "N derniers jours"
        model.addAttribute("configValue", config.getCommandesLast()); // N (10, 20, etc.)
        model.addAttribute("produits", produits);               // pour le <select> dans le formulaire

        return "commandes"; // template Thymeleaf commandes.html
    }

    @PostMapping
    public String create(@ModelAttribute Commande commande) {
        commandeService.create(commande);
        return "redirect:/web/commandes";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        Commande commande = commandeService.getById(id);
        List<Produit> produits = produitServiceClient.getAllProduits();

        model.addAttribute("commande", commande);
        model.addAttribute("produits", produits);

        return "edit-commande"; // template edit-commande.html
    }

    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long id, @ModelAttribute Commande form) {
        commandeService.update(id, form);
        return "redirect:/web/commandes";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        commandeService.delete(id);
        return "redirect:/web/commandes";
    }
}