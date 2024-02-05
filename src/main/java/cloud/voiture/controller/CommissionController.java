package cloud.voiture.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cloud.voiture.model.Commission;
import cloud.voiture.model.ResponseWrap;
import cloud.voiture.repository.CommissionRepository;
import cloud.voiture.service.AnnonceService;
import cloud.voiture.service.CommissionService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

@RestController
@RequestMapping("/admin/commission")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CommissionController {

    @Autowired
    CommissionRepository commissionRepository;
    
    @Autowired
    private CommissionService commissionService;

    @GetMapping
    public ResponseEntity<ResponseWrap<List<Commission>>> getAllCarburant() {
        return new ResponseEntity<>(ResponseWrap.success(commissionRepository.findAll()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseWrap<Commission> getCommmissionById(@PathVariable long id) {
        return commissionRepository.findById(id).map(carburant -> ResponseWrap.success(carburant))
                .orElseGet(() -> ResponseWrap.error("Commission non trouvee"));
    }

    @DeleteMapping("/{id}")
    public ResponseWrap<String> deleteCommission(@PathVariable long id) {
        commissionRepository.deleteById(id);
        return ResponseWrap.success("deleted successfully");

    }

    @PostMapping
    public ResponseEntity createCommission(@RequestBody Commission commission)
            throws Exception {
        try {
            if(!commissionService.isRangeValid(commission)){
                throw new Exception("Intervalle deja existante");
            }
            return new ResponseEntity<>(ResponseWrap.success(commissionRepository.save(commission)), HttpStatus.OK);

        }catch (Exception e) {
            if (e instanceof ConstraintViolationException) {
                ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
                Validator validator = factory.getValidator();
                Set<ConstraintViolation<Commission>> violations = validator.validate(commission);
                for (ConstraintViolation<Commission> constraintViolation : violations) {
                    throw new Exception(constraintViolation.getMessageTemplate());
                }
            }
            throw e;
        }
    }

    @PutMapping("/{id}")
    public ResponseWrap<Commission> updateCommission(@RequestBody Commission commission, @PathVariable int id)
            throws Exception {
        if (commissionRepository.existsById((long) id)) {
            commission.setId((long) id);
            try {
                if(!commissionService.isRangeValid(commission, (long)id)){
                    throw new Exception("Intervalle invalide");
                }
                return ResponseWrap.success(commissionRepository.saveAndFlush(commission));
            } catch (Exception e) {
                // TODO: handle exception
                if (e instanceof ConstraintViolationException) {
                    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
                    Validator validator = factory.getValidator();
                    Set<ConstraintViolation<Commission>> violations = validator.validate(commission);
                    for (ConstraintViolation<Commission> constraintViolation : violations) {
                        throw new Exception(constraintViolation.getMessageTemplate());
                    }
                }
                throw e;
            }
        } else {
            return ResponseWrap.error("Le type de commission n'existe pas");
        }
    }

}
