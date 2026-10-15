package br.com.unipds.markdown;

import br.com.unipds.ebook.Chapter;

import java.nio.file.Path;
import java.util.List;

public interface IMarkdownRepository {
    List<Chapter> getChapters(Path sourceDir);
}
