package grocerywholesaler.repository;

import grocerywholesaler.model.Produce;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProduceRepository extends MongoRepository<Produce, Long>{
}
