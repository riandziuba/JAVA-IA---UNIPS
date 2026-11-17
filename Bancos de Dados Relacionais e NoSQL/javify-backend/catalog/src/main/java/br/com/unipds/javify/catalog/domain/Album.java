package br.com.unipds.javify.catalog.domain;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "albums")
public record Album (
                     @Id
                     String id,

                     @NotBlank(message = "O título do álbum é obrigatório e não pode estar em branco.")
                     String title,

                     @NotNull(message = "O ano de lançamento é obrigatório.")
                     @Min(value = 1900, message = "O ano de lançamento deve ser maior que 1900.")
                     Integer releaseYear,

                     @NotBlank(message = "O gênero musical é obrigatório.")
                     String genre,

                     @Valid
                     List<ArtistParticipant> artists,

                     @Valid
                     List<Track> tracks) {

}
