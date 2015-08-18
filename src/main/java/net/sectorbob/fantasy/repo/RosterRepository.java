package net.sectorbob.fantasy.repo;

import net.sectorbob.fantasy.model.Roster;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author Kyle Heide
 *
 */
public interface RosterRepository extends MongoRepository<Roster, String> {

}
