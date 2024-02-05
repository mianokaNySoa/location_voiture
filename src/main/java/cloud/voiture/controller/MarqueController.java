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

import cloud.voiture.model.Marque;
import cloud.voiture.model.ResponseWrap;
import cloud.voiture.repository.MarqueRepository;


@RestController
@RequestMapping("/marque")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MarqueController {
    @Autowired
    private MarqueRepository marqueRepository;

    @GetMapping
    public ResponseWrap<List<Marque>> getAllMarque() {
        return ResponseWrap.success(marqueRepository.findByEtat(1));
    }

    @Secured({"ROLE_ADMIN"})
    @GetMapping("/{id}")
    public ResponseWrap<Marque> getMarqueById(@PathVariable int id) {
        return marqueRepository.findById(id)
                .map(marque -> ResponseWrap.success(marque))
                .orElseGet(() -> ResponseWrap.error("id not found"));

    }

    @Secured({"ROLE_ADMIN"})
    @PostMapping
    public ResponseWrap<Marque> addMarque(@RequestBody Marque marque) {
        return ResponseWrap.success(marqueRepository.save(marque));
    }

    @Secured({"ROLE_ADMIN"})
    @PutMapping("/{id}")
    public ResponseWrap<Marque> updateMarque(@PathVariable int id, @RequestBody Marque marqueModif) {
        if (marqueRepository.existsById(id)) {
            marqueModif.setId(id);
            Marque maj = marqueRepository.save(marqueModif);
            return ResponseWrap.success(maj);
        }
        return ResponseWrap.error("id not found");
    }

    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("/{id}")
    public ResponseWrap<String> deleteMarque(@PathVariable int id) {
        Optional<Marque> optionalMarque = marqueRepository.findById(id);

        if (optionalMarque.isPresent()) {
            Marque existingMarque = optionalMarque.get();
            existingMarque.setEtat(0);
            marqueRepository.save(existingMarque);
            return ResponseWrap.success("deleted successfully");
        } else {
            return ResponseWrap.error("id not found");
        }
    }

}
