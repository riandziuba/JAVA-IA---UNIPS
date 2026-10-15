package br.com.unipds.options;

import java.nio.file.Path;

public record OptionsDTO(Path sourceDir, EbookFormat format, Path outputFileName, boolean isVerboseMode) {
}
