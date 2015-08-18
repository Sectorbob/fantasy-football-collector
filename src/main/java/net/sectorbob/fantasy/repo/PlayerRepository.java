package net.sectorbob.fantasy.repo;

import net.sectorbob.fantasy.model.Player;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by ltm688 on 8/17/15.
 */
public interface PlayerRepository extends MongoRepository<Player, String> {

    Player save(Player player);

}
