package com.example.springwebrickandmortyapi;

public record RickAndMortyInfo(
        int count,
        int pages,
        String next,
        String prev
) {
}
