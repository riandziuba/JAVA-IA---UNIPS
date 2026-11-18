package br.com.unipds.javify.catalog.domain;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.mongodb.core.mapping.Field;

public record AlbumTrack(
        @NotNull(message = "O número da faixa é obrigatório.")
        @Min(value = 1, message = "O número da faixa deve ser maior que zero.")
        Integer number,

        @NotBlank(message = "O título da faixa não pode estar em branco.")
        String title,

        @NotNull(message = "A duração da faixa é obrigatória.")
        @Min(value = 1, message = "A duração deve ter pelo menos 1 segundo.")
        @Field("duration_seconds")
        Integer durationSeconds ) {}
