package grocerywholesaler.service;

import grocerywholesaler.model.Produce;
import grocerywholesaler.repository.ProduceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProduceService {
    @Autowired
    ProduceRepository produceRepository;

    public void setData(List<Produce> produce){
        if (!produceRepository.findAll().isEmpty()){
            produceRepository.deleteAll();
        }
        produceRepository.saveAll(produce);
    }
}
