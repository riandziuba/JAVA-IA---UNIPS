package br.com.unipds.ebook;

import br.com.unipds.options.EbookFormat;

public class EBookServiceFactory {

    public static IEBookService create(EbookFormat format) {
        if (EbookFormat.PDF.equals(format)) {
            return new PDFEBookService();
        } else if (EbookFormat.EPUB.equals(format)) {
            return new EPUBEBookService();
        } else {
            throw new IllegalArgumentException("Formato do ebook inválido: " + format);
        }
    }
}
