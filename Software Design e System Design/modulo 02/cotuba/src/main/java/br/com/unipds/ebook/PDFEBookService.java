package br.com.unipds.ebook;

import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfOutline;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.navigation.PdfExplicitDestination;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.IBlockElement;
import com.itextpdf.layout.element.IElement;
import com.itextpdf.layout.properties.AreaBreakType;
import br.com.unipds.options.EbookFormat;
import jakarta.enterprise.context.ApplicationScoped;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@ApplicationScoped
@EbookFormatQualifier(EbookFormat.PDF)
public class PDFEBookService implements IEBookService {

    public void generate(Ebook ebook) {
        try (var writer = new PdfWriter(Files.newOutputStream(ebook.getFileName()));
             var pdf = new PdfDocument(writer);
             var pdfDocument = new Document(pdf)) {

            pdf.getDocumentInfo().setTitle(ebook.getTitle());
            pdf.getDocumentInfo().setAuthor(ebook.getAuthor());

            ebook.getAllChapters().forEach(chapterRaw -> {
                try {

                    List<IElement> convertToElements = HtmlConverter.convertToElements(chapterRaw.getHtml());

                    if (pdf.getNumberOfPages() == 0) {
                        pdf.addNewPage();
                    } else {
                        pdfDocument.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
                    }
                    PdfOutline rootOutline = pdf.getOutlines(false);
                    if (rootOutline == null) {
                        pdf.initializeOutlines();
                        rootOutline = pdf.getOutlines(false);
                    }

                    PdfOutline chapterOutline = rootOutline.addOutline(chapterRaw.getTitle());
                    chapterOutline.addDestination(PdfExplicitDestination.createFit(pdf.getLastPage()));

                    for (IElement element : convertToElements) {
                        pdfDocument.add((IBlockElement) element);
                    }
                    // TODO: não adicionar página depois do último capítulo

                } catch (Exception ex) {
                    throw new IllegalStateException("Erro ao renderizar para HTML o arquivo " + chapterRaw, ex);
                }
            });
        } catch (IOException ex) {
            throw new IllegalStateException("Erro ao gerar PDF em" + ebook.getFileName().toAbsolutePath(), ex);
        }
    }
}
