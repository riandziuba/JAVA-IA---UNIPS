package br.com.unipds.javify.catalog.controller;

import br.com.unipds.javify.catalog.service.AlbumReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/admin/reports")
public class AdminReportController {

    private final AlbumReportService albumReportService;

    public AdminReportController(AlbumReportService albumReportService) {
        this.albumReportService = albumReportService;
    }

    @GetMapping("duration-genre")
    public ResponseEntity<AlbumReportService.GenreDurationReportResult> getDurationReportByGenre(
            @RequestParam String genre) {
        var result = albumReportService.calculateTotalDurationByGenre(genre);
        if (result == null) {
            result = new AlbumReportService.GenreDurationReportResult(0L, 0L);
        }
        return ResponseEntity.ok(result);
    }
}
