package br.com.unipds.javify.recommendations.controller;

import br.com.unipds.javify.recommendations.dto.ArtistRecommendation;
import br.com.unipds.javify.recommendations.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/recommendations")
public class RecommendationsController {

    private final UserRepository repository;

    public RecommendationsController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/artists/{userId}")
    public ResponseEntity<List<ArtistRecommendation>> getNoveltyRadar(@PathVariable Long userId) {
        List<ArtistRecommendation> recommendations = repository.recommendations(userId);
        return ResponseEntity.ok(recommendations);
    }

}
