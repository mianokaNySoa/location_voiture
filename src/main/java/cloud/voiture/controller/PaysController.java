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

import cloud.voiture.model.Pays;
import cloud.voiture.model.ResponseWrap;
import cloud.voiture.repository.PaysRepository;


@RestController
@RequestMapping("/pays")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PaysController {
    @Autowired
    private PaysRepository paysRepository;

    @GetMapping
    public ResponseWrap<List<Pays>> getAllPays() {
        return ResponseWrap.success(paysRepository.findByEtat(1));
    }

    @Secured({"ROLE_ADMIN"})
    @GetMapping("/{id}")
    public ResponseWrap<Pays> getPaysById(@PathVariable int id) {
        return paysRepository.findById(id)
                .map(type -> ResponseWrap.success(type))
                .orElseGet(() -> ResponseWrap.error("id not found"));

    }

    @Secured({"ROLE_ADMIN"})
    @PostMapping
    public ResponseWrap<Pays> addPays(@RequestBody Pays pays) {
        return ResponseWrap.success(paysRepository.save(pays));
    }

    @Secured({"ROLE_ADMIN"})
    @PutMapping("/{id}")
    public ResponseWrap<Pays> updatePays(@PathVariable int id, @RequestBody Pays paysModif) {
        if (paysRepository.existsById(id)) {
            paysModif.setId(id);
            Pays maj = paysRepository.save(paysModif);
            return ResponseWrap.success(maj);
        }
        return ResponseWrap.error("id not found");
    }

    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("/{id}")
    public ResponseWrap<String> deletePays(@PathVariable int id) {
        Optional<Pays> optionalPays = paysRepository.findById(id);

        if (optionalPays.isPresent()) {
            Pays existingPays = optionalPays.get();
            existingPays.setEtat(0);
            paysRepository.save(existingPays);
            return ResponseWrap.success("deleted successfully");
        } else {
            return ResponseWrap.error("id not found");
        }
    }

}
