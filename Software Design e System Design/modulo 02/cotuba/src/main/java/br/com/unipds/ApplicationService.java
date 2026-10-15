package br.com.unipds;

import br.com.unipds.ebook.Chapter;
import br.com.unipds.ebook.EBookServiceFactory;
import br.com.unipds.ebook.Ebook;
import br.com.unipds.ebook.IEBookService;
import br.com.unipds.options.OptionsDTO;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class ApplicationService {
    private final MarkdownRenderizerService markdownRenderizerService;
    private final EbookPropertiesReader ebookPropertiesReader;

    public ApplicationService() {
        this.markdownRenderizerService = new MarkdownRenderizerService();
        this.ebookPropertiesReader = new EbookPropertiesReader();
    }

    public void processEbook(OptionsDTO options) {
        List<Chapter> chapters = markdownRenderizerService.render(options.sourceDir());
        Ebook ebook = new Ebook();
        ebook.addChapters(chapters);
        ebook.setFileName(options.outputFileName());
        this.ebookPropertiesReader.read(options.sourceDir(), ebook);
        IEBookService eBookService = EBookServiceFactory.create(options.format());
        eBookService.generate(ebook);
    }
}
