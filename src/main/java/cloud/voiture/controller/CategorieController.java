package cloud.voiture.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cloud.voiture.model.Categorie;
import cloud.voiture.model.ResponseWrap;
import cloud.voiture.repository.CategorieRepository;

@RestController
@RequestMapping("/categorie")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategorieController {
    @Autowired
    private CategorieRepository categorieRepository;

    @GetMapping
    public ResponseWrap<List<Categorie>> getAllCategorie() {
        return ResponseWrap.success(categorieRepository.findByEtat(1));
    }

    @Secured({"ROLE_ADMIN"})
    @GetMapping("/{id}")
    public ResponseWrap<Categorie> getCategorieById(@PathVariable int id) {
        return categorieRepository.findById(id)
                .map(categorie -> ResponseWrap.success(categorie))
                .orElseGet(() -> ResponseWrap.error("id not found"));

    }

    @Secured({"ROLE_ADMIN"})
    @PostMapping
    public ResponseWrap<Categorie> addCategorie(@RequestBody Categorie categorie) {
        return ResponseWrap.success(categorieRepository.save(categorie));
    }

    @Secured({"ROLE_ADMIN"})
    @PutMapping("/{id}")
    public ResponseWrap<Categorie> updateCategorie(@PathVariable int id, @RequestBody Categorie categorieModif) {
        if (categorieRepository.existsById(id)) {
            categorieModif.setId(id);
            Categorie maj = categorieRepository.save(categorieModif);
            return ResponseWrap.success(maj);
        }
        return ResponseWrap.error("id not found");
    }

    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("/{id}")
    public ResponseWrap<String> deleteCategorie(@PathVariable int id) {
        Optional<Categorie> optionalCategorie = categorieRepository.findById(id);

        if (optionalCategorie.isPresent()) {
            Categorie existingCategorie = optionalCategorie.get();
            existingCategorie.setEtat(0);
            categorieRepository.save(existingCategorie);
            return ResponseWrap.success("deleted successfully");
        } else {
            return ResponseWrap.error("id not found");
        }
    }

}
