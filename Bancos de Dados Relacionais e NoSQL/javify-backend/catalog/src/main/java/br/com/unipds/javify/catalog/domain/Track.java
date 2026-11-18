package br.com.unipds.javify.catalog.domain;

import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "tracks")
public record Track(
        @Id
        String id,

        @NotBlank(message = "O título da faixa não pode estar em branco.")
        String title,

        @NotNull(message = "A duração da faixa é obrigatória.")
        @Min(value = 1, message = "A duração deve ter pelo menos 1 segundo.")
        Integer durationSeconds,

        @NotBlank(message = "A URL do arquivo de áudio é obrigatória.")
        String audioFileUrl,

        @NotBlank(message = "O ISRC é obrigatório.")
        String isrc,

        @NotNull(message = "O total de reproduções é obrigatório.")
        @Min(value = 0, message = "O total de reproduções não pode ser negativo.")
        Long totalPlays,

        @NotNull(message = "A indicação de conteúdo explícito é obrigatória.")
        Boolean explicitContent,

        String lyricsUrl,

        @NotNull(message = "O BPM é obrigatório.")
        @Min(value = 1, message = "O BPM deve ser maior que zero.")
        Integer bpm,

        @NotNull(message = "A energia é obrigatória.")
        @DecimalMin(value = "0.0", message = "A energia não pode ser negativa.")
        @DecimalMax(value = "1.0", message = "A energia não pode ser maior que 1.")
        Double energy,

        @NotNull(message = "A indicação de instrumental é obrigatória.")
        Boolean instrumental,

        @Valid
        @NotNull(message = "O álbum da faixa é obrigatório.")
        TrackAlbum album,

        @Valid
        List<ArtistParticipant> artists) {
}
