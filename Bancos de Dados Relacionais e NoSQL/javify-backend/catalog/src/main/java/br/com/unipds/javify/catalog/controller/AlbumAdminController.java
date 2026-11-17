package br.com.unipds.javify.catalog.controller;

import br.com.unipds.javify.catalog.domain.Album;
import br.com.unipds.javify.catalog.service.AlbumService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/albums")
public class AlbumAdminController {

    private final AlbumService albumService;

    public AlbumAdminController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @PostMapping
    public ResponseEntity<Album> createAlbum(@Valid @RequestBody Album newAlbum) {
        Album savedAlbum = albumService.saveNewAlbum(newAlbum);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAlbum);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Album> updateAlbum(
            @PathVariable String id,
            @Valid @RequestBody Album updatedAlbum) {
        Album savedAlbum = albumService.updateAlbum(id, updatedAlbum);
        return ResponseEntity.ok(savedAlbum);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlbum(@PathVariable String id) {
        albumService.deleteAlbum(id);
        return ResponseEntity.noContent().build();
    }
}
