package br.com.unipds.javify.catalog.domain;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;

public record TrackAlbum(
        @Id
        String id,

        @NotBlank(message = "O título do álbum é obrigatório.")
        String title,

        @NotBlank(message = "A URL da capa do álbum é obrigatória.")
        String coverUrl) {
}
