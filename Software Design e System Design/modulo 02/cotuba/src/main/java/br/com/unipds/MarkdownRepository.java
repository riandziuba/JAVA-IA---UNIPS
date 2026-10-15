package br.com.unipds;

import br.com.unipds.ebook.Chapter;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class MarkdownRepository {
    public List<Chapter> getChapters(Path sourceDir) {
        List<Chapter> chapters = new ArrayList<>();
        PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:**/*.md");
        try (Stream<Path> streamMDs = Files.list(sourceDir)) {
            List<Path> mdFiles = streamMDs
                    .filter(matcher::matches)
                    .sorted()
                    .toList();

            if (mdFiles.isEmpty()) {
                throw new IllegalStateException("Não foram encontrados capítulos (arquivos .md) no diretório: " + sourceDir.toAbsolutePath());
            }
            for (Path mdFile : mdFiles) {
                Chapter chapter = new Chapter();
                String markdown = Files.readString(mdFile);
                chapter.setContent(markdown);
                chapter.setMarkDownFile(mdFile);
                chapters.add(chapter);
            }
            return chapters;
        } catch (IOException ex) {
            throw new IllegalStateException("Erro tentando encontrar arquivos .md em " + sourceDir.toAbsolutePath(), ex);
        }
    }
}
