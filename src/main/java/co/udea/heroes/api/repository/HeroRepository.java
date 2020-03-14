package co.udea.heroes.api.repository;

import co.udea.heroes.api.model.Hero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HeroRepository extends JpaRepository<Hero, Integer> {

    Optional<Hero> findByName(String name);

    @Query("SELECT MAX(h.id) from Hero h")
    int findHighestId();

    @Query("SELECT h from Hero h WHERE h.name LIKE %?1%")
    List<Hero> searchHeroes(String term);
}
