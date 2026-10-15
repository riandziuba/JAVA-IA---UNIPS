package br.com.unipds.ebook;

import java.nio.file.Path;

public record Markdown(String content, Path markDownFile) {
}
