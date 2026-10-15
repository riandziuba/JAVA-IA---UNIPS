package br.com.unipds.ebook;

import br.com.unipds.options.EbookFormat;
import jakarta.enterprise.util.AnnotationLiteral;

public class EbookFormatFilter extends AnnotationLiteral<EbookFormatQualifier> implements EbookFormatQualifier {
    private final EbookFormat format;

    public EbookFormatFilter(EbookFormat format) {
        this.format = format;
    }

    @Override
    public EbookFormat value() {
        return format;
    }
}
