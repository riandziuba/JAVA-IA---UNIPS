package br.com.unipds.ebook;

public class ChapterBuilder {
    private String title;
    private Markdown markdown;
    private String html;

    public ChapterBuilder title(String title) {
        this.title = title;
        return this;
    }

    public ChapterBuilder markdown(Markdown markdown) {
        this.markdown = markdown;
        return this;
    }

    public ChapterBuilder html(String html) {
        this.html = html;
        return this;
    }

    public Chapter build() {
        return new Chapter(title, markdown, html);
    }
}
