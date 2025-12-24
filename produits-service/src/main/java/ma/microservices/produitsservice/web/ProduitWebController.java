package ma.microservices.produitsservice.web;

import ma.microservices.produitsservice.entity.Produit;
import ma.microservices.produitsservice.repository.ProduitRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/web/produits")
public class ProduitWebController {

    private final ProduitRepository produitRepository;

    public ProduitWebController(ProduitRepository produitRepository) {
        this.produitRepository = produitRepository;
    }

    // LISTE
    @GetMapping
    public String listProduits(Model model) {
        model.addAttribute("produits", produitRepository.findAll());
        return "produits-list";   // templates/produits-list.html
    }

    // FORMULAIRE CREATION
    @GetMapping("/nouveau")
    public String showCreateForm(Model model) {
        model.addAttribute("produit", new Produit());
        model.addAttribute("titre", "Créer un produit");
        return "produit-form";    // templates/produit-form.html
    }

    // ENREGISTRER (CREATE + UPDATE)
    @PostMapping("/save")
    public String saveProduit(@ModelAttribute("produit") Produit produit) {
        produitRepository.save(produit);
        return "redirect:/web/produits";
    }

    // FORMULAIRE EDITION
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Produit produit = produitRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produit introuvable id=" + id));

        model.addAttribute("produit", produit);
        model.addAttribute("titre", "Modifier le produit");
        return "produit-form";
    }

    // DELETE
    @PostMapping("/delete/{id}")
    public String deleteProduit(@PathVariable Long id) {
        produitRepository.deleteById(id);
        return "redirect:/web/produits";
    }
}
