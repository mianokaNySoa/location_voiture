package cloud.voiture.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cloud.voiture.model.Historique;
import cloud.voiture.model.ResponseWrap;
import cloud.voiture.repository.HistoriqueRepository;

@RestController
@RequestMapping("/user/historique")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class HistoriqueController {

    @Autowired
    HistoriqueRepository historiqueRepository;

    @GetMapping("/")
    public ResponseWrap<List<Historique>> getAllHistorique() {
        return ResponseWrap.success(historiqueRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseWrap<Historique> getHistoriqueById(@PathVariable long id) {
        return historiqueRepository.findById(id).map(historique -> ResponseWrap.success(historique))
                .orElseGet(() -> ResponseWrap.error("Historique non trouvee"));
    }

    @DeleteMapping("/{id}")
    public void deleteHistorique(@PathVariable long id) {
        historiqueRepository.deleteById(id);
    }

    @PostMapping("/")
    public Historique createHistorique(@RequestBody Historique historique) {
        return historiqueRepository.save(historique);
    }

    @PutMapping("/{id}")
    public ResponseWrap<Historique> updateHistorique(@RequestBody Historique historique, @PathVariable int id)
            throws Exception {
        if (historiqueRepository.existsById((long) id)) {
            historique.setId((long) id);
            return ResponseWrap.success(historiqueRepository.saveAndFlush(historique));
        }else {
            return ResponseWrap.error("L'historique n'existe pas");
        }
    }

}
