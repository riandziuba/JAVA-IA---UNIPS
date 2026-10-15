package br.com.unipds.options;

public enum EbookFormat {
    PDF,
    EPUB;

    public static EbookFormat getType(String format) {
        return  switch (format) {
            case "pdf" -> PDF;
            case "epub" -> EPUB;
            default -> throw new RuntimeException("Formato do ebook inválido: " + format);
        };
    }
}
