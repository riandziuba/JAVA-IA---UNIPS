package br.com.unipds;

import br.com.unipds.ebook.*;
import br.com.unipds.markdown.IMarkdownRenderizerService;

import br.com.unipds.markdown.IMarkdownRepository;
import br.com.unipds.options.OptionsDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class ApplicationService implements IApplicationService {
    private final IMarkdownRenderizerService markdownRenderizerService;
    private final IEbookPropertiesReader ebookPropertiesReader;
    private final IMarkdownRepository markdownRepository;

    @Inject
    public ApplicationService(IMarkdownRenderizerService markdownRenderizerService, IEbookPropertiesReader ebookPropertiesReader, IMarkdownRepository markdownRepository) {
        this.markdownRenderizerService = markdownRenderizerService;
        this.ebookPropertiesReader = ebookPropertiesReader;
        this.markdownRepository = markdownRepository;
    }

    public void processEbook(OptionsDTO options) {
        List<Chapter> chapters = this.markdownRepository.getChapters(options.sourceDir());
        markdownRenderizerService.render(chapters);
        Ebook ebook = new Ebook();
        ebook.addChapters(chapters);
        ebook.setFileName(options.outputFileName());
        this.ebookPropertiesReader.read(options.sourceDir(), ebook);
        IEBookService eBookService = EBookServiceFactory.create(options.format());
        eBookService.generate(ebook);
    }
}
