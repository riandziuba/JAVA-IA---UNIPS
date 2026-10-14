package br.com.unipds.options;

import java.nio.file.Path;

public record OptionsDTO(Path sourceDir, String format, Path outputFileName, boolean isVerboseMode) {
}
