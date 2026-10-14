package br.com.unipds.ebook;


import br.com.unipds.options.OptionsDTO;
import nl.siegmann.epublib.domain.Author;
import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.GuideReference;
import nl.siegmann.epublib.domain.Resource;
import nl.siegmann.epublib.epub.EpubWriter;
import nl.siegmann.epublib.service.MediatypeService;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.List;
import java.util.stream.Stream;

public class EPUBEBookService implements IEBookService {
    public void generate(OptionsDTO options) {
        try {
            var epub = new Book();

            //TODO: definir título e autor para o livro
            epub.getMetadata().addTitle("Livro");
            epub.getMetadata().addAuthor(new Author("Autor"));

            boolean[] ehPrimeiroCapitulo = {true};

            List<Path> mdFiles = this.getMDPaths(options.sourceDir());
            mdFiles.forEach(mdFile -> {
                try {
                    String html = this.extractHtml(mdFile);

                    // TODO: usar título do capítulo
                    String epubHtml = """
                              <html xmlns="http://www.w3.org/1999/xhtml">
                                <head>
                                  <title>Capítulo</title>
                                </head>
                                <body>
                                  %s
                                </body>
                              </html>
                            """.formatted(html);
                    var chapter = new Resource(epubHtml.getBytes(), MediatypeService.XHTML);
                    epub.addSection("Capítulo", chapter);

                    if (ehPrimeiroCapitulo[0]) {
                        epub.getGuide().addReference(new GuideReference(chapter, "text", "Start Reading"));
                        ehPrimeiroCapitulo[0] = false;
                    }

                } catch (Exception ex) {
                    throw new IllegalStateException("Erro ao renderizar para HTML o arquivo " + mdFile, ex);
                }
            });


            var epubWriter = new EpubWriter();

            try {
                epubWriter.write(epub, Files.newOutputStream(options.outputFileName()));
            } catch (IOException ex) {
                throw new IllegalStateException("Erro ao criar arquivo EPUB: " + options.outputFileName().toAbsolutePath(), ex);
            }

        } catch (Exception ex) {
            throw new IllegalStateException("Erro ao gerar EPUB: " + options.outputFileName().toAbsolutePath(), ex);
        }
    }
}
