package cloud.voiture.service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cloud.voiture.model.Commission;
import cloud.voiture.repository.CommissionRepository;

@Service
public class CommissionService {

    @Autowired
    CommissionRepository commissionRepository ;
    
    public CommissionService (){
        
    }

    public List<Commission> getCommissionsBetweenValues(Double minValue, Double maxValue) {
        return commissionRepository.findAll()
                .stream()
                .filter(commission -> commission.getMin() >= minValue && commission.getMax() <= maxValue)
                .collect(Collectors.toList());
    }

    List<Commission> getAllCommissionsExcepted(Long id){
        return commissionRepository.findAll()
                .stream()
                .filter(commission -> !commission.getId().equals(id))
                .collect(Collectors.toList());
    }

    public List<Commission> getCommissionsBetweenValues(Double value) {
        return commissionRepository.findAll()
                .stream()
                .filter(commission -> commission.getMin() <= value && commission.getMax() >= value)
                .collect(Collectors.toList());
    }

    public List<Commission> getCommissionsBetweenValues(Double value,Long id) {
        return getAllCommissionsExcepted(id)
                .stream()
                .filter(commission -> commission.getMin() <= value && commission.getMax() >= value)
                .collect(Collectors.toList());
    }

    public boolean isRangeValid(Double value){
        if(getCommissionsBetweenValues(value).isEmpty()){
            return true;
        }
        return false;
    }
    
    public boolean isRangeValid(Double value,Long id){
        if(getCommissionsBetweenValues(value,id).isEmpty()){
            return true;
        }
        return false;
    }

    public boolean isRangeValid(Commission commission){
        if(isRangeValid(commission.getMin()) && isRangeValid(commission.getMax())){
            return true;
        }
        return false;
    }

    public boolean isRangeValid(Commission commission,Long id){
        if(isRangeValid(commission.getMin(),id) && isRangeValid(commission.getMax(),id)){
            return true;
        }
        return false;
    }

    public Commission getRecentCommission(double prix){
        return commissionRepository.findCommissionByValueAndMaxDate(prix);
    }
}
