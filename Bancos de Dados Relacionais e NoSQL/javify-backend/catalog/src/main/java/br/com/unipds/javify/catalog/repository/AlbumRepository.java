package br.com.unipds.javify.catalog.repository;

import br.com.unipds.javify.catalog.domain.Album;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AlbumRepository extends MongoRepository<Album, String> {
}
