package com.example.springwebrickandmortyapi;

import java.util.List;

public record RickAndMortyResponse(
        RickAndMortyInfo info,

        List<RickAndMortyCharacter> results
) {
}
