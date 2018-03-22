package grocerywholesaler.repository;

import grocerywholesaler.model.Produce;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ProduceRepository
 * MongoDB
 */
@Repository
public interface ProduceRepository extends MongoRepository<Produce, Long>{
    /**
     * Returns produce with given name
     * @param name name of produce
     * @return matched Produce
     */
    Produce findByName(String name);

    /**
     * Finds all Produce in repository and orders by updated date (ascending)
     * @return List<Produce> ordered by updated date ascending
     */
    List<Produce> findAllByOrderByUpdatedAsc();
    /**
     * Finds all Produce in repository and orders by updated date (descending)
     * @return List<Produce> ordered by updated date descending
     */
    List<Produce> findAllByOrderByUpdatedDesc();
    /**
     * Searches Produce Repository by given string
     * uses regex to search repository
     * @return List<Produce> ordered by updated date ascending
     */
    @Query(value = "{'name': {$regex : ?0, $options: 'i'}}")
    List<Produce> findByNameRegex(String name);
}
