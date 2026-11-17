package br.com.unipds.javify.catalog.controller;

import br.com.unipds.javify.catalog.domain.Album;
import br.com.unipds.javify.catalog.service.AlbumService;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/albums")
public class AlbumController {

    private final AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping
    public ResponseEntity<List<Album>> listAll(@RequestParam(required = false) String title) {
        if (StringUtils.hasText(title)) {
            return ResponseEntity.ok(albumService.searchByTitle(title));
        }
        return ResponseEntity.ok(albumService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Album> getAlbumDetails(@PathVariable String id) {
        Album album = albumService.findById(id);
        return ResponseEntity.ok(album);
    }

}
