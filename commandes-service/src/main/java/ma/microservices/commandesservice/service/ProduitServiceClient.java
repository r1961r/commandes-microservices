package ma.microservices.commandesservice.service;


import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import ma.microservices.commandesservice.entity.Produit;
import ma.microservices.commandesservice.feign.ProduitClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProduitServiceClient {

    private final ProduitClient produitClient;

    public List<Produit> getAllProduits() {
        // ⚠️ surtout pas de try/catch qui fait return Collections.emptyList()
        return produitClient.getAllProduits();
    }

    @CircuitBreaker(name = "produitService", fallbackMethod = "fallbackProduit")
    public Produit getProduit(Long id) {
        return produitClient.getProduit(id);
    }

    private Produit fallbackProduit(Long id, Throwable ex) {
        Produit p = new Produit();
        p.setId(id);
        p.setNom("Produit indisponible (fallback)");
        p.setPrix(0.0);
        return p;
    }
}