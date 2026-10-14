package br.com.unipds.ebook;

public class EBookServiceFactory {

    public static IEBookService create(String format) {
        if ("pdf".equals(format)) {
            return new PDFEBookService();
        } else if ("epub".equals(format)) {
            return new EPUBEBookService();
        } else {
            throw new IllegalArgumentException("Formato do ebook inválido: " + format);
        }
    }
}
