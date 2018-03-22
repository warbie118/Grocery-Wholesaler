package grocerywholesaler.service;

import grocerywholesaler.model.Produce;
import grocerywholesaler.model.ProducePriceOnly;
import grocerywholesaler.repository.ProduceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ProduceService {

    @Autowired
    private ProduceRepository produceRepository;

    /**
     * Sets data in Repository
     * If data already exists in Repository will overwrite
     * @param produce List<Produce>
     */
    public void setData(List<Produce> produce){
        if (!produceRepository.findAll().isEmpty()){
            produceRepository.deleteAll();
        }
        produceRepository.saveAll(produce);
    }

    /**
     * Updates price of produce item if found in repository
     * if price updated, updatedDate is set to current date
     * if produce item not found in repository null is returned
     *
     * @param priceUpdate ProductPriceOnly
     * @return updated Produce with updated price and updatedDate
     */
    public Produce updatePrice(ProducePriceOnly priceUpdate){
        Produce produce = produceRepository.findByName(priceUpdate.getName());
        if (produce == null){
            return null;
        } else {
            produce.setPrice(priceUpdate.getPrice());
            produce.setUpdated(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            return produceRepository.save(produce);
        }
    }

    /**
     * Returns list of produce ordered by given order value
     * allowed order values are - asc, desc
     * if order value not recognised null is returned
     *
     * @param order String
     * @return List<Produce> ordered by given order value
     */
    public List<Produce> getProduceByUpdatedDate(String order){
        if (order.toUpperCase().equals("ASC")){
            return produceRepository.findAllByOrderByUpdatedAsc();
        } else if (order.toUpperCase().equals("DESC")){
            return produceRepository.findAllByOrderByUpdatedDesc();
        } else return null;
    }

    /**
     * Searches repository by given produce name
     * if name not found, empty array returned
     *
     * @param name name of produce to search repository by
     * @return List<Produce> search results
     */
    public List<Produce> searchProduceByName(String name){
        List<Produce> results = produceRepository.findByNameRegex(name);
        return results;
    }
}
