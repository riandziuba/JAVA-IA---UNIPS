package br.com.unipds.ebook;

import br.com.unipds.options.EbookFormat;
import jakarta.enterprise.context.ApplicationScoped;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@EbookFormatQualifier(EbookFormat.HTML)
public class HTMLEBookService implements IEBookService {

    @Override
    public void generate(Ebook ebook) {
        Path outputDir = resolveOutputDir(ebook.fileName());

        try {
            Files.createDirectories(outputDir);

            List<Chapter> chapters = ebook.getAllChapters();
            List<String> chapterFileNames = new ArrayList<>();

            for (int i = 0; i < chapters.size(); i++) {
                Chapter chapter = chapters.get(i);
                String chapterFileName = "chapter-" + (i + 1) + ".html";
                chapterFileNames.add(chapterFileName);
                Files.writeString(outputDir.resolve(chapterFileName),
                        renderChapter(ebook, chapter), StandardCharsets.UTF_8);
            }

            Files.writeString(outputDir.resolve("index.html"),
                    renderIndex(ebook, chapters, chapterFileNames), StandardCharsets.UTF_8);
        } catch (IOException ex) {
            throw new IllegalStateException("Erro ao gerar site HTML em " + outputDir.toAbsolutePath(), ex);
        }
    }

    private String renderChapter(Ebook ebook, Chapter chapter) {
        return """
                <!DOCTYPE html>
                <html lang="pt-BR">
                  <head>
                    <meta charset="UTF-8">
                    <title>%s - %s</title>
                  </head>
                  <body>
                    <p><a href="index.html">&laquo; Sumário</a></p>
                    %s
                  </body>
                </html>
                """.formatted(escape(chapter.title()), escape(ebook.title()),
                chapter.html());
    }

    private String renderIndex(Ebook ebook, List<Chapter> chapters, List<String> chapterFileNames) {
        StringBuilder summary = new StringBuilder("<ul>\n");
        for (int i = 0; i < chapters.size(); i++) {
            summary.append("      <li><a href=\"")
                    .append(chapterFileNames.get(i))
                    .append("\">")
                    .append(escape(chapters.get(i).title()))
                    .append("</a></li>\n");
        }
        summary.append("    </ul>");

        return """
                <!DOCTYPE html>
                <html lang="pt-BR">
                  <head>
                    <meta charset="UTF-8">
                    <title>%s</title>
                  </head>
                  <body>
                    <h1>%s</h1>
                    <p><em>%s</em></p>
                    <h2>Sumário</h2>
                    %s
                  </body>
                </html>
                """.formatted(escape(ebook.title()), escape(ebook.title()),
                escape(ebook.author()), summary);
    }

    private Path resolveOutputDir(Path outputFileName) {
        String fileName = outputFileName.getFileName().toString();
        int dot = fileName.lastIndexOf('.');
        String dirName = dot > 0 ? fileName.substring(0, dot) : fileName;
        Path parent = outputFileName.getParent();
        return parent != null ? parent.resolve(dirName) : Path.of(dirName);
    }

    private static String escape(String text) {
        if (text == null) return "";
        return text
                .replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;");
    }
}
