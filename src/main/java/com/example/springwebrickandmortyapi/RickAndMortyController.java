package com.example.springwebrickandmortyapi;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class RickAndMortyController {

    private final RickAndMortyService rickAndMortyService;

    public RickAndMortyController(RickAndMortyService rickAndMortyService) {
        this.rickAndMortyService = rickAndMortyService;
    }

    @GetMapping("/characters")
    public List<RickAndMortyCharacter> getAllCharacters(@RequestParam Optional<String> status) {
        return rickAndMortyService.getReallyAllCharacters(status);
    }

    @GetMapping("/character/{id}")
    public RickAndMortyCharacter getCharacterById(@PathVariable int id){
        return rickAndMortyService.getCharacter(id);
    }

    @GetMapping("/species-statistic")
    public int getCharacterBySpecies(@RequestParam Optional<String> species){
        return rickAndMortyService.getCharacterBySpecies(species);
    }

}
