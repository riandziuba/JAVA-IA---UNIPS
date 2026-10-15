package br.com.unipds.ebook;

import br.com.unipds.EBookInfoDTO;

import java.nio.file.Path;

public interface IEbookPropertiesReader {
    EBookInfoDTO read(Path sourceDir);
}
