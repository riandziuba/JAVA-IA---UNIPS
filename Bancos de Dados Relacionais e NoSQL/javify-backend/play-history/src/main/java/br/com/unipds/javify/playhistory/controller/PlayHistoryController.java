package br.com.unipds.javify.playhistory.controller;

import br.com.unipds.javify.playhistory.domain.PlayHistory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/analytics/history")
public class PlayHistoryController {

    @PostMapping("/{userId}/play/{trackId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void registerPlay(
            @PathVariable Long userId,
            @PathVariable String trackId,
            @RequestParam String trackName) {

        throw new UnsupportedOperationException("TODO");

    }

    @GetMapping("/{userId}")
    public List<PlayHistory> getUserHistory(@PathVariable Long userId) {

        throw new UnsupportedOperationException("TODO");

    }

}
