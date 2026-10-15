package br.com.unipds;

import br.com.unipds.ebook.*;
import br.com.unipds.markdown.IMarkdownRenderizerService;

import br.com.unipds.markdown.IMarkdownRepository;
import br.com.unipds.options.OptionsDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Any;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class ApplicationService implements IApplicationService {
    private final IMarkdownRenderizerService markdownRenderizerService;
    private final IEbookPropertiesReader ebookPropertiesReader;
    private final IMarkdownRepository markdownRepository;
    private final Instance<IEBookService> ebookServices;

    @Inject
    public ApplicationService(IMarkdownRenderizerService markdownRenderizerService, IEbookPropertiesReader ebookPropertiesReader, IMarkdownRepository markdownRepository, @Any Instance<IEBookService> ebookServices) {
        this.markdownRenderizerService = markdownRenderizerService;
        this.ebookPropertiesReader = ebookPropertiesReader;
        this.markdownRepository = markdownRepository;
        this.ebookServices = ebookServices;
    }

    public void processEbook(OptionsDTO options) {
        List<Markdown> markdowns = this.markdownRepository.getChapters(options.sourceDir());
        List<Chapter> chapters = markdownRenderizerService.render(markdowns);
        EBookInfoDTO eBookInfoDTO = this.ebookPropertiesReader.read(options.sourceDir());
        Ebook ebook = new Ebook(eBookInfoDTO.title(), eBookInfoDTO.author(), chapters, options.outputFileName());
        IEBookService eBookService = ebookServices.select(new EbookFormatFilter(options.format())).get();
        eBookService.generate(ebook);
    }
}
