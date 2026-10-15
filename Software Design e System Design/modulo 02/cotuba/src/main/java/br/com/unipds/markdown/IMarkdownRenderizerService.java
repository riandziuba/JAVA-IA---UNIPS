package br.com.unipds.markdown;

import br.com.unipds.ebook.Chapter;
import br.com.unipds.ebook.Markdown;

import java.util.List;

public interface IMarkdownRenderizerService {
    List<Chapter> render(List<Markdown> markdowns);
}
