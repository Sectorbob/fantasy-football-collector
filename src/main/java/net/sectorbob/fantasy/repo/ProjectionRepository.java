package net.sectorbob.fantasy.repo;

import net.sectorbob.fantasy.sharks.dto.FSPlayer;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by ltm688 on 8/18/15.
 */
public interface ProjectionRepository extends MongoRepository<FSPlayer, String> {



    FSPlayer save(FSPlayer player);

}
