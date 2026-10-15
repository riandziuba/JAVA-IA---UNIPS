package br.com.unipds.markdown;

import br.com.unipds.ebook.Chapter;

import java.util.List;

public interface IMarkdownRenderizerService {
    void render(List<Chapter> chapters);
}
