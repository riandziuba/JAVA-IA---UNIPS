package br.com.unipds.ebook;

import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

public record Ebook (String title, String author, List<Chapter> chapters, Path fileName) {
    public void addChapter(Chapter chapter) {
        chapters.add(chapter);
    }

    public void addChapters(List<Chapter> chapters) {
        this.chapters.addAll(chapters);
    }

    public List<Chapter> getAllChapters() {
        return Collections.unmodifiableList(this.chapters);
    }
}
