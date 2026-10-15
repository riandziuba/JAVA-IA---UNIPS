package br.com.unipds.markdown;

import br.com.unipds.ebook.Markdown;

import java.nio.file.Path;
import java.util.List;

public interface IMarkdownRepository {
    List<Markdown> getChapters(Path sourceDir);
}
