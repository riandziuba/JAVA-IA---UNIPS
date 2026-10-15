package br.com.unipds.markdown;

import br.com.unipds.ebook.Chapter;
import br.com.unipds.ebook.ChapterBuilder;
import br.com.unipds.ebook.Markdown;
import jakarta.enterprise.context.ApplicationScoped;
import org.commonmark.node.AbstractVisitor;
import org.commonmark.node.Heading;
import org.commonmark.node.Node;
import org.commonmark.node.Text;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class MarkdownRenderizerCommonmarkService implements IMarkdownRenderizerService {

    public List<Chapter> render(List<Markdown> markdowns) {
        List<Chapter> chapters = new ArrayList<>();
        for (Markdown markdown : markdowns) {
            try {
                ChapterBuilder chapterBuilder = new ChapterBuilder();
                chapterBuilder.markdown(markdown);
                Parser parser = Parser.builder().build();
                Node document = parser.parse(markdown.content());
                document.accept(new AbstractVisitor() {
                    @Override
                    public void visit(Heading heading) {
                        if (heading.getLevel() == 1) {
                            if (heading.getFirstChild() instanceof Text text) {
                                chapterBuilder.title(text.getLiteral());
                            }
                        } else if (heading.getLevel() == 2) {
                            // seção
                        } else if (heading.getLevel() == 3) {
                            // título
                        }
                    }
                });

                HtmlRenderer renderer = HtmlRenderer.builder().build();
                chapters.add(chapterBuilder
                        .html(renderer.render(document))
                        .build());
            } catch (Exception ex) {
                throw new IllegalStateException("Erro ao fazer parse do arquivo " + markdown.markDownFile(), ex);
            }
        }
        return chapters;
    }
}
