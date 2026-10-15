package br.com.unipds;

import br.com.unipds.ebook.Chapter;
import org.commonmark.node.AbstractVisitor;
import org.commonmark.node.Heading;
import org.commonmark.node.Node;
import org.commonmark.node.Text;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

import java.nio.file.Path;
import java.util.List;

public class MarkdownRenderizerService {
    public List<Chapter> render(Path sourceDir) {
        MarkdownRepository markdownRepository = new MarkdownRepository();
        List<Chapter> chapters = markdownRepository.getChapters(sourceDir);
        for (Chapter chapter : chapters) {
            try {
                Parser parser = Parser.builder().build();
                Node document = null;

                document = parser.parse(chapter.getContent());
                document.accept(new AbstractVisitor() {
                    @Override
                    public void visit(Heading heading) {
                        if (heading.getLevel() == 1) {
                            chapter.setTitle(((Text) heading.getFirstChild()).getLiteral());
                        } else if (heading.getLevel() == 2) {
                            // seção
                        } else if (heading.getLevel() == 3) {
                            // título
                        }
                    }

                });
                HtmlRenderer renderer = HtmlRenderer.builder().build();
                chapter.setHtml(renderer.render(document));
            } catch (Exception ex) {
                throw new IllegalStateException("Erro ao fazer parse do arquivo " + chapter.getMarkDownFile(), ex);
            }
        }
        return chapters;
    }
}
