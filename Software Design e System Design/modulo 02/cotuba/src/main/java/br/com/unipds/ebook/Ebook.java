package br.com.unipds.ebook;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Ebook {
    private String title;
    private String author;
    private final List<Chapter> chapters = new ArrayList<>();
    private Path fileName;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Path getFileName() {
        return fileName;
    }

    public void setFileName(Path fileName) {
        this.fileName = fileName;
    }

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
