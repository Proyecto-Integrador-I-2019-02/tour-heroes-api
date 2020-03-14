package co.udea.heroes.api.service;

import co.udea.heroes.api.exception.DataDuplicatedException;
import co.udea.heroes.api.exception.NotFoundException;
import co.udea.heroes.api.model.Hero;
import co.udea.heroes.api.repository.HeroRepository;
import co.udea.heroes.api.util.Messages;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HeroService {

    private HeroRepository heroRepository;
    private Messages messages;

    public HeroService(HeroRepository heroRepository, Messages messages){

        this.heroRepository = heroRepository;
        this.messages = messages;
    }

    public Hero getHero(Integer id){
        Optional<Hero> optionalHero = heroRepository.findById(id);
        return optionalHero.get();
    }

    public List<Hero> getHeroes(){

        return heroRepository.findAll();
    }

    public Hero addHero(Hero hero){
        Optional<Hero> optionalHero = heroRepository.findByName(hero.getName());
        if(optionalHero.isPresent()){
            throw new DataDuplicatedException(messages.get("exception.data_duplicate_name.hero"));
        }         try {
            hero.setId(heroRepository.findHighestId() + 1);
        }catch (Exception e){
            hero.setId(1);
        }
        return heroRepository.save(hero);
    }

    public Hero deleteHero(Integer id) {
        Optional<Hero> hero = heroRepository.findById(id);
        if (!hero.isPresent()) {
            throw new NotFoundException(messages.get("exception.cannot_delete_hero.hero"));
        }
        heroRepository.deleteById(id);
        return hero.get();
    }

    public Hero updateHero(Hero hero) {
        // Valida que exista el heroe con ese id
        Optional<Hero> optionalHero = heroRepository.findById(hero.getId());
        if(!optionalHero.isPresent()){
            throw new NotFoundException(messages.get("exception.cannot_find_hero.hero"));

        }          // Valida que no se actualize a un nombre ya existente
        optionalHero = heroRepository.findByName(hero.getName());
        if(optionalHero.isPresent()){
            throw new DataDuplicatedException(messages.get("exception.data_duplicate_name.hero"));
        }         return heroRepository.save(hero);
    }
    public List<Hero> searchHeroes(String term){
        return heroRepository.searchHeroes(term);
    }

}
