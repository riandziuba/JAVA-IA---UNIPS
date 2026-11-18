package br.com.unipds.javify.catalog.service;

import br.com.unipds.javify.catalog.domain.Album;
import br.com.unipds.javify.catalog.exception.AlbumNotFoundException;
import br.com.unipds.javify.catalog.repository.AlbumRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumService {

    private final AlbumRepository albumRepository;

    AlbumService(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    public Page<Album> findAll(Pageable pageable) {
        return this.albumRepository.findAll(pageable);
    }

    public Page<Album> searchByTitle(String title, Pageable pageable) {
        return this.albumRepository.findByTitleContainingIgnoreCase(title, pageable);
    }

    public Album findById(String id) {
        return this.albumRepository.findById(id).orElseThrow(() -> new AlbumNotFoundException(id));
    }

    public Album saveNewAlbum(Album newAlbum) {
        return this.albumRepository.save(newAlbum);
    }

    public Album updateAlbum(String id, Album updatedAlbum) {
        this.albumRepository.findById(id);
        if (!id.equals(updatedAlbum.id())) {
            throw new AlbumNotFoundException(updatedAlbum.id());
        }

        return this.albumRepository.save(updatedAlbum);
    }

    public void deleteAlbum(String id) {
        if (!this.albumRepository.existsById(id)) {
            throw new AlbumNotFoundException(id);
        }
        this.albumRepository.deleteById(id);
    }
}
