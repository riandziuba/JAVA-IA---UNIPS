package br.com.unipds.javify.catalog.service;

import br.com.unipds.javify.catalog.domain.Album;
import br.com.unipds.javify.catalog.exception.AlbumNotFoundException;
import br.com.unipds.javify.catalog.repository.AlbumRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AlbumService.class);

    private final AlbumRepository albumRepository;

    AlbumService(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @Cacheable(value = "albums", key = "#pageable.pageNumber + ' - ' + #pageable.pageSize")
    public Page<Album> findAll(Pageable pageable) {
        LOGGER.info("Calling findAll of Album");
        return this.albumRepository.findAll(pageable);
    }

    public Page<Album> searchByTitle(String title, Pageable pageable) {
        return this.albumRepository.findByTitleContainingIgnoreCase(title, pageable);
    }

    @Cacheable(value = "album", key = "#id")
    public Album findById(String id) {
        return this.albumRepository.findById(id).orElseThrow(() -> new AlbumNotFoundException(id));
    }

    @CacheEvict(value = "albums", allEntries = true)
    public Album saveNewAlbum(Album newAlbum) {
        return this.albumRepository.save(newAlbum);
    }

    @Caching(
            put = @CachePut(value = "album", key = "#id"),
            evict = @CacheEvict(value = "albums", allEntries = true)
    )
    public Album updateAlbum(String id, Album updatedAlbum) {
        this.albumRepository.findById(id);
        if (!id.equals(updatedAlbum.id())) {
            throw new AlbumNotFoundException(updatedAlbum.id());
        }

        return this.albumRepository.save(updatedAlbum);
    }

    @Caching(
            evict = {
                    @CacheEvict(value = "albums", allEntries = true),
                    @CacheEvict(value = "album", key = "#id")
            }
    )
    public void deleteAlbum(String id) {
        if (!this.albumRepository.existsById(id)) {
            throw new AlbumNotFoundException(id);
        }
        this.albumRepository.deleteById(id);
    }
}
