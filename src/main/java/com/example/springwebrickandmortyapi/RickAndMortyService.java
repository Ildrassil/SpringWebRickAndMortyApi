package com.example.springwebrickandmortyapi;

import org.springframework.stereotype.Service;

import org.springframework.web.client.RestClient;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RickAndMortyService {

    RestClient rickAndMortyController = RestClient.builder().baseUrl("https://rickandmortyapi.com/api").build();

    public List<RickAndMortyCharacter> getAllCharacters(Optional<String> status){

        String uri = "/character";

        if (status.isPresent()) {
            uri += "?status=" + status.get();
        }

        RickAndMortyResponse response = rickAndMortyController.get().uri(uri)
                .retrieve()
                .body(RickAndMortyResponse.class);
        List<RickAndMortyCharacter> characterList = response.results();

        return characterList;
    }

    public List<RickAndMortyCharacter> getReallyAllCharacters(Optional<String> status) {

        String statusString = "";

        if (status.isPresent()) {
            statusString = "&status=" + status.get();
        }

        List<RickAndMortyCharacter> characterList = new ArrayList<>();

        RickAndMortyResponse response = rickAndMortyController.get().uri("/character?page=1" + statusString)
                .retrieve()
                .body(RickAndMortyResponse.class);

        characterList.addAll(response.results());
        int count = 2;

        while (count <= response.info().pages()) {

            response = rickAndMortyController.get().uri("/character?page=" + count + statusString)
                    .retrieve()
                    .body(RickAndMortyResponse.class);
            characterList.addAll(response.results());
            count++;
        }

        return characterList;
    }

    public RickAndMortyCharacter getCharacter(int id) {
        return rickAndMortyController.get().uri("/character/{id}", id)
                .retrieve()
                .body(RickAndMortyCharacter.class);
    }

    public int getCharacterBySpecies(Optional<String> species) {
        String uri = "/character";


            uri += "?species=" + species.get() +"&status=Alive";


        RickAndMortyResponse response = rickAndMortyController.get().uri(uri)
                .retrieve()
                .body(RickAndMortyResponse.class);
       int result = response.info().count();

        return result;

    }
}
