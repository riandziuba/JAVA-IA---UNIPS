package br.com.unipds.javify.catalog.domain;

import jakarta.validation.constraints.NotBlank;

public record ArtistParticipant(
        @NotBlank(message = "O nome do artista é obrigatório.")
        String name,

        @NotBlank(message = "A função do artista é obrigatória.")
        String role ) {}
