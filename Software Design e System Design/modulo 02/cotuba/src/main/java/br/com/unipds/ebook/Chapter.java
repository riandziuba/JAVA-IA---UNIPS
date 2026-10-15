package br.com.unipds.ebook;

import java.nio.file.Path;

public class Chapter {
    private String title;
    private String content;
    private Path markDownFile;
    private String html;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Path getMarkDownFile() {
        return markDownFile;
    }

    public void setMarkDownFile(Path markDownFile) {
        this.markDownFile = markDownFile;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }
}
