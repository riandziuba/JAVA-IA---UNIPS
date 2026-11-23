package br.com.unipds.javify.playhistory.controller;

import br.com.unipds.javify.playhistory.domain.PlayHistory;
import br.com.unipds.javify.playhistory.domain.PlayHistoryKey;
import br.com.unipds.javify.playhistory.repository.PlayHistoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/analytics/history")
public class PlayHistoryController {

    private final PlayHistoryRepository repository;

    public PlayHistoryController(PlayHistoryRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/{userId}/play/{trackId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void registerPlay(
            @PathVariable Long userId,
            @PathVariable String trackId,
            @RequestParam String trackName) {

        var key = new PlayHistoryKey(userId, LocalDateTime.now());
        var playHistory = new PlayHistory(key, trackId, trackName);
        repository.save(playHistory);

    }

    @GetMapping("/{userId}")
    public List<PlayHistory> getUserHistory(@PathVariable Long userId) {

        return repository.findByKeyUserId(userId);

    }

}
