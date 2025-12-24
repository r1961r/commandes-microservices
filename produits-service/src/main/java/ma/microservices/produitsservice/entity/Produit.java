package ma.microservices.produitsservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Produit {  // MAJUSCULE ICI

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private Double prix;
    private String description; // AJOUTEZ ce champ important

    // Constructeur sans id (pour faciliter les tests)
    public Produit(String nom, Double prix, String description) {
        this.nom = nom;
        this.prix = prix;
        this.description = description;
    }
}
