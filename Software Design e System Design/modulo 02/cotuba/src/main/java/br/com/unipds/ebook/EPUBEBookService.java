package br.com.unipds.ebook;


import br.com.unipds.options.EbookFormat;
import jakarta.enterprise.context.ApplicationScoped;
import nl.siegmann.epublib.domain.Author;
import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.GuideReference;
import nl.siegmann.epublib.domain.Resource;
import nl.siegmann.epublib.epub.EpubWriter;
import nl.siegmann.epublib.service.MediatypeService;

import java.io.IOException;
import java.nio.file.Files;

@ApplicationScoped
@EbookFormatQualifier(EbookFormat.EPUB)
public class EPUBEBookService implements IEBookService {
    public void generate(Ebook ebook) {
        try {
            var epub = new Book();

            epub.getMetadata().addTitle(ebook.getTitle());
            epub.getMetadata().addAuthor(new Author(escape(ebook.getAuthor())));

            boolean[] ehPrimeiroCapitulo = {true};


            ebook.getAllChapters().forEach(chapterRaw -> {
                try {

                    String epubHtml = """
                              <html xmlns="http://www.w3.org/1999/xhtml">
                                <head>
                                  <title>%s</title>
                                </head>
                                <body>
                                  %s
                                </body>
                              </html>
                            """.formatted(escape(chapterRaw.getTitle()), escape(chapterRaw.getHtml()));
                    var chapter = new Resource(epubHtml.getBytes(), MediatypeService.XHTML);
                    epub.addSection(escape(chapterRaw.getTitle()), chapter);

                    if (ehPrimeiroCapitulo[0]) {
                        epub.getGuide().addReference(new GuideReference(chapter, "text", "Start Reading"));
                        ehPrimeiroCapitulo[0] = false;
                    }

                } catch (Exception ex) {
                    throw new IllegalStateException("Erro ao renderizar para HTML o arquivo " + chapterRaw.getMarkDownFile(), ex);
                }
            });


            var epubWriter = new EpubWriter();

            try {
                epubWriter.write(epub, Files.newOutputStream(ebook.getFileName()));
            } catch (IOException ex) {
                throw new IllegalStateException("Erro ao criar arquivo EPUB: " + ebook.getFileName().toAbsolutePath(), ex);
            }

        } catch (Exception ex) {
            throw new IllegalStateException("Erro ao gerar EPUB: " + ebook.getFileName().toAbsolutePath(), ex);
        }
    }

    public static String escape(String text) {
        if (text == null) return "";
        return text.replaceAll("&(?!(?:[a-zA-Z]+|#\\d+|#x[0-9a-fA-F]+);)", "&amp;");
    }
}
