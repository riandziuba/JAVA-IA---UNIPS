package br.com.unipds.javify.catalog.repository;

import br.com.unipds.javify.catalog.domain.Album;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AlbumRepository extends MongoRepository<Album, String> {
    Page<Album> findByTitleContainingIgnoreCase(String title, Pageable pageable);
}
