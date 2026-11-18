package br.com.unipds.javify.catalog.controller;

import br.com.unipds.javify.catalog.domain.Album;
import br.com.unipds.javify.catalog.service.AlbumService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
    public ResponseEntity<Page<Album>> listAll(@RequestParam(required = false) String title,
                                               @PageableDefault(size = 5, page = 0, sort = "releaseYear", direction = Sort.Direction.DESC) Pageable pageable) {
        if (StringUtils.hasText(title)) {
            return ResponseEntity.ok(albumService.searchByTitle(title, pageable));
        }
        return ResponseEntity.ok(albumService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Album> getAlbumDetails(@PathVariable String id) {
        Album album = albumService.findById(id);
        return ResponseEntity.ok(album);
    }

}
