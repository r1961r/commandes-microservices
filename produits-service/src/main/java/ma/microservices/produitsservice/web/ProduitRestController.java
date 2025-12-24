package ma.microservices.produitsservice.web;


import lombok.RequiredArgsConstructor;
import ma.microservices.produitsservice.entity.Produit;
import ma.microservices.produitsservice.repository.ProduitRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produits")
@RequiredArgsConstructor
public class ProduitRestController {

    private final ProduitRepository produitRepository;

    @GetMapping
    public List<Produit> getAll() {
        return produitRepository.findAll();
    }

    @GetMapping("/{id}")
    public Produit getById(@PathVariable Long id) {
        return produitRepository.findById(id).orElse(null);
    }

    // ⚠️ AJOUTE CETTE MÉTHODE POST ⚠️
    @PostMapping
    public Produit create(@RequestBody Produit produit) {
        return produitRepository.save(produit);
    }

    // Optionnel : Ajoute aussi PUT et DELETE si besoin
    @PutMapping("/{id}")
    public Produit update(@PathVariable Long id, @RequestBody Produit produit) {
        produit.setId(id);
        return produitRepository.save(produit);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        produitRepository.deleteById(id);
    }
}
