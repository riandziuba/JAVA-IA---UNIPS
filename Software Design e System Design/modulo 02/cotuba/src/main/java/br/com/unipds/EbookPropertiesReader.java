package br.com.unipds;

import br.com.unipds.ebook.Ebook;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class EbookPropertiesReader {

    public void read(Path sourceDir, Ebook ebook) {
        Path propertiesFile = sourceDir.resolve("ebook.properties");

        if(!Files.exists(propertiesFile)) {
            throw new IllegalStateException("O arquivo ebook.properties não encontrado em:" + sourceDir);
        }

        Properties properties = new Properties();
        try (InputStream inputStream = Files.newInputStream(propertiesFile)) {
            properties.load(inputStream);
            String propertyTitle = "title";
            String propertyAuthor = "author";
            String title = properties.getProperty(propertyTitle);
            validateProperty(title, propertyTitle);
            String author = properties.getProperty(propertyAuthor);
            validateProperty(author, propertyTitle);
            ebook.setTitle(title);
            ebook.setAuthor(author);
        } catch (IOException e) {
            throw new IllegalStateException("Erro ao ler o arquivo: " + propertiesFile);
        }
    }

    private static void validateProperty(String property, String propertyKey) {
        if(property == null || property.isBlank()) {
            throw new IllegalStateException("Propriedade Invalida: " + propertyKey);
        }
    }
}
