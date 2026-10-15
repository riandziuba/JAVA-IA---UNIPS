package br.com.unipds.markdown;

import br.com.unipds.ebook.Markdown;
import jakarta.enterprise.context.ApplicationScoped;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

@ApplicationScoped
public class MarkdownRepositoryFiles implements IMarkdownRepository {
    public List<Markdown> getChapters(Path sourceDir) {
        List<Markdown> chapters = new ArrayList<>();
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
                String markdown = Files.readString(mdFile);
                chapters.add(new Markdown(markdown, mdFile));
            }
            return Collections.unmodifiableList(chapters);
        } catch (IOException ex) {
            throw new IllegalStateException("Erro tentando encontrar arquivos .md em " + sourceDir.toAbsolutePath(), ex);
        }
    }
}
