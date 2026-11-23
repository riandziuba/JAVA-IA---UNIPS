package br.com.unipds.javify.recommendations.dto;

public record ArtistRecommendation(
        String name,
        long recommendationStrength
) {}
