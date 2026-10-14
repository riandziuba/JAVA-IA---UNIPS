package br.com.unipds.ebook;

import br.com.unipds.options.OptionsDTO;
import org.commonmark.node.AbstractVisitor;
import org.commonmark.node.Heading;
import org.commonmark.node.Node;
import org.commonmark.node.Text;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.List;
import java.util.stream.Stream;

public interface IEBookService {
    void generate(OptionsDTO options);
    default List<Path> getMDPaths(Path sourceDir) {
        PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:**/*.md");
        try (Stream<Path> streamMDs = Files.list(sourceDir)) {
            List<Path> mdFiles = streamMDs
                    .filter(matcher::matches)
                    .sorted()
                    .toList();

            if (mdFiles.isEmpty()) {
                throw new IllegalStateException("Não foram encontrados capítulos (arquivos .md) no diretório: " + sourceDir.toAbsolutePath());
            }

            return mdFiles;
        } catch (IOException ex) {
            throw new IllegalStateException("Erro tentando encontrar arquivos .md em " + sourceDir.toAbsolutePath(), ex);
        }
    }
    default String extractHtml(Path mdFile) {
        Parser parser = Parser.builder().build();
        Node document = null;
        try {
            document = parser.parseReader(Files.newBufferedReader(mdFile));
            document.accept(new AbstractVisitor() {
                @Override
                public void visit(Heading heading) {
                    if (heading.getLevel() == 1) {
                        // capítulo
                        String tituloDoCapitulo = ((Text) heading.getFirstChild()).getLiteral();
                        // TODO: usar título do capítulo
                    } else if (heading.getLevel() == 2) {
                        // seção
                    } else if (heading.getLevel() == 3) {
                        // título
                    }
                }

            });
        } catch (Exception ex) {
            throw new IllegalStateException("Erro ao fazer parse do arquivo " + mdFile, ex);
        }

        HtmlRenderer renderer = HtmlRenderer.builder().build();
        return renderer.render(document);
    }
}
