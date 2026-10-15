package br.com.unipds.ebook;

import java.nio.file.Path;

public interface IEbookPropertiesReader {
    void read(Path sourceDir, Ebook ebook);
}
