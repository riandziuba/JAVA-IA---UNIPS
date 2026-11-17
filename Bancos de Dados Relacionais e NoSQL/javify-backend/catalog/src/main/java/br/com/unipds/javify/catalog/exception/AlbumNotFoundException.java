package br.com.unipds.javify.catalog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AlbumNotFoundException extends RuntimeException {

    public AlbumNotFoundException(String id) {
        super("Álbum com ID '" + id + "' não encontrado no catálogo.");
    }
}
